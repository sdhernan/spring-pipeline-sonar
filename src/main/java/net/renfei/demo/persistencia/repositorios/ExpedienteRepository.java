package net.renfei.demo.persistencia.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.renfei.demo.persistencia.entidades.Expediente;

/**
 * The Interface ExpedienteRepository.
 * 
 * @author SDHERNAN
 */
@Repository
public interface ExpedienteRepository extends JpaRepository<Expediente, Long> {

	/**
	 * Find by curp and clave tipo proceso and clave estatus expe.
	 *
	 * @param curp             the curp
	 * @param claveTipoProceso the clave tipo proceso
	 * @param estatus          the estatus
	 * @return the list
	 */
	List<Expediente> findByCurpAndClaveTipoProcesoAndClaveEstatusExpe(String curp, String claveTipoProceso,
			String estatus);

	/**
	 * Find by curp and clave tipo proceso and clave estatus expe order by id
	 * expediente desc.
	 *
	 * @param curp             the curp
	 * @param claveTipoProceso the clave tipo proceso
	 * @param estatus          the estatus
	 * @return the list
	 */
	List<Expediente> findByCurpAndClaveTipoProcesoAndClaveEstatusExpeOrderByIdExpedienteDesc(String curp,
			String claveTipoProceso, String estatus);

}
