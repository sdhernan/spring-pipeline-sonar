package net.renfei.demo.persistencia.modelos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The Class ExpedienteModel.
 * 
 * @author SDHERNAN
 */
public class ExpedienteModel implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3245540696246534358L;

	/** The id expediente. */
	private Long idExpediente;

	/** The fecha operacion. */
	private String fechaOperacion;

	/** The folio operacion. */
	private String folioOperacion;

	/** The usuario modificador. */
	private String usuarioModificador;

	/** The curp. */
	private String curp;

	/** The afore. */
	private AforeModel afore;

	/** The clave estatus expe. */
	private String claveEstatusExpe;

	/** The clave lugar firma. */
	private String claveLugarFirma;

	/** The clave tipo proceso. */
	private String claveTipoProceso;

	/** The fecha control. */
	private Date fechaControl;

	/** The fecha lugar firma. */
	private Date fechaLugarFirma;

	/** The id entidad federativa. */
	private BigDecimal idEntidadFederativa;

	/** The id procesar. */
	private BigDecimal idProcesar;

	/** The id procesar agente. */
	private BigDecimal idProcesarAgente;

	/** The id solicitud. */
	private BigDecimal idSolicitud;

	/** The registrado por. */
	private BigDecimal registradoPor;

	/** The tiene archivo voz. */
	private BigDecimal tieneArchivoVoz;

	/** The tiene datos complement. */
	private BigDecimal tieneDatosComplement;

	/** The tiene excepcion. */
	private BigDecimal tieneExcepcion;

	/** The tiene imagenes. */
	private BigDecimal tieneImagenes;

	/**
	 * Instantiates a new expediente.
	 */
	public ExpedienteModel() {
		super();
	}

	/**
	 * Gets the afore.
	 *
	 * @return the afore
	 */
	public AforeModel getAfore() {
		return afore;
	}

	/**
	 * Gets the clave estatus expe.
	 *
	 * @return the clave estatus expe
	 */
	public String getClaveEstatusExpe() {
		return claveEstatusExpe;
	}

	/**
	 * Gets the clave lugar firma.
	 *
	 * @return the clave lugar firma
	 */
	public String getClaveLugarFirma() {
		return claveLugarFirma;
	}

	/**
	 * Gets the clave tipo proceso.
	 *
	 * @return the clave tipo proceso
	 */
	public String getClaveTipoProceso() {
		return claveTipoProceso;
	}

	/**
	 * Gets the curp.
	 *
	 * @return the curp
	 */
	public String getCurp() {
		return curp;
	}

	/**
	 * Gets the fecha control.
	 *
	 * @return the fecha control
	 */
	public Date getFechaControl() {
		return fechaControl;
	}

	/**
	 * Gets the fecha lugar firma.
	 *
	 * @return the fecha lugar firma
	 */
	public Date getFechaLugarFirma() {
		return fechaLugarFirma;
	}

	/**
	 * Gets the fecha operacion.
	 *
	 * @return the fecha operacion
	 */
	public String getFechaOperacion() {
		return fechaOperacion;
	}

	/**
	 * Gets the folio operacion.
	 *
	 * @return the folio operacion
	 */
	public String getFolioOperacion() {
		return folioOperacion;
	}

	/**
	 * Gets the id entidad federativa.
	 *
	 * @return the id entidad federativa
	 */
	public BigDecimal getIdEntidadFederativa() {
		return idEntidadFederativa;
	}

	/**
	 * Gets the id expediente.
	 *
	 * @return the id expediente
	 */
	public Long getIdExpediente() {
		return idExpediente;
	}

	/**
	 * Gets the id procesar.
	 *
	 * @return the id procesar
	 */
	public BigDecimal getIdProcesar() {
		return idProcesar;
	}

	/**
	 * Gets the id procesar agente.
	 *
	 * @return the id procesar agente
	 */
	public BigDecimal getIdProcesarAgente() {
		return idProcesarAgente;
	}

	/**
	 * Gets the id solicitud.
	 *
	 * @return the id solicitud
	 */
	public BigDecimal getIdSolicitud() {
		return idSolicitud;
	}

	/**
	 * Gets the registrado por.
	 *
	 * @return the registrado por
	 */
	public BigDecimal getRegistradoPor() {
		return registradoPor;
	}

	/**
	 * Gets the tiene archivo voz.
	 *
	 * @return the tiene archivo voz
	 */
	public BigDecimal getTieneArchivoVoz() {
		return tieneArchivoVoz;
	}

	/**
	 * Gets the tiene datos complement.
	 *
	 * @return the tiene datos complement
	 */
	public BigDecimal getTieneDatosComplement() {
		return tieneDatosComplement;
	}

	/**
	 * Gets the tiene excepcion.
	 *
	 * @return the tiene excepcion
	 */
	public BigDecimal getTieneExcepcion() {
		return tieneExcepcion;
	}

	/**
	 * Gets the tiene imagenes.
	 *
	 * @return the tiene imagenes
	 */
	public BigDecimal getTieneImagenes() {
		return tieneImagenes;
	}

	/**
	 * Gets the usuario modificador.
	 *
	 * @return the usuario modificador
	 */
	public String getUsuarioModificador() {
		return usuarioModificador;
	}

	/**
	 * Sets the afore.
	 *
	 * @param afore the new afore
	 */
	public void setAfore(AforeModel afore) {
		this.afore = afore;
	}

	/**
	 * Sets the clave estatus expe.
	 *
	 * @param claveEstatusExpe the new clave estatus expe
	 */
	public void setClaveEstatusExpe(String claveEstatusExpe) {
		this.claveEstatusExpe = claveEstatusExpe;
	}

	/**
	 * Sets the clave lugar firma.
	 *
	 * @param claveLugarFirma the new clave lugar firma
	 */
	public void setClaveLugarFirma(String claveLugarFirma) {
		this.claveLugarFirma = claveLugarFirma;
	}

	/**
	 * Sets the clave tipo proceso.
	 *
	 * @param claveTipoProceso the new clave tipo proceso
	 */
	public void setClaveTipoProceso(String claveTipoProceso) {
		this.claveTipoProceso = claveTipoProceso;
	}

	/**
	 * Sets the curp.
	 *
	 * @param curp the new curp
	 */
	public void setCurp(String curp) {
		this.curp = curp;
	}

	/**
	 * Sets the fecha control.
	 *
	 * @param fechaControl the new fecha control
	 */
	public void setFechaControl(Date fechaControl) {
		this.fechaControl = fechaControl;
	}

	/**
	 * Sets the fecha lugar firma.
	 *
	 * @param fechaLugarFirma the new fecha lugar firma
	 */
	public void setFechaLugarFirma(Date fechaLugarFirma) {
		this.fechaLugarFirma = fechaLugarFirma;
	}

	/**
	 * Sets the fecha operacion.
	 *
	 * @param fechaOperacion the new fecha operacion
	 */
	public void setFechaOperacion(String fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	/**
	 * Sets the folio operacion.
	 *
	 * @param folioOperacion the new folio operacion
	 */
	public void setFolioOperacion(String folioOperacion) {
		this.folioOperacion = folioOperacion;
	}

	/**
	 * Sets the id entidad federativa.
	 *
	 * @param idEntidadFederativa the new id entidad federativa
	 */
	public void setIdEntidadFederativa(BigDecimal idEntidadFederativa) {
		this.idEntidadFederativa = idEntidadFederativa;
	}

	/**
	 * Sets the id expediente.
	 *
	 * @param idExpediente the new id expediente
	 */
	public void setIdExpediente(Long idExpediente) {
		this.idExpediente = idExpediente;
	}

	/**
	 * Sets the id procesar.
	 *
	 * @param idProcesar the new id procesar
	 */
	public void setIdProcesar(BigDecimal idProcesar) {
		this.idProcesar = idProcesar;
	}

	/**
	 * Sets the id procesar agente.
	 *
	 * @param idProcesarAgente the new id procesar agente
	 */
	public void setIdProcesarAgente(BigDecimal idProcesarAgente) {
		this.idProcesarAgente = idProcesarAgente;
	}

	/**
	 * Sets the id solicitud.
	 *
	 * @param idSolicitud the new id solicitud
	 */
	public void setIdSolicitud(BigDecimal idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	/**
	 * Sets the registrado por.
	 *
	 * @param registradoPor the new registrado por
	 */
	public void setRegistradoPor(BigDecimal registradoPor) {
		this.registradoPor = registradoPor;
	}

	/**
	 * Sets the tiene archivo voz.
	 *
	 * @param tieneArchivoVoz the new tiene archivo voz
	 */
	public void setTieneArchivoVoz(BigDecimal tieneArchivoVoz) {
		this.tieneArchivoVoz = tieneArchivoVoz;
	}

	/**
	 * Sets the tiene datos complement.
	 *
	 * @param tieneDatosComplement the new tiene datos complement
	 */
	public void setTieneDatosComplement(BigDecimal tieneDatosComplement) {
		this.tieneDatosComplement = tieneDatosComplement;
	}

	/**
	 * Sets the tiene excepcion.
	 *
	 * @param tieneExcepcion the new tiene excepcion
	 */
	public void setTieneExcepcion(BigDecimal tieneExcepcion) {
		this.tieneExcepcion = tieneExcepcion;
	}

	/**
	 * Sets the tiene imagenes.
	 *
	 * @param tieneImagenes the new tiene imagenes
	 */
	public void setTieneImagenes(BigDecimal tieneImagenes) {
		this.tieneImagenes = tieneImagenes;
	}

	/**
	 * Sets the usuario modificador.
	 *
	 * @param usuarioModificador the new usuario modificador
	 */
	public void setUsuarioModificador(String usuarioModificador) {
		this.usuarioModificador = usuarioModificador;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExpedienteModel [idExpediente=");
		builder.append(idExpediente);
		builder.append(", fechaOperacion=");
		builder.append(fechaOperacion);
		builder.append(", folioOperacion=");
		builder.append(folioOperacion);
		builder.append(", usuarioModificador=");
		builder.append(usuarioModificador);
		builder.append(", curp=");
		builder.append(curp);
		builder.append(", afore=");
		builder.append(afore);
		builder.append(", claveEstatusExpe=");
		builder.append(claveEstatusExpe);
		builder.append(", claveLugarFirma=");
		builder.append(claveLugarFirma);
		builder.append(", claveTipoProceso=");
		builder.append(claveTipoProceso);
		builder.append(", fechaControl=");
		builder.append(fechaControl);
		builder.append(", fechaLugarFirma=");
		builder.append(fechaLugarFirma);
		builder.append(", idEntidadFederativa=");
		builder.append(idEntidadFederativa);
		builder.append(", idProcesar=");
		builder.append(idProcesar);
		builder.append(", idProcesarAgente=");
		builder.append(idProcesarAgente);
		builder.append(", idSolicitud=");
		builder.append(idSolicitud);
		builder.append(", registradoPor=");
		builder.append(registradoPor);
		builder.append(", tieneArchivoVoz=");
		builder.append(tieneArchivoVoz);
		builder.append(", tieneDatosComplement=");
		builder.append(tieneDatosComplement);
		builder.append(", tieneExcepcion=");
		builder.append(tieneExcepcion);
		builder.append(", tieneImagenes=");
		builder.append(tieneImagenes);
		builder.append("]");
		return builder.toString();
	}

}
