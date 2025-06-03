package net.renfei.demo.exposicion.controladores;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import net.renfei.demo.persistencia.modelos.FileLineInfo;
import net.renfei.demo.persistencia.modelos.SvnLineRequest;
import net.renfei.demo.persistencia.modelos.SvnLineResponse;

/**
 * The Class SvnLineController with parallel processing.
 */
@RestController
@RequestMapping("/api/svn")
public class SvnLineController {

	/** The Constant EXTENSIONS. */
	private static final List<String> EXTENSIONS = Arrays.asList(".java", ".jsp", "orm.xml");

	/** Number of threads in the thread pool */
	private static final int THREAD_POOL_SIZE = 10;

	/**
	 * Count lines.
	 *
	 * @param request the request
	 * @return the svn line response
	 * @throws Exception the exception
	 */
	@PostMapping("/count-lines")
	public SvnLineResponse countLines(@RequestBody SvnLineRequest request) throws Exception {
		System.out.println("=========================================================");
		System.out.println("INICIANDO PROCESAMIENTO DE CONTEO DE LÍNEAS EN PARALELO");
		System.out.println("URL del repositorio: " + request.getRepoURL());
		long startTime = System.currentTimeMillis();

		SVNURL url = SVNURL.parseURIEncoded(request.getRepoURL());
		ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(request.getUser(),
				request.getPassword());
		SVNRepository repository = SVNRepositoryFactory.create(url);
		repository.setAuthenticationManager(authManager);
		System.out.println("Conexión establecida con el repositorio SVN");

		// Usando colecciones thread-safe
		List<FileLineInfo> fileInfos = new CopyOnWriteArrayList<>();
		Map<String, AtomicInteger> linesByExt = new ConcurrentHashMap<>();
		AtomicInteger totalLines = new AtomicInteger(0);

		// Crear un pool de hilos
		System.out.println("Creando pool de " + THREAD_POOL_SIZE + " hilos");
		ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

		// Obtener la lista de archivos a procesar
		System.out.println("Obteniendo lista de archivos del repositorio...");
		long filesStartTime = System.currentTimeMillis();
		List<String> filePaths = getAllFilePaths(repository, "");
		long filesEndTime = System.currentTimeMillis();
		System.out.println("Se encontraron " + filePaths.size() + " archivos en total (en "
				+ (filesEndTime - filesStartTime) + " ms)");

		// Filtrar solo los archivos que deben contarse
		List<String> filesToProcess = filePaths.stream().filter(this::shouldCount).collect(Collectors.toList());
		System.out.println("Se procesarán " + filesToProcess.size() + " archivos con las extensiones configuradas");

		// Contadores para seguimiento
		AtomicInteger processedFiles = new AtomicInteger(0);
		AtomicLong lastLogTime = new AtomicLong(System.currentTimeMillis());
		int totalFilesToProcess = filesToProcess.size();

		// Procesar cada archivo en paralelo
		System.out.println("Iniciando procesamiento en paralelo...");
		List<CompletableFuture<Void>> futures = filesToProcess.stream()
				.map(filePath -> CompletableFuture.runAsync(() -> {
					try {
						processFile(repository, filePath, fileInfos, linesByExt, totalLines);

						// Actualizar contador de archivos procesados
						int processed = processedFiles.incrementAndGet();

						// Imprimir progreso cada segundo o cada 50 archivos
						long currentTime = System.currentTimeMillis();
						if (processed % 50 == 0 || currentTime - lastLogTime.get() > 1000) {
							lastLogTime.set(currentTime);
							double percentComplete = (processed * 100.0) / totalFilesToProcess;
							System.out.printf("Progreso: %d/%d archivos (%.2f%%) - Total líneas: %d%n", processed,
									totalFilesToProcess, percentComplete, totalLines.get());
						}
					} catch (SVNException e) {
						System.err.println("Error procesando archivo " + filePath + ": " + e.getMessage());
						throw new RuntimeException(e);
					}
				}, executor)).collect(Collectors.toList());

		// Esperar a que todos los hilos terminen
		System.out.println("Esperando a que todos los hilos terminen...");
		CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

		// Cerrar el pool de hilos
		executor.shutdown();
		System.out.println("Todos los hilos han finalizado");

		// Convertir el mapa de AtomicInteger a un mapa de Integer
		Map<String, Integer> finalLinesByExt = new HashMap<>();
		linesByExt.forEach((key, value) -> finalLinesByExt.put(key, value.get()));

		// Imprimir resumen por extensión
		System.out.println("\nRESUMEN POR EXTENSIÓN:");
		finalLinesByExt.forEach((ext, lines) -> {
			System.out.printf("  %s: %,d líneas%n", ext, lines);
		});

		SvnLineResponse response = new SvnLineResponse();
		response.setExecutionDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		response.setFiles(fileInfos);
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
	}

