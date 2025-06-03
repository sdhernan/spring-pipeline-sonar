package net.renfei.demo.persistencia.modelos;

import java.io.Serializable;

/**
 * The Class ConsultaUniversalExpedienteModel.
 */
public class ConsultaUniversalExpedienteModel implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The afore. */
	private String afore;

	/** The curp. */
	private String curp;

	/** The proceso. */
	private String proceso;

	/** The fecha operacion. */
	private String fechaOperacion;

	/** The folio consecutivo. */
	private String folioConsecutivo;

	/** The clave documento. */
	private String claveDocumento;

	/** The tipo documento. */
	private String tipoDocumento;

	/**
	 * Instantiates a new consulta universal expediente model.
	 */
	public ConsultaUniversalExpedienteModel() {
		super();
	}

	/**
	 * Gets the afore.
	 *
	 * @return the afore
	 */
	public String getAfore() {
		return afore;
	}

	/**
	 * Gets the clave documento.
	 *
	 * @return the clave documento
	 */
	public String getClaveDocumento() {
		return claveDocumento;
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
	 * Gets the fecha operacion.
	 *
	 * @return the fecha operacion
	 */
	public String getFechaOperacion() {
		return fechaOperacion;
	}

	/**
	 * Gets the folio consecutivo.
	 *
	 * @return the folio consecutivo
	 */
	public String getFolioConsecutivo() {
		return folioConsecutivo;
	}

	/**
	 * Gets the proceso.
	 *
	 * @return the proceso
	 */
	public String getProceso() {
		return proceso;
	}

	/**
	 * Gets the tipo documento.
	 *
	 * @return the tipo documento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * Sets the afore.
	 *
	 * @param afore the new afore
	 */
	public void setAfore(String afore) {
		this.afore = afore;
	}

	/**
	 * Sets the clave documento.
	 *
	 * @param claveDocumento the new clave documento
	 */
	public void setClaveDocumento(String claveDocumento) {
		this.claveDocumento = claveDocumento;
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
	 * Sets the fecha operacion.
	 *
	 * @param fechaOperacion the new fecha operacion
	 */
	public void setFechaOperacion(String fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	/**
	 * Sets the folio consecutivo.
	 *
	 * @param folioConsecutivo the new folio consecutivo
	 */
	public void setFolioConsecutivo(String folioConsecutivo) {
		this.folioConsecutivo = folioConsecutivo;
	}

	/**
	 * Sets the proceso.
	 *
	 * @param proceso the new proceso
	 */
	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	/**
	 * Sets the tipo documento.
	 *
	 * @param tipoDocumento the new tipo documento
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ConsultaUniversalExpedienteModel [afore=");
		builder.append(afore);
		builder.append(", curp=");
		builder.append(curp);
		builder.append(", proceso=");
		builder.append(proceso);
		builder.append(", fechaOperacion=");
		builder.append(fechaOperacion);
		builder.append(", folioConsecutivo=");
		builder.append(folioConsecutivo);
		builder.append(", claveDocumento=");
		builder.append(claveDocumento);
		builder.append(", tipoDocumento=");
		builder.append(tipoDocumento);
		builder.append("]");
		return builder.toString();
	}

}
