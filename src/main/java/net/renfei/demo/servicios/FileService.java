package net.renfei.demo.servicios;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import net.renfei.demo.persistencia.excepciones.FileNotFoundException;
import net.renfei.demo.persistencia.excepciones.FileStorageException;
import net.renfei.demo.persistencia.modelos.ConsultaUniversalExpedienteModel;

/**
 * The Class FileService.
 */
@Service
public class FileService {

	/** The file storage location. */
	private final Path fileStorageLocation;

	/** The test file path. */
	private final String TEST_FILE_PATH = "D:\\552\\Test";

	/** The zip file name. */
	// Constantes para los nombres de archivo
	private final String ZIP_FILE_NAME = "LEGF641005HNLLRR06EXPE01.20250310190541.zip";

	/** The image file name. */
	private final String IMAGE_FILE_NAME = "foto-trabajador.jpg";

	/**
	 * Instantiates a new file service.
	 */
	public FileService() {
		// Inicializamos la ruta a la carpeta especificada
		this.fileStorageLocation = Paths.get(TEST_FILE_PATH).toAbsolutePath().normalize();

		try {
			// Verificamos que el directorio exista, si no existe tratamos de crearlo
			if (!Files.exists(this.fileStorageLocation)) {
				Files.createDirectories(this.fileStorageLocation);
			}
		} catch (Exception ex) {
			throw new FileStorageException("No se pudo acceder al directorio: " + TEST_FILE_PATH, ex);
		}
	}

	/**
	 * Determina el tipo de contenido (Content-Type) basado en el nombre del
	 * archivo.
	 * 
	 * @param fileName El nombre del archivo
	 * @return El tipo de contenido MIME apropiado
	 */
	public String getContentType(String fileName) {
		if (fileName.toLowerCase().endsWith(".jpg") || fileName.toLowerCase().endsWith(".jpeg")) {
			return "image/jpeg";
		} else if (fileName.toLowerCase().endsWith(".png")) {
			return "image/png";
		} else if (fileName.toLowerCase().endsWith(".gif")) {
			return "image/gif";
		} else if (fileName.toLowerCase().endsWith(".zip")) {
			return "application/zip";
		} else {
			return "application/octet-stream";
		}
	}

	/**
	 * Gets the expediente resource.
	 *
	 * @param consulta the consulta
	 * @return the expediente resource
	 */
	public Resource getExpedienteResource(ConsultaUniversalExpedienteModel consulta) {
		try {
			String fileName;

			// Determinar qué archivo devolver basado en el tipoDocumento
			if (consulta.getTipoDocumento() != null && "imagen".equalsIgnoreCase(consulta.getTipoDocumento())) {
				fileName = IMAGE_FILE_NAME;
			} else {
				// Por defecto o si tipoDocumento es "zip", devolver el ZIP
				fileName = ZIP_FILE_NAME;
			}

			Path filePath = Paths.get(TEST_FILE_PATH, fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());

			if (resource.exists()) {
				return resource;
			} else {
				throw new FileNotFoundException(
						"No se pudo encontrar el archivo: " + fileName + " en la ruta: " + filePath.toString());
			}
		} catch (MalformedURLException ex) {
			throw new FileNotFoundException("Error al cargar el archivo", ex);
		}
	}

	/**
	 * Obtiene el nombre del archivo que se va a devolver basado en el modelo de
	 * consulta.
	 * 
	 * @param consulta El modelo de consulta
	 * @return El nombre del archivo a devolver
	 */
	public String getFileName(ConsultaUniversalExpedienteModel consulta) {
		if (consulta.getTipoDocumento() != null && "imagen".equalsIgnoreCase(consulta.getTipoDocumento())) {
			return IMAGE_FILE_NAME;
		} else {
			return ZIP_FILE_NAME;
		}
	}

	/**
	 * Load file as resource.
	 *
	 * @param fileName the file name
	 * @return the resource
	 */
	public Resource loadFileAsResource(String fileName) {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new FileNotFoundException("Archivo no encontrado: " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new FileNotFoundException("Archivo no encontrado: " + fileName, ex);
		}
	}

	/**
	 * Store file.
	 *
	 * @param file the file
	 * @return the string
	 */
	public String storeFile(MultipartFile file) {
		// Normalizar el nombre del archivo
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Verificar si el nombre del archivo contiene caracteres inválidos
			if (fileName.contains("..")) {
				throw new FileStorageException(
						"¡Lo siento! El nombre del archivo contiene una secuencia de ruta no válida " + fileName);
			}

			// Copiar el archivo a la ubicación de destino
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			return fileName;
		} catch (IOException ex) {
			throw new FileStorageException(
					"No se pudo almacenar el archivo " + fileName + ". Por favor, inténtelo de nuevo.", ex);
		}
	}
}