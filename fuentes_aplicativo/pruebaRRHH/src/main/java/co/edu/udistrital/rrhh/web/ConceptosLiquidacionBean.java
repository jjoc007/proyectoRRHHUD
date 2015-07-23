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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.serializable.RooSerializable;

@ManagedBean(name = "conceptosLiquidacionBean")
@SessionScoped
@Configurable
@RooSerializable
public class ConceptosLiquidacionBean implements Serializable  {


	private boolean dataVisible = false;
	
	@Autowired
    EmpleadoService empleadoService;
	
	@Autowired
	LiquidacionService liquidacionService; 
	
	private List<Empleado> allEmpleados;
	private List<Empleado> allEmpleadosWithPagos;
	public Calendar periodo;
	public String periodoLiquidar;
	
	
	private boolean createDialogVisible = false;

	@PostConstruct
    public void init() {
		obtenerPeriodo();
		findAllEmpleadoes();
    }
	
	
	public String findAllEmpleadoes() {
        allEmpleados = empleadoService.findAllEmpleadosAct(Constantes.ESTADO_EMPL_ACTIVO);
        fillPagosEmpleado();
        dataVisible = !allEmpleados.isEmpty();
        return null;
    }
	

	public void fillPagosEmpleado(){
			
		allEmpleadosWithPagos = liquidacionService.fillPagosEmpleado(allEmpleados, periodo.getTime());
			
	}
	
	public void saveConceptosLiq(){
		
		try {
			
			obtenerPeriodo();
			liquidacionService.saveConceptosLiq(allEmpleadosWithPagos, periodo.getTime(),Constantes.GENERAL_ESTADO_ACTIVO);
			reset();
			
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Proceso terminado con éxito."));
			
		} catch (NominaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", e.getMessage()));
		}				
	
	}
	
	public boolean isDataVisible() {
        return dataVisible;
    }

	public void setDataVisible(boolean dataVisible) {
        this.dataVisible = dataVisible;
    }

	public String onEdit() {
        return null;
    }

	public boolean isCreateDialogVisible() {
        return createDialogVisible;
    }

	public void setCreateDialogVisible(boolean createDialogVisible) {
        this.createDialogVisible = createDialogVisible;
    }

	public String displayList() {
        createDialogVisible = false;
        return "conceptosLiquidacion";
    }

	public String displayCreateDialog() {
        createDialogVisible = true;
        return "conceptosLiquidacion";
    }

	public void reset() {
		
		allEmpleadosWithPagos= new ArrayList<Empleado>();
        fillPagosEmpleado();
    }

	public void handleDialogClose(CloseEvent event) {
        reset();
    }
	
	public List<Empleado> getAllEmpleados() {
		return allEmpleados;
	}


	public void setAllEmpleados(List<Empleado> allEmpleadoes) {
		this.allEmpleados = allEmpleadoes;
	}

	public List<Empleado> getAllEmpleadosWithPagos() {
		return allEmpleadosWithPagos;
	}


	public void setAllEmpleadosWithPagos(List<Empleado> allEmpleadosWithPagos) {
		this.allEmpleadosWithPagos = allEmpleadosWithPagos;
	}
	
	public Calendar obtenerPeriodo() {
		periodo = liquidacionService.periodoLiquidacion();
		return periodo;
	}


	public String getPeriodoLiquidar() {
		periodoLiquidar = Utilidades.dateFormat(periodo.getTime());
		return periodoLiquidar;
	}

	public void setPeriodoLiquidar(String periodoLiquidar) {
		this.periodoLiquidar = periodoLiquidar;
	}

	private static final long serialVersionUID = 1L;
	
	public void onCellEdit(CellEditEvent event){
		
		//guardar parcialmente los registros

		try {
			
			obtenerPeriodo();
			liquidacionService.saveConceptosLiq(allEmpleadosWithPagos, periodo.getTime(),Constantes.GENERAL_ESTADO_TEMPORAL);
						
		} catch (NominaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", e.getMessage()));
		}
		
	}
	
}
