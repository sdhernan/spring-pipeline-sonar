package net.renfei.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.renfei.demo.persistencia.entidades.Expediente;
import net.renfei.demo.persistencia.modelos.ExpedienteModel;
import net.renfei.demo.persistencia.repositorios.ExpedienteRepository;
import net.renfei.demo.servicios.impl.ExpedienteServiceImpl;

/**
 * The Class ExpedienteServiceTest.
 */
class ExpedienteServiceTest {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ExpedienteServiceTest.class);

	/** The expediente service impl. */
	@InjectMocks
	private ExpedienteServiceImpl expedienteServiceImpl;

	/** The expediente repository. */
	@Mock
	private ExpedienteRepository expedienteRepository;

	/**
	 * Before setup.
	 */
	@BeforeEach
	void beforeSetup() {
		MockitoAnnotations.openMocks(this);
	}

	/**
	 * Expediente test 01.
	 */
	@Test
	void expedienteTest_01() {

		try {
			String curp = "HEMS911218HDFRRD06";
			String claveProceso = "01";
			String estatus = "5";

			List<Expediente> expedientes = new ArrayList<>();

			Expediente expediente1 = new Expediente();
			expediente1.setIdExpediente(1L);
			expediente1.setCurp(curp);
			expediente1.setFechaOperacion("20240501");
			expedientes.add(expediente1);

			Expediente expediente2 = new Expediente();
			expediente2.setIdExpediente(1L);
			expediente2.setCurp(curp);
			expediente2.setFechaOperacion("20230501");

			expedientes.add(expediente2);

			when(expedienteRepository.findByCurpAndClaveTipoProcesoAndClaveEstatusExpe(curp, claveProceso, estatus))
					.thenReturn(expedientes);

			ExpedienteModel respuesta = expedienteServiceImpl
					.buscarExpedientePorCurpPorProcesoPorEstatusOrdenadoPorFechaOperacion(curp, claveProceso, estatus);

			assertNotNull(respuesta);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * Expediente test 02.
	 */
	@Test
	void expedienteTest_02() {

		try {
			String curp = "HEMS911218HDFRRD06";
			String claveProceso = "01";
			String estatus = "5";

			List<Expediente> expedientes = new ArrayList<>();

			Expediente expediente1 = new Expediente();
			expediente1.setIdExpediente(1L);
			expediente1.setCurp(curp);
			expediente1.setFechaOperacion("20240501");
			expedientes.add(expediente1);

			Expediente expediente2 = new Expediente();
			expediente2.setIdExpediente(1L);
			expediente2.setCurp(curp);
			expediente2.setFechaOperacion("0501");

			expedientes.add(expediente2);

			when(expedienteRepository.findByCurpAndClaveTipoProcesoAndClaveEstatusExpe(curp, claveProceso, estatus))
					.thenReturn(expedientes);

			ExpedienteModel respuesta = expedienteServiceImpl
					.buscarExpedientePorCurpPorProcesoPorEstatusOrdenadoPorFechaOperacion(curp, claveProceso, estatus);

			assertNotNull(respuesta);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * Expediente test 03.
	 */
	@Test
	void expedienteTest_03() {

		try {
			String curp = "HEMS911218HDFRRD06";
			String claveProceso = "01";
			String estatus = "5";

			List<Expediente> expedientes = new ArrayList<>();

			Expediente expediente1 = new Expediente();
			expediente1.setIdExpediente(1L);
			expediente1.setCurp(curp);
			expediente1.setFechaOperacion("");
			expedientes.add(expediente1);

			Expediente expediente2 = new Expediente();
			expediente2.setIdExpediente(1L);
			expediente2.setCurp(curp);
			expediente2.setFechaOperacion(null);

			expedientes.add(expediente2);

			when(expedienteRepository.findByCurpAndClaveTipoProcesoAndClaveEstatusExpe(curp, claveProceso, estatus))
					.thenReturn(expedientes);

			ExpedienteModel respuesta = expedienteServiceImpl
					.buscarExpedientePorCurpPorProcesoPorEstatusOrdenadoPorFechaOperacion(curp, claveProceso, estatus);
			assertNotNull(respuesta);
		} catch (Exception e) {
			LOGGER.error("Error ", e);
		}

	}

}
