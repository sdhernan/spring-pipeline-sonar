package net.renfei.demo.servicios;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ExpedienteClient {
    
    private static final Logger logger = LoggerFactory.getLogger(ExpedienteClient.class);
    private static final String ENDPOINT = "http://172.21.66.90/ProcesarECM/RegistroExpediente";
    
    public String enviarExpediente(String tipoCarga, String agente, String folioProcesar, 
                                   String usuario,String password, String rutaArchivo) {
        try {
            // Crear el mapa multivalue para los parámetros
            MultiValueMap<String, Object> multiValoresMapa = new LinkedMultiValueMap<>();
            multiValoresMapa.add("tipoCarga", tipoCarga);
            multiValoresMapa.add("agente", agente);
            multiValoresMapa.add("zipExpediente", new FileSystemResource(rutaArchivo));
            multiValoresMapa.add("folioProcesar", folioProcesar);
            multiValoresMapa.add("usuario", usuario);
            multiValoresMapa.add("password", password);
            
            // Configurar cabeceras HTTP
            HttpHeaders cabeceras = new HttpHeaders();
            cabeceras.setContentType(MediaType.MULTIPART_FORM_DATA);
            
            // Crear entidad HTTP con el cuerpo y las cabeceras
            HttpEntity<MultiValueMap<String, Object>> entidadSolicitud = 
                new HttpEntity<>(multiValoresMapa, cabeceras);
            
            // Imprimir información de depuración (sin mostrar el archivo completo)
            logger.info("Enviando solicitud a {}", ENDPOINT);
            logger.info("Parámetros: tipoCarga={}, agente={}, folioProcesar={}, usuario={}, archivo={}",
                    tipoCarga, agente, folioProcesar, usuario, rutaArchivo);
            
            // Enviar solicitud
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> respuesta = restTemplate.exchange(
                ENDPOINT, 
                HttpMethod.POST, 
                entidadSolicitud, 
                String.class
            );
            
            logger.info("Respuesta recibida: {}", respuesta.getStatusCode());
            return respuesta.getBody();
            
        } catch (Exception e) {
            logger.error("Error al enviar el expediente", e);
            return "Error: " + e.getMessage();
        }
    }
}