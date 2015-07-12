package co.edu.udistrital.rrhh.service;

import co.edu.udistrital.rrhh.domain.Concepto;
import co.edu.udistrital.rrhh.domain.Empleado;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { co.edu.udistrital.rrhh.domain.Empleado.class })
public interface LiquidacionService {

	public abstract void Liquidar(List<Empleado> allEmpleados, Calendar periodo);
	
	
	public abstract void procesarPrima(Empleado empleado, Calendar periodo);
	
	
	public abstract void procesarCesantias(Empleado empleado, Calendar periodo);
	

	public abstract Double calcularTotalDeducciones(Integer cedulaEmpleado,
			Date periodo);

	
	public abstract Double calcularTotalDevengados(Empleado empleado,
			Date periodo);

	
	public Double calcularSalarioEmpleado(Empleado empleado, Date periodo);
	

	public void realizarPago(Concepto concepto, String estado,
			Empleado empleado, Date periodo, Double valorPago);
		
	
	public Integer recuperarAfiliacion(Integer empleado, String tipoEntidad);
	
	
	public abstract void realizarAporte(Integer entidad, String tipo, Date periodo,
			Double valor);
	
	
	public void liquidaEmpleado(Empleado empleado, Date periodo);
}
