package co.edu.udistrital.rrhh.service;
import java.util.Calendar;
import java.util.List;

import co.edu.udistrital.rrhh.domain.Concepto;
import co.edu.udistrital.rrhh.domain.Empleado;
import co.edu.udistrital.rrhh.web.util.NominaException;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { Concepto.class })
public interface PrestacionService {

	StringBuffer liquidarPrestaciones(List<Empleado> allEmpleados,Calendar periodo) throws NominaException;
	
	Double[] calcularConcepto(Double sueldoEmpleado, Concepto concepto);

	List<Concepto> getAllConceptosProvisionables(List<Concepto> listaConceptosProvisionables);

	List<Concepto> getAllConceptosAporte(List<Concepto> listaConceptosAporte);
	
}
