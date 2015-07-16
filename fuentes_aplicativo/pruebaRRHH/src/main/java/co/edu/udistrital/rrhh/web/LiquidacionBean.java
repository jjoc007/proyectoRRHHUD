package co.edu.udistrital.rrhh.web;

import co.edu.udistrital.rrhh.domain.Empleado;
import co.edu.udistrital.rrhh.service.EmpleadoService;
import co.edu.udistrital.rrhh.service.LiquidacionService;
import co.edu.udistrital.rrhh.web.util.Constantes;
import co.edu.udistrital.rrhh.web.util.NominaException;
import co.edu.udistrital.rrhh.web.util.Utilidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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
		obtenerPeriodo();
	}

	private List<Empleado> allEmpleados;

	public List<Empleado> getAllEmpleados() {
		return allEmpleados;
	}

	public void setAllEmpleados(List<Empleado> allEmpleados) {
		this.allEmpleados = allEmpleados;
	}

	public String displayList() {
		findAllEmpleados();
		return "liquidacion";
	}

	public String findAllEmpleados() {
		allEmpleados = empleadoService.findAllEmpleadosAct(Constantes.ESTADO_EMPL_ACTIVO);
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

	public void liquidar(){
		
		try {
			
			obtenerPeriodo();
			 
			System.out.println("calendario " + periodo.getTime());

			liquidacionService.Liquidar(allEmpleados, periodo);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Proceso terminado con éxito."));

		} catch (NominaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", e.getMessage()));
			
		}
	}

	public Calendar obtenerPeriodo() {
		periodo = liquidacionService.periodoLiquidacion();
		System.out.println("aqui en periodo :"+periodo.getTime());
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
