package co.edu.udistrital.rrhh.service;
import co.edu.udistrital.rrhh.domain.Concepto;

import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { Concepto.class })
public interface PrestacionService {

	Double calcularCesantias(Double sueldoEmpleado);

	Double calcularInteresesCesantias(Double valorCesantias);

	Double calcularPrima(Double sueldoEmpleado);
	
	Double calcularVacaciones(Double sueldoEmpleado);
	
}
