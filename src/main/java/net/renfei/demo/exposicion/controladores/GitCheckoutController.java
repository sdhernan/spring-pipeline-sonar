package net.renfei.demo.exposicion.controladores;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.ProgressMonitor;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.renfei.demo.persistencia.modelos.FileLineInfo;
import net.renfei.demo.persistencia.modelos.GitBranchLineRequest;
import net.renfei.demo.persistencia.modelos.SvnLineResponse;

/**
 * The Class GitCheckoutController for repository checkout and line counting.
 * Compatible with GitHub, GitLab and Bitbucket.
 * Uses parallel processing with ExecutorService.
 */
@RestController
@RequestMapping("/api/git-checkout")
public class GitCheckoutController {

    /**
     * Tarea para procesar un archivo en paralelo.
     */
    private class FileProcessingTask implements Callable<FileLineInfo> {
        private final File file;
        private final File baseDir;
        private final Map<String, AtomicInteger> linesByExt;
        private final AtomicInteger totalLines;

        public FileProcessingTask(File file, File baseDir, Map<String, AtomicInteger> linesByExt,
                AtomicInteger totalLines) {
            this.file = file;
            this.baseDir = baseDir;
            this.linesByExt = linesByExt;
            this.totalLines = totalLines;
        }

        @Override
        public FileLineInfo call() throws Exception {
            long startTime = System.currentTimeMillis();

            try {
                // Obtener la ruta relativa del archivo
                String relativePath = baseDir.toPath().relativize(file.toPath()).toString();

                // Leer el contenido y contar líneas
                String content = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
                int lines = countLines(content);

                // Si hay diferencia grande entre métodos de conteo, ejecutar diagnóstico
                int splitLines = content.isEmpty() ? 0 : content.split("\r\n|\r|\n").length;
                if (Math.abs(lines - splitLines) > 1) {
                    synchronized (System.out) {
                        System.out.println("Detectada discrepancia en conteo de líneas para: " + relativePath);
                        diagnoseLineCountingIssue(file);
                    }
                }

                // Actualizar contadores
                totalLines.addAndGet(lines);

                String ext = getFilePattern(file.getName());
                linesByExt.computeIfAbsent(ext, k -> new AtomicInteger(0)).addAndGet(lines);

                // Mostrar información detallada para archivos grandes o lentos de procesar
                long processingTime = System.currentTimeMillis() - startTime;
                if (processingTime > 200 || lines > 1000 || file.length() > 200000) {
                    synchronized (System.out) {
                        System.out.printf("Archivo grande procesado: %s - %,d líneas - %s - %.2f segundos%n",
                                relativePath, lines, formatFileSize(file.length()), processingTime / 1000.0);
                    }
                }

                return new FileLineInfo(relativePath, lines);

            } catch (Exception e) {
                synchronized (System.err) {
                    System.err.println("ERROR procesando archivo: " + file.getPath() + " - " + e.getMessage());
                }
                throw e;
            }
        }
    }

    /**
     * Clase para monitorear el progreso del clone Git.
     */
    private class GitProgressMonitor implements org.eclipse.jgit.lib.ProgressMonitor {
        private static final long PROGRESS_UPDATE_INTERVAL = 2000; // Mostrar progreso cada 2 segundos
        private final AtomicInteger totalTasks = new AtomicInteger(0);
        private final AtomicInteger completedTasks = new AtomicInteger(0);
        private final AtomicLong totalBytes = new AtomicLong(0);
        private final AtomicInteger currentObjectCount = new AtomicInteger(0);
        private long lastUpdateTime = System.currentTimeMillis();
        private String currentTask;

        @Override
        public void start(int totalTasks) {
            this.totalTasks.set(totalTasks);
            System.out.println("Iniciando clone Git con " + totalTasks + " tareas totales");
        }

        @Override
        public void beginTask(String title, int totalWork) {
            currentTask = title;
            System.out.println("Iniciando tarea: " + title + " - Trabajo total: " + totalWork);
        }

