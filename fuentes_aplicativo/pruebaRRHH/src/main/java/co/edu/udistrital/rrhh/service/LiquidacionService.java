package co.edu.udistrital.rrhh.service;

import co.edu.udistrital.rrhh.domain.Empleado;
import co.edu.udistrital.rrhh.web.util.NominaException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { co.edu.udistrital.rrhh.domain.Empleado.class })
public interface LiquidacionService {

	public StringBuffer Liquidar(List<Empleado> allEmpleados, Calendar periodo) throws NominaException;
	
	
	public abstract void procesarPrima(Empleado empleado, Calendar periodo);
	
	
	public abstract void procesarCesantias(Empleado empleado, Calendar periodo);
	

	public abstract Double calcularTotalDeducciones(Integer cedulaEmpleado,
			Date periodo);

	
	public abstract Double calcularTotalDevengados(Empleado empleado,
			Date periodo);

	
	public Double calcularSalarioEmpleado(Empleado empleado, Date periodo);

	
	public void liquidaEmpleado(Empleado empleado, Date periodo);
	
	
	public void generarArchivoPlano(StringBuffer contenidoArchivo, Date periodo);
	
	
	public void saveConceptosLiq(List<Empleado> allEmpleadosWithPagos, Date periodo) throws NominaException;
	
	
	public List<Empleado> fillPagosEmpleado(List<Empleado> allEmpleados, Date periodo);
	
	
	public Calendar periodoLiquidacion();
}
