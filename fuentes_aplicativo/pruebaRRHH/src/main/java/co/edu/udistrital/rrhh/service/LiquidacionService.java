package co.edu.udistrital.rrhh.service;

import co.edu.udistrital.rrhh.domain.Empleado;

import java.util.Calendar;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { co.edu.udistrital.rrhh.domain.Empleado.class })
public interface LiquidacionService {

	public abstract void Liquidar(List<Empleado> allEmpleados, Calendar periodo);

	public abstract Double calcularTotalDeducciones(Integer cedulaEmpleado,
			Calendar periodo);

	public abstract Double calcularTotalDevengados(Empleado empleado,
			Calendar periodo);

	public abstract void procesarPrima(Empleado empleado, Calendar periodo);

	public void procesarCesantias(Empleado empleado, Calendar periodo);

}
