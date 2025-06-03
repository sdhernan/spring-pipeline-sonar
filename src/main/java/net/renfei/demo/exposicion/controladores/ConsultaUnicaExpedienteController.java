package net.renfei.demo.exposicion.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.renfei.demo.persistencia.modelos.ConsultaUniversalExpedienteModel;
import net.renfei.demo.servicios.FileService;

@RestController
@RequestMapping("/api/expediente")
public class ConsultaUnicaExpedienteController {

	@Autowired
	private FileService fileService;

	@PostMapping("/consulta")
	public ResponseEntity<Resource> consultaExpediente(@RequestBody ConsultaUniversalExpedienteModel consulta) {
		try {
			// Obtener el recurso usando el servicio actualizado
			Resource resource = fileService.getExpedienteResource(consulta);

			// Obtener el nombre del archivo según el tipo de documento
			String fileName = fileService.getFileName(consulta);

			// Determinar el Content-Type apropiado
			String contentType = fileService.getContentType(fileName);

			// Configurar los headers personalizados
			HttpHeaders headers = new HttpHeaders();
			headers.add("resultadoOperacion", "01");
			headers.add("nombreArchivo", fileName);
			headers.add("indicativoImagen", "1");
			headers.add("proceso", consulta.getProceso());
			headers.add("curp", consulta.getCurp());

			// Configurar header para forzar descarga o mostrar en el navegador según el
			// tipo
			if (consulta.getTipoDocumento() != null && "imagen".equalsIgnoreCase(consulta.getTipoDocumento())) {
				// Para imágenes, permitir que se muestren en el navegador
				headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"");
			} else {
				// Para ZIP u otros archivos, forzar descarga
				headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");
			}

			return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType(contentType))
					.body(resource);

		} catch (RuntimeException ex) {
			return ResponseEntity.notFound().build();
		}
	}
}