        @Override
        public void update(int completed) {
            currentObjectCount.addAndGet(completed);
            totalBytes.addAndGet(completed);

            // Mostrar progreso periódicamente
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastUpdateTime > PROGRESS_UPDATE_INTERVAL) {
                lastUpdateTime = currentTime;
                System.out.println("Progreso en '" + currentTask + "': " + currentObjectCount.get() + " objetos ("
                        + formatFileSize(totalBytes.get()) + ")");
            }
        }

        @Override
        public void endTask() {
            completedTasks.incrementAndGet();
            System.out.println("Tarea completada: " + currentTask + " - " + completedTasks.get() + "/"
                    + totalTasks.get() + " tareas terminadas");
        }

        @Override
        public boolean isCancelled() {
            return false;
        }
      

        /**
         * Obtiene el tamaño total en bytes procesados.
         *
         * @return el tamaño total en bytes
         */
        public long getTotalBytes() {
            return totalBytes.get();
        }
    }

    /**
     * Lista de extensiones y patrones de archivos a procesar. Configurada para
     * coincidir con el comando de Linux.
     */
    private static final List<String> FILE_PATTERNS = Arrays.asList(
            ".java", // Archivos Java
            ".jsp", // Archivos JSP
            "orm.xml", // Archivos *orm.xml específicamente (no todos los XML)
            ".h", // Archivos de cabecera C/C++
            ".cpp", // Archivos C++
            ".pc" // Archivos Pro*C
    );

    /** Base temp directory for checkouts */
    private static final String TEMP_DIR_BASE = System.getProperty("java.io.tmpdir") + File.separator + "git-checkout-";

    /** Número de hilos para procesamiento paralelo */
    private static final int NUM_THREADS = Math.max(Runtime.getRuntime().availableProcessors() - 1, 1);

    /**
     * Count lines from branch checkout.
     *
     * @param request the request with repo URL, credentials, and branch
     * @return the line response
     * @throws Exception the exception
     */
    @PostMapping("/count-lines-branch")
    public SvnLineResponse countLinesFromBranch(@RequestBody GitBranchLineRequest request) throws Exception {
        System.out.println("=========================================================");
        System.out.println("INICIANDO PROCESAMIENTO DE CONTEO DE LÍNEAS CON CHECKOUT DE RAMA GIT");
        System.out.println("URL del repositorio: " + request.getRepoGit());
        System.out.println("Rama: " + (request.getBranch() != null ? request.getBranch() : "default"));
        System.out.println("Utilizando " + NUM_THREADS + " hilos para procesamiento paralelo");
        long startTime = System.currentTimeMillis();

        // Crear directorio temporal único para el checkout
        String tempDirId = UUID.randomUUID().toString();
        String tempDirPath = TEMP_DIR_BASE + tempDirId;
        File tempDir = new File(tempDirPath);

        try {
            // Crear el directorio temporal
            if (!tempDir.mkdirs()) {
                throw new Exception("No se pudo crear el directorio temporal: " + tempDirPath);
            }
            System.out.println("Directorio temporal creado: " + tempDirPath);

            // Configurar credenciales Git
            UsernamePasswordCredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider(
                    request.getUser(), request.getToken());

            // Monitor de progreso
            GitProgressMonitor progressMonitor = new GitProgressMonitor();

            // Configurar comando de clone
            CloneCommand cloneCommand = Git.cloneRepository()
                    .setURI(request.getRepoGit())
                    .setDirectory(tempDir)
                    .setCredentialsProvider(credentialsProvider)
                    .setProgressMonitor(progressMonitor);

            // Configurar la rama si se especifica
            if (request.getBranch() != null && !request.getBranch().isEmpty()) {
                cloneCommand.setBranch(request.getBranch());
                System.out.println("Clonando específicamente la rama: " + request.getBranch());
            } else {
                System.out.println("No se especificó rama, se clonará la rama por defecto");
            }

            // Realizar el clone
            System.out.println("Iniciando clone del repositorio Git...");
            long cloneStartTime = System.currentTimeMillis();
            Git git = null;

            try {
                git = cloneCommand.call();
                long cloneEndTime = System.currentTimeMillis();
                System.out.println("Clone completado en " + (cloneEndTime - cloneStartTime) + " ms");
                System.out.println("Tamaño total de datos transferidos: " + formatFileSize(progressMonitor.getTotalBytes()));
            } finally {
                if (git != null) {
                    git.close();
                }
            }

            // Obtener la lista de archivos a procesar
            System.out.println("Obteniendo lista de archivos del checkout...");
            long filesStartTime = System.currentTimeMillis();
            List<File> allFiles = getAllFiles(tempDir);
            List<File> filesToProcess = allFiles.stream().filter(this::shouldCount).collect(Collectors.toList());
            long filesEndTime = System.currentTimeMillis();

            System.out.println("Se encontraron " + allFiles.size() + " archivos en total");
            System.out.println("Se procesarán " + filesToProcess.size() + " archivos con las extensiones configuradas");

            // Imprimir desglose por extensión para verificación
            Map<String, Integer> fileCountByPattern = new HashMap<>();
            for (File file : filesToProcess) {
                String pattern = getFilePattern(file.getName());
                fileCountByPattern.put(pattern, fileCountByPattern.getOrDefault(pattern, 0) + 1);
            }

            System.out.println("\nDETALLE DE ARCHIVOS POR PATRÓN:");
            fileCountByPattern.forEach((pattern, count) -> {
                System.out.printf("  %s: %d archivos%n", pattern, count);
            });

            // Contadores y estructuras de datos thread-safe
            AtomicInteger totalLines = new AtomicInteger(0);
            CopyOnWriteArrayList<FileLineInfo> fileInfos = new CopyOnWriteArrayList<>();
            ConcurrentHashMap<String, AtomicInteger> linesByExt = new ConcurrentHashMap<>();

            // Crear pool de hilos para procesamiento paralelo
            ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

            // Procesar cada archivo en paralelo
            System.out.println("\nIniciando procesamiento paralelo de archivos con " + NUM_THREADS + " hilos...");
            long processingStartTime = System.currentTimeMillis();

            // Preparar las tareas para cada archivo
            List<Callable<FileLineInfo>> tasks = new ArrayList<>();
            for (File file : filesToProcess) {
                tasks.add(new FileProcessingTask(file, tempDir, linesByExt, totalLines));
            }

            // Contador para monitorear progreso
            AtomicInteger processedFiles = new AtomicInteger(0);
            long lastUpdateTime = processingStartTime;

            // Iniciar un hilo separado para monitorear el progreso
            Thread progressMonitorThread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Thread.sleep(1000); // Actualizar cada segundo

                        int current = processedFiles.get();
                        if (current >= filesToProcess.size()) {
                            break;
                        }

                        long currentTime = System.currentTimeMillis();
                        double percentComplete = (current * 100.0) / filesToProcess.size();
                        long elapsedMs = currentTime - processingStartTime;
                        double filesPerSecond = current * 1000.0 / Math.max(elapsedMs, 1);

                        // Estimar tiempo restante
                        long remainingFiles = filesToProcess.size() - current;
                        double remainingSeconds = remainingFiles / Math.max(filesPerSecond, 0.1);

                        System.out.printf(
                                "Progreso: %d/%d archivos (%.2f%%) - %.1f archivos/seg - %.1f seg restantes - Total líneas: %,d%n",
                                current, filesToProcess.size(), percentComplete, filesPerSecond, remainingSeconds,
                                totalLines.get());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });

            // Iniciar el monitor de progreso
            progressMonitorThread.setDaemon(true);
            progressMonitorThread.start();

            // Ejecutar todas las tareas en paralelo
            List<Future<FileLineInfo>> results = executor.invokeAll(tasks);

            // Detener el monitor de progreso
            progressMonitorThread.interrupt();

            // Recolectar resultados
            for (Future<FileLineInfo> future : results) {
                FileLineInfo fileInfo = future.get();
                fileInfos.add(fileInfo);
                processedFiles.incrementAndGet();
            }

            // Apagar el ExecutorService
            executor.shutdown();
            boolean terminated = executor.awaitTermination(5, TimeUnit.MINUTES);
            if (!terminated) {
                System.out.println("WARNING: El executor no terminó correctamente, forzando cierre.");
                executor.shutdownNow();
            }

            // Convertir el mapa de AtomicInteger a un mapa de Integer
            Map<String, Integer> finalLinesByExt = new HashMap<>();
            linesByExt.forEach((key, value) -> finalLinesByExt.put(key, value.get()));

            // Imprimir resumen por extensión
            System.out.println("\nRESUMEN POR EXTENSIÓN:");
            finalLinesByExt.forEach((ext, lines) -> {
                System.out.printf("  %s: %,d líneas%n", ext, lines);
            });

            // Crear la respuesta
            SvnLineResponse response = new SvnLineResponse();
            response.setExecutionDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            response.setFiles(new ArrayList<>(fileInfos));
            response.setTotalByExtension(finalLinesByExt);
            response.setTotalLines(totalLines.get());

            long endTime = System.currentTimeMillis();
            double totalTimeSeconds = (endTime - startTime) / 1000.0;
            System.out.println("\nPROCESO COMPLETADO");
            System.out.printf("Tiempo total: %.2f segundos%n", totalTimeSeconds);
            System.out.printf("Total de archivos procesados: %d%n", processedFiles.get());
            System.out.printf("Total de líneas contadas: %,d%n", totalLines.get());
            System.out.println("=========================================================");

            return response;

        } catch (GitAPIException e) {
            String errorMessage = "Error en operación Git: " + e.getMessage();
            System.err.println(errorMessage);
            throw new Exception(errorMessage, e);
        } finally {
            // Limpiar directorio temporal
            System.out.println("Eliminando directorio temporal: " + tempDirPath);
            try {
                FileUtils.deleteDirectory(tempDir);
                System.out.println("Directorio temporal eliminado correctamente");
            } catch (Exception e) {
                System.err.println("Error al eliminar directorio temporal: " + e.getMessage());
                // Intentar eliminar al salir
                tempDir.deleteOnExit();
            }
        }
    }

    /**
     * Cuenta el número de líneas en un string de forma compatible con wc -l.
     * Emula exactamente el comportamiento de wc -l en Linux.
     *
     * @param content el contenido a analizar
     * @return el número de líneas (igual que wc -l)
     */
    private int countLines(String content) {
        if (content == null || content.isEmpty()) {
            return 0;
        }

        // Contar solo los caracteres de nueva línea (\n), igual que wc -l
        int count = 0;
        for (int i = 0; i < content.length(); i++) {
            if (content.charAt(i) == '\n') {
                count++;
            }
        }
        return count;
    }

    /**
     * Método de diagnóstico para comparar diferentes formas de contar líneas. Útil
     * para identificar por qué hay discrepancias con wc -l.
     */
    private void diagnoseLineCountingIssue(File file) throws Exception {
        // Leer el contenido del archivo
        String content = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);

        // Método 1: Conteo estilo wc -l (solo \n)
        int countWcStyle = 0;
        for (int i = 0; i < content.length(); i++) {
            if (content.charAt(i) == '\n') {
                countWcStyle++;
            }
        }

        // Método 2: Conteo usando split
        int countSplit = 0;
        if (!content.isEmpty()) {
            countSplit = content.split("\r\n|\r|\n").length;
        }

        // Verificar si el archivo termina con nueva línea
        boolean endsWithNewline = !content.isEmpty()
                && (content.charAt(content.length() - 1) == '\n' || content.charAt(content.length() - 1) == '\r');

        // Contar diferentes tipos de terminadores de línea
        int countCRLF = 0; // Windows (\r\n)
        int countLF = 0; // Unix (\n)
        int countCR = 0; // Mac antiguo (\r)

        for (int i = 0; i < content.length(); i++) {
            if (content.charAt(i) == '\r') {
                if (i + 1 < content.length() && content.charAt(i + 1) == '\n') {
                    countCRLF++;
                    i++; // Saltar el \n
                } else {
                    countCR++;
                }
            } else if (content.charAt(i) == '\n') {
                countLF++;
            }
        }

        // Imprimir resultados
        System.out.println("Diagnóstico para: " + file.getName());
        System.out.println("  Conteo estilo wc -l (\\n): " + countWcStyle);
        System.out.println("  Conteo usando split: " + countSplit);
        System.out.println("  ¿Termina con nueva línea?: " + endsWithNewline);
        System.out.println("  Terminadores Windows (\\r\\n): " + countCRLF);
        System.out.println("  Terminadores Unix (\\n): " + countLF);
        System.out.println("  Terminadores Mac antiguo (\\r): " + countCR);

        // La causa más probable
        if (countSplit == countWcStyle + 1 && !endsWithNewline) {
            System.out.println("  CAUSA PROBABLE: El archivo no termina con nueva línea");
        } else if (countCRLF > 0 && countLF > 0) {
            System.out.println("  CAUSA PROBABLE: El archivo mezcla diferentes tipos de terminadores de línea");
        }
    }

    /**
     * Formatea el tamaño de archivo a una unidad legible.
     *
     * @param size el tamaño en bytes
     * @return el tamaño formateado (KB, MB, GB)
     */
    private String formatFileSize(long size) {
        if (size < 1024) {
            return size + " B";
        } else if (size < 1024 * 1024) {
            return String.format("%.2f KB", size / 1024.0);
        } else if (size < 1024 * 1024 * 1024) {
            return String.format("%.2f MB", size / (1024.0 * 1024));
        } else {
            return String.format("%.2f GB", size / (1024.0 * 1024 * 1024));
        }
    }

    /**
     * Gets all files recursively from a directory.
     *
     * @param directory the base directory
     * @return the list of all files
     */
    private List<File> getAllFiles(File directory) {
        List<File> files = new ArrayList<>();
        File[] entries = directory.listFiles();

        if (entries != null) {
            for (File entry : entries) {
                // Ignorar directorio .git
                if (entry.isDirectory()) {
                    if (!entry.getName().equals(".git")) {
                        files.addAll(getAllFiles(entry));
                    }
                } else if (entry.isFile()) {
                    files.add(entry);
                }
            }
        }

        return files;
    }

    /**
     * Gets the file pattern that matches.
     *
     * @param fileName the file name
     * @return the matching pattern or "otros" if none match
     */
    private String getFilePattern(String fileName) {
        // Caso especial para orm.xml
        if (fileName.endsWith("orm.xml")) {
            return "orm.xml";
        }

        // Para el resto de extensiones
        for (String pattern : FILE_PATTERNS) {
            if (!pattern.equals("orm.xml") && fileName.endsWith(pattern)) {
                return pattern;
            }
        }

        return "otros";
    }

    /**
     * Determina si un archivo debe ser contado basado en su nombre.
     * Emula exactamente el comportamiento del comando find en Linux.
     *
     * @param file el archivo a verificar
     * @return true si el archivo debe ser contado, false en caso contrario
     */
    private boolean shouldCount(File file) {
        String fileName = file.getName();

        // Verificar extensiones .java, .jsp, .h, .cpp, .pc
        if (fileName.endsWith(".java") || fileName.endsWith(".jsp") || fileName.endsWith(".h")
                || fileName.endsWith(".cpp") || fileName.endsWith(".pc")) {
            return true;
        }

        // Verificar específicamente archivos *orm.xml
        if (fileName.endsWith("orm.xml")) {
            return true;
        }

        return false;
    }
}