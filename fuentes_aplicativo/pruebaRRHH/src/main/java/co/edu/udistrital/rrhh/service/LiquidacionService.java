package co.edu.udistrital.rrhh.service;
import co.edu.udistrital.rrhh.domain.Empleado;

import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { co.edu.udistrital.rrhh.domain.Empleado.class })
public interface LiquidacionService {

	public abstract List<Empleado> findAllEmpleados();
	
	public abstract void Liquidar(List<Empleado> allEmpleados); 
	
	public abstract Double calcularTotalDeducciones(Integer cedulaEmpleado, String periodo);
	
	public abstract Double calcularTotalDevengados(Integer cedulaEmpleado, String periodo);
	
	public abstract Double calcularSalarioEmpleado(Integer cedulaEmpleado, String periodo);
	
	public abstract void procesarPrima(Integer cedulaEmpleado, String periodo);

}
