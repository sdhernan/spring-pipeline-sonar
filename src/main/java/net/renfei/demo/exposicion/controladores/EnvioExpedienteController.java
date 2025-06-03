package net.renfei.demo.exposicion.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.renfei.demo.servicios.ExpedienteClient;

@RestController
@RequestMapping("/api-expediente")
public class EnvioExpedienteController {
    
    @Autowired
    private ExpedienteClient expedienteClient;
    
    @PostMapping("/enviar-expediente")
    public String enviarExpediente() {
        
    	 return  expedienteClient.enviarExpediente(
                 "1", 
                 "1602070581", 
                 "S0000777202504220010", 
                 "usuario", 
                 "password", 
                 "D:\\552\\MDD\\expedientes\\identificacion\\552S0000777202504220010AALE821213HDFRPR00CAOE700723MSLSJV0002011745365554734.zip"
             );
            
    }
}