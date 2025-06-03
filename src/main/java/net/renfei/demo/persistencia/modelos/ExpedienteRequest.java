package net.renfei.demo.persistencia.modelos;

import org.springframework.web.multipart.MultipartFile;

public class ExpedienteRequest {
    private String tipoCarga;
    private String agente;
    private String folioProcesar;
    private String usuario;
    private MultipartFile zipExpediente;
    
    // Getters y setters
    public String getTipoCarga() {
        return tipoCarga;
    }
    
    public void setTipoCarga(String tipoCarga) {
        this.tipoCarga = tipoCarga;
    }
    
    public String getAgente() {
        return agente;
    }
    
    public void setAgente(String agente) {
        this.agente = agente;
    }
    
    public String getFolioProcesar() {
        return folioProcesar;
    }
    
    public void setFolioProcesar(String folioProcesar) {
        this.folioProcesar = folioProcesar;
    }
    
    public String getUsuario() {
        return usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public MultipartFile getZipExpediente() {
        return zipExpediente;
    }
    
    public void setZipExpediente(MultipartFile zipExpediente) {
        this.zipExpediente = zipExpediente;
    }
}