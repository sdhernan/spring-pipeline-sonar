package net.renfei.demo.persistencia.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "EXPE_TR_EXPEDIENTE")
public class Expediente implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3245540696246534358L;

	/** ID_EXPEDIENTE. */
	@Id
	@Column(name = "ID_EXPEDIENTE")
	private Long idExpediente;

	/** CH_FECHA_OPERACION. */
	@Column(name = "CH_FECHA_OPERACION")
	private String fechaOperacion;

	/** CH_FOLIO_OPERACION. */
	@Column(name = "CH_FOLIO_OPERACION")
	private String folioOperacion;

	/** identificador de procesar afore. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CV_AFORE", referencedColumnName = "CV_AFORE")
	private Afore afore;

	/** CH_USUARIO_MODIFICADOR. */
	@Column(name = "CH_USUARIO_MODIFICADOR")
	private String usuarioModificador;

	/** CURP. */
	@Column(name = "CURP")
	private String curp;

	/** CV_ESTATUS_EXPE. */
	@Column(name = "CV_ESTATUS_EXPE")
	private String claveEstatusExpe;

	/** CV_LUGAR_FIRMA. */
	@Column(name = "CV_LUGAR_FIRMA")
	private String claveLugarFirma;

	/** CV_TIPO_PROCESO. */
	@Column(name = "CV_TIPO_PROCESO")
	private String claveTipoProceso;

	/** FC_CONTROL. */
	@Temporal(TemporalType.DATE)
	@Column(name = "FC_CONTROL")
	private Date fechaControl;

	/** FC_LUGAR_FIRMA. */
	@Temporal(TemporalType.DATE)
	@Column(name = "FC_LUGAR_FIRMA")
	private Date fechaLugarFirma;

	/** ID_ENTIDAD_FEDERATIVA. */
	@Column(name = "ID_ENTIDAD_FEDERATIVA")
	private BigDecimal idEntidadFederativa;

	/** ID_PROCESAR. */
	@Column(name = "ID_PROCESAR")
	private BigDecimal idProcesar;

	/** ID_PROCESAR_AGENTE. */
	@Column(name = "ID_PROCESAR_AGENTE")
	private BigDecimal idProcesarAgente;

	/** ID_SOLICITUD. */
	@Column(name = "ID_SOLICITUD")
	private BigDecimal idSolicitud;

	/** NU_REGISTRADO_POR. */
	@Column(name = "NU_REGISTRADO_POR")
	private BigDecimal registradoPor;

	/** NU_TIENE_ARCHIVO_VOZ. */
	@Column(name = "NU_TIENE_ARCHIVO_VOZ")
	private BigDecimal tieneArchivoVoz;

	/** NU_TIENE_DATOS_COMPLEMENT. */
	@Column(name = "NU_TIENE_DATOS_COMPLEMENT")
	private BigDecimal tieneDatosComplement;

	/** NU_TIENE_EXCEPCION. */
	@Column(name = "NU_TIENE_EXCEPCION")
	private BigDecimal tieneExcepcion;

	/** NU_TIENE_IMAGENES. */
	@Column(name = "NU_TIENE_IMAGENES")
	private BigDecimal tieneImagenes;

	/**
	 * Instantiates a new expediente.
	 */
	public Expediente() {
		super();
	}

	/**
	 * Gets the afore.
	 *
	 * @return the afore
	 */
	public Afore getAfore() {
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
	public void setAfore(Afore afore) {
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

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Expediente [idExpediente=");
		builder.append(idExpediente);
		builder.append(", fechaOperacion=");
		builder.append(fechaOperacion);
		builder.append(", folioOperacion=");
		builder.append(folioOperacion);
		builder.append(", afore=");
		builder.append(afore);
		builder.append(", usuarioModificador=");
		builder.append(usuarioModificador);
		builder.append(", curp=");
		builder.append(curp);
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
