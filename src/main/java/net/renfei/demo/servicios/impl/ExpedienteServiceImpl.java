package net.renfei.demo.servicios.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.renfei.demo.persistencia.entidades.Expediente;
import net.renfei.demo.persistencia.modelos.ExpedienteModel;
import net.renfei.demo.persistencia.repositorios.ExpedienteRepository;
import net.renfei.demo.servicios.ExpedienteService;
import net.renfei.demo.servicios.utilerias.MapperUtils;

@Service
public class ExpedienteServiceImpl implements ExpedienteService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExpedienteServiceImpl.class);

	@Autowired
	private ExpedienteRepository expedienteRepository;

	@Override
	public ExpedienteModel buscarExpedientePorCurpPorProcesoPorEstatusOrdenadoPorFechaOperacion(String curp,
			String claveProceso, String estatus) {
		LOGGER.info(
				"ExpedienteServiceImpl > buscarExpedientePorCurpPorProcesoPorEstatusOrdenadoPorFechaOperacion > curp: {} claveProceso: {} estatus: {}",
				curp, claveProceso, estatus);

		List<Expediente> expediente = expedienteRepository.findByCurpAndClaveTipoProcesoAndClaveEstatusExpe(curp,
				claveProceso, estatus);

		// Procesa la lista de expedientes para obtener el más reciente con fecha válida
		return expediente.stream()
				// Filtra solo objetos del tipo Expediente que tengan fechaOperacion válida (no
				// nula ni vacía)
				.filter(exp -> exp instanceof Expediente && exp.getFechaOperacion() != null
						&& !exp.getFechaOperacion().isEmpty())
				// Ordena los expedientes por fechaOperacion
				.sorted(Comparator.comparing((Expediente exp) -> {
					try {
						// Convierte el string de fecha (formato yyyyMMdd) a un objeto LocalDate
						// usando la zona horaria de Ciudad de México para interpretación correcta
						return LocalDate.parse(exp.getFechaOperacion(),
								DateTimeFormatter.ofPattern("yyyyMMdd").withZone(ZoneId.of("America/Mexico_City")));
					} catch (DateTimeParseException ex) {
						// Si hay error en el formato de fecha, asigna la fecha mínima posible
						// para que estos registros aparezcan al final de la ordenación
						return LocalDate.MIN;
					}
				}).reversed()) // Invierte el orden para tener fechas más recientes primero
				// Obtiene el primer elemento del stream (el expediente con fecha más reciente)
				.findFirst()
				// Convierte el objeto Expediente a ExpedienteModel usando el mapper
				.map(exp -> MapperUtils.map(exp, ExpedienteModel.class))
				// Si no encuentra ningún expediente válido, devuelve un nuevo objeto
				// ExpedienteModel vacío
				.orElse(new ExpedienteModel());

	}

}
