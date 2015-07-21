package co.edu.udistrital.rrhh.service;
import java.util.Calendar;
import java.util.List;

import co.edu.udistrital.rrhh.domain.Concepto;
import co.edu.udistrital.rrhh.domain.Empleado;
import co.edu.udistrital.rrhh.web.util.NominaException;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { Concepto.class })
public interface PrestacionService {

	Double calcularCesantias(Double sueldoEmpleado);

	Double calcularInteresesCesantias(Double valorCesantias);

	Double calcularPrima(Double sueldoEmpleado);
	
	Double calcularVacaciones(Double sueldoEmpleado);

	StringBuffer liquidarPrestaciones(List<Empleado> allEmpleados,Calendar periodo) throws NominaException;
	
}
