package co.edu.udistrital.rrhh.web;


import co.edu.udistrital.rrhh.domain.Empleado;
import co.edu.udistrital.rrhh.service.EmpleadoService;
import co.edu.udistrital.rrhh.service.LiquidacionService;
import co.edu.udistrital.rrhh.service.PagoService;
import co.edu.udistrital.rrhh.web.util.Constantes;
import co.edu.udistrital.rrhh.web.util.Utilidades;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@ManagedBean(name = "liquidacionBean")
@SessionScoped
@Configurable
@RooSerializable
@RooJsfManagedBean(entity = Empleado.class, beanName = "liquidacionBean")
public class LiquidacionBean implements Serializable {

	@Autowired
	EmpleadoService empleadoService;
	
	@Autowired
	LiquidacionService liquidacionService;
	
	@Autowired
	PagoService pagoService;

	private List<String> columns;
	public Calendar periodo;
	public String periodoLiquidar;

	@PostConstruct
	public void init() {
		columns = new ArrayList<String>();
		columns.add("empCedula");
		columns.add("empNombre");
		columns.add("emp_vacaciones");
		columns.add("emp_liquida");
	}

	private List<Empleado> allEmpleados;

	public List<Empleado> getAllEmpleados() {
		return allEmpleados;
	}

	public void setAllEmpleados(List<Empleado> allEmpleados) {
		this.allEmpleados = allEmpleados;
	}

	public String displayList() {
		// createDialogVisible = false;
		findAllEmpleados();
		obtenerPeriodo();
		return "liquidacion";
	}

	public String findAllEmpleados() {
		allEmpleados = empleadoService
				.findAllEmpleadosAct(Constantes.ESTADO_EMPL_ACTIVO);
		// dataVisible = !allEmpleadoes.isEmpty();
		return null;
	}

	public void reset() {

	}

	public void handleDialogClose(CloseEvent event) {
		reset();
	}

	private static final long serialVersionUID = 1L;

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	public void liquidar() throws ParseException {

		//Calendar periodo = pagoService.traerPeriodoActualPago();
		//periodo.set(2015, 1, 1, 0, 0, 0);

		System.out.println("calendario " + periodo.getTime());

		liquidacionService.Liquidar(allEmpleados, periodo);
	}
	
	public Calendar obtenerPeriodo() {
		periodo = pagoService.traerPeriodoActualPago();
		return periodo;
	}


	public String getPeriodoLiquidar() {
		periodoLiquidar = Utilidades.dateFormat(periodo.getTime());
		return periodoLiquidar;
	}

	public void setPeriodoLiquidar(String periodoLiquidar) {
		this.periodoLiquidar = periodoLiquidar;
	}

	
}
