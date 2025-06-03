package net.renfei.demo.servicios;

import net.renfei.demo.persistencia.modelos.ExpedienteModel;

/**
 * The Interface ExpedienteService.
 */
public interface ExpedienteService {

	/**
	 * Buscar expediente por curp por proceso por estatus ordenado por fecha
	 * operacion.
	 *
	 * @param curp         the curp
	 * @param claveProceso the clave proceso
	 * @param estatus      the estatus
	 * @return the expediente model
	 */
	ExpedienteModel buscarExpedientePorCurpPorProcesoPorEstatusOrdenadoPorFechaOperacion(String curp,
			String claveProceso, String estatus);

}
