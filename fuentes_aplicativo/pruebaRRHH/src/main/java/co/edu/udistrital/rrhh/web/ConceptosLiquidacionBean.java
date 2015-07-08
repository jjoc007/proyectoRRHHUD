package co.edu.udistrital.rrhh.web;
import co.edu.udistrital.rrhh.domain.Afiliacion;
import co.edu.udistrital.rrhh.domain.Concepto;
import co.edu.udistrital.rrhh.domain.Empleado;
import co.edu.udistrital.rrhh.domain.Pago;
import co.edu.udistrital.rrhh.service.AfiliacionService;
import co.edu.udistrital.rrhh.service.ConceptoService;
import co.edu.udistrital.rrhh.service.EmpleadoService;
import co.edu.udistrital.rrhh.web.util.Constantes;
import co.edu.udistrital.rrhh.web.util.MessageFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@ManagedBean(name = "conceptosLiquidacionBean")
@ViewScoped
@Configurable
@RooSerializable
public class ConceptosLiquidacionBean implements Serializable  {


	private boolean dataVisible = false;
	
	@Autowired
    EmpleadoService empleadoService;
	
	@Autowired
	ConceptoService conceptoService; 
	
	private List<Empleado> allEmpleadoes;
	private List<Empleado> allEmpleadosWithPagos;
	
	
	private boolean createDialogVisible = false;

	@PostConstruct
    public void init() {
		findAllEmpleadoes();
    }
	
	
	public String findAllEmpleadoes() {
        allEmpleadoes = empleadoService.findAllEmpleadoes();
        fillPagosEmpleado();
        dataVisible = !allEmpleadoes.isEmpty();
        return null;
    }
	

	public void fillPagosEmpleado(){
		
		
		//busca todos los conceptos que sean devengados y deducidos
		List<Concepto> conceptos =  new ArrayList<Concepto>();
		allEmpleadosWithPagos = new ArrayList<Empleado>();
		conceptos.addAll(conceptoService.findByTipoPer(Constantes.TIPO_CONCEPTO_DEVENGO));
		conceptos.addAll(conceptoService.findByTipoPer(Constantes.TIPO_CONCEPTO_DEDUCIDO));
		
		System.out.println("cantidad de conceptos: "+conceptos.size());
		
		for (Empleado empleadoAux: allEmpleadoes){

			List<Pago> pagosPorEmpleado =  new ArrayList<Pago>();
			
			for(Concepto conceptoAux: conceptos){

				pagosPorEmpleado.add(new Pago(empleadoAux, conceptoAux, new Date(),	0.0D, "P", null));
				
			}
			
			empleadoAux.setPagos(pagosPorEmpleado);
			System.out.println("cantidad pagos: "+pagosPorEmpleado.size());
			allEmpleadosWithPagos.add(empleadoAux);
		}
		
		System.out.println("!!!");
		
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
        createDialogVisible = false;
    }

	public void handleDialogClose(CloseEvent event) {
        reset();
    }

	
	
	public List<Empleado> getAllEmpleadoes() {
		return allEmpleadoes;
	}


	public void setAllEmpleadoes(List<Empleado> allEmpleadoes) {
		this.allEmpleadoes = allEmpleadoes;
	}

	public List<Empleado> getAllEmpleadosWithPagos() {
		return allEmpleadosWithPagos;
	}


	public void setAllEmpleadosWithPagos(List<Empleado> allEmpleadosWithPagos) {
		this.allEmpleadosWithPagos = allEmpleadosWithPagos;
	}




	private static final long serialVersionUID = 1L;
}
