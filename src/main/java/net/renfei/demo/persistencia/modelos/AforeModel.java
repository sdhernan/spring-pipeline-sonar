package net.renfei.demo.persistencia.modelos;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class AforeModel.
 * 
 * @author SDHERNAN
 */
public class AforeModel implements Serializable {

	/** Serializacion. */
	private static final long serialVersionUID = -3861947866338064750L;

	/** The activo. */
	private Integer activo;

	/** The clave afore. */
	private String claveAfore;

	/** The descripcion afore. */
	private String descripcionAfore;

	/** The id. */
	private Long id;

	/** The fecha control. */
	private Date fechaControl;

	/** The usuario modificador. */
	private String usuarioModificador;

	/** The telefono afore. */
	private String telefonoAfore;

	/** The tipo administracion. */
	private String tipoAdministracion;

	/**
	 * Instantiates a new afore.
	 */
	public AforeModel() {
		super();
	}

	/**
	 * Gets the activo.
	 *
	 * @return the activo
	 */
	public Integer getActivo() {
		return activo;
	}

	/**
	 * Gets the clave afore.
	 *
	 * @return the clave afore
	 */
	public String getClaveAfore() {
		return claveAfore;
	}

	/**
	 * Gets the descripcion afore.
	 *
	 * @return the descripcion afore
	 */
	public String getDescripcionAfore() {
		return descripcionAfore;
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
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Gets the telefono afore.
	 *
	 * @return the telefono afore
	 */
	public String getTelefonoAfore() {
		return telefonoAfore;
	}

	/**
	 * Gets the tipo administracion.
	 *
	 * @return the tipo administracion
	 */
	public String getTipoAdministracion() {
		return tipoAdministracion;
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
	 * Sets the activo.
	 *
	 * @param activo the new activo
	 */
	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	/**
	 * Sets the clave afore.
	 *
	 * @param claveAfore the new clave afore
	 */
	public void setClaveAfore(String claveAfore) {
		this.claveAfore = claveAfore;
	}



	/**
	 * Sets the descripcion afore.
	 *
	 * @param descripcionAfore the new descripcion afore
	 */
	public void setDescripcionAfore(String descripcionAfore) {
		this.descripcionAfore = descripcionAfore;
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
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Sets the telefono afore.
	 *
	 * @param telefonoAfore the new telefono afore
	 */
	public void setTelefonoAfore(String telefonoAfore) {
		this.telefonoAfore = telefonoAfore;
	}

	/**
	 * Sets the tipo administracion.
	 *
	 * @param tipoAdministracion the new tipo administracion
	 */
	public void setTipoAdministracion(String tipoAdministracion) {
		this.tipoAdministracion = tipoAdministracion;
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
		builder.append("AforeModel [activo=");
		builder.append(activo);
		builder.append(", claveAfore=");
		builder.append(claveAfore);
		builder.append(", descripcionAfore=");
		builder.append(descripcionAfore);
		builder.append(", id=");
		builder.append(id);
		builder.append(", usuarioModificador=");
		builder.append(usuarioModificador);
		builder.append(", telefonoAfore=");
		builder.append(telefonoAfore);
		builder.append(", tipoAdministracion=");
		builder.append(tipoAdministracion);
		builder.append("]");
		return builder.toString();
	}



}
