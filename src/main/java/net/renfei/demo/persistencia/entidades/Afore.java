package net.renfei.demo.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Class Afore.
 * 
 * @author SDHERNAN
 */
@Entity
@Table(name = "NSAR_TC_AFORE")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Afore implements Serializable {

	/** Serializacion. */
	private static final long serialVersionUID = -3861947866338064750L;

	/** Identificador. */
	@Id
	@Column(name = "ID_PROCESAR")
	private Long id;

	/** Indicador de activo/inactivo. */
	@Column(name = "NU_ACTIVO")
	private Integer activo;

	/** Clave de la afore. */
	@Column(name = "CV_AFORE")
	private String claveAfore;

	/** Descripci�n. */
	@Column(name = "CH_DESC_AFORE")
	private String descripcionAfore;

	/** /** Fecha de control. */
	@Temporal(TemporalType.DATE)
	@Column(name = "FC_CONTROL")
	private Date fechaControl;

	/** Usuario modificador. */
	@Column(name = "CH_USUARIO_MODIFICADOR")
	private String usuarioModificador;

	/** Tel�fono de la Afore. */
	@Column(name = "CH_TELEFONO_AFORE")
	private String telefonoAfore;

	/** Tipo de administracion. */
	@Column(name = "CH_TIPO_ADMINISTRACION")
	private String tipoAdministracion;

	/**
	 * Instantiates a new afore.
	 */
	public Afore() {
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
		builder.append("Afore [id=");
		builder.append(id);
		builder.append(", activo=");
		builder.append(activo);
		builder.append(", claveAfore=");
		builder.append(claveAfore);
		builder.append(", descripcionAfore=");
		builder.append(descripcionAfore);
		builder.append(", fechaControl=");
		builder.append(fechaControl);
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