	/**
	 * Gets all file paths recursively.
	 *
	 * @param repository the repository
	 * @param path       the path
	 * @return the list of all file paths
	 * @throws SVNException the SVN exception
	 */
	private List<String> getAllFilePaths(SVNRepository repository, String path) throws SVNException {
		List<String> filePaths = new ArrayList<>();
		Collection<SVNDirEntry> entries = repository.getDir(path, -1, null, (Collection<?>) null);

		if (path.isEmpty()) {
			System.out.println("Explorando directorio raíz del repositorio...");
		} else if (path.split("/").length == 1) {
			System.out.println("Explorando directorio: " + path);
		}

		for (SVNDirEntry entry : entries) {
			String fullPath = path.isEmpty() ? entry.getName() : path + "/" + entry.getName();

			if (entry.getKind() == SVNNodeKind.DIR) {
				filePaths.addAll(getAllFilePaths(repository, fullPath));
			} else {
				filePaths.add(fullPath);
			}
		}

		return filePaths;
	}

	/**
	 * Gets the extension.
	 *
	 * @param filePath the file path
	 * @return the extension
	 */
	private String getExtension(String filePath) {
		return EXTENSIONS.stream().filter(filePath::endsWith).findFirst().orElse("otros");
	}

	/**
	 * Process a single file.
	 *
	 * @param repository the repository
	 * @param filePath   the file path
	 * @param fileInfos  the file infos list
	 * @param linesByExt the lines by extension map
	 * @param totalLines the total lines counter
	 * @throws SVNException the SVN exception
	 */
	private void processFile(SVNRepository repository, String filePath, List<FileLineInfo> fileInfos,
			Map<String, AtomicInteger> linesByExt, AtomicInteger totalLines) throws SVNException {

		long startTime = System.currentTimeMillis();

		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			repository.getFile(filePath, -1, null, baos);
			String content = new String(baos.toByteArray(), StandardCharsets.UTF_8);

			int lines = (int) Arrays.stream(content.split("\r\n|\r|\n")).count();

			totalLines.addAndGet(lines);
			fileInfos.add(new FileLineInfo(filePath, lines));

			String ext = getExtension(filePath);
			linesByExt.computeIfAbsent(ext, k -> new AtomicInteger(0)).addAndGet(lines);

			// Debug detallado solo para archivos grandes
			long processingTime = System.currentTimeMillis() - startTime;
			if (processingTime > 500 || lines > 1000) {
				System.out.printf("Archivo grande procesado: %s - %d líneas (%.2f segundos)%n", filePath, lines,
						processingTime / 1000.0);
			}
		} catch (Exception e) {
			System.err.println("ERROR procesando archivo: " + filePath + " - " + e.getMessage());
			throw e;
		}
	}

	/**
	 * Should count.
	 *
	 * @param filePath the file path
	 * @return true, if successful
	 */
	private boolean shouldCount(String filePath) {
		return EXTENSIONS.stream().anyMatch(filePath::endsWith);
	}
}