package co.edu.udistrital.rrhh.web;
import co.edu.udistrital.rrhh.domain.Empleado;
import co.edu.udistrital.rrhh.domain.Historicocargo;
import co.edu.udistrital.rrhh.service.EmpleadoService;
import co.edu.udistrital.rrhh.service.HistoricocargoService;
import co.edu.udistrital.rrhh.web.util.CampoValor;
import co.edu.udistrital.rrhh.web.util.ComponentsGenerator;
import co.edu.udistrital.rrhh.web.util.Constantes;
import co.edu.udistrital.rrhh.web.util.MessageFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;

import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@ManagedBean(name = "empleadoBean")
@SessionScoped
@Configurable
@RooSerializable
@RooJsfManagedBean(entity = Empleado.class, beanName = "empleadoBean")
public class EmpleadoBean implements Serializable{

	@Autowired
    EmpleadoService empleadoService;
	
	@Autowired
	HistoricocargoService historicocargoService;

	private String name = "Empleados";

	private Empleado empleado;

	private List<Empleado> allEmpleadoes;

	private boolean dataVisible = false;

	private List<CampoValor> columns;

	private HtmlPanelGrid createPanelGrid;

	private HtmlPanelGrid editPanelGrid;

	private HtmlPanelGrid viewPanelGrid;

	private boolean createDialogVisible = false;
	
	private boolean insertAction = false;
	private boolean updateAction = false;
	
	private AutoComplete autocompleteCargo;
	private SelectOneMenu selectEstadoEmpleado;
	private Calendar fechaIngreso;
	
	private AutoComplete autocompleteSalud;
	private AutoComplete autocompletePension;
	private AutoComplete autocompleteArl;
	private AutoComplete autocompleteCesantias;
	private AutoComplete autocompleteCaja;
	

	@PostConstruct
    public void init() {
        columns = new ArrayList<CampoValor>();
        columns.add(new CampoValor("Cedula", "empCedula"));
        columns.add(new CampoValor("Nombre", "empNombre"));
        columns.add(new CampoValor("Fecha Ingreso", "formatedFechaIngreso"));
        columns.add(new CampoValor("Cargo", "cargoNombre"));
        columns.add(new CampoValor("Nro. Cuenta", "empCuentaPago"));
        
        
    }

	public String getName() {
        return name;
    }

	public List<CampoValor> getColumns() {
        return columns;
    }

	public List<Empleado> getAllEmpleadoes() {
        return allEmpleadoes;
    }

	public void setAllEmpleadoes(List<Empleado> allEmpleadoes) {
        this.allEmpleadoes = allEmpleadoes;
    }

	public String findAllEmpleadoes() {
       // allEmpleadoes = empleadoService.findAllEmpleadoes();
        allEmpleadoes = empleadoService.findAllEmpleadosAct(Constantes.ESTADO_EMPL_ACTIVO);
        dataVisible = !allEmpleadoes.isEmpty();
        return "empleado";
    }

	public boolean isDataVisible() {
        return dataVisible;
    }

	public void setDataVisible(boolean dataVisible) {
        this.dataVisible = dataVisible;
    }

	public HtmlPanelGrid getCreatePanelGrid() {
        if (createPanelGrid == null) {
            createPanelGrid = populateCreatePanel();
        }
        return createPanelGrid;
    }

	public void setCreatePanelGrid(HtmlPanelGrid createPanelGrid) {
        this.createPanelGrid = createPanelGrid;
    }

	public HtmlPanelGrid getEditPanelGrid() {
        if (editPanelGrid == null) {
            editPanelGrid = populateEditPanel();
        }
        return editPanelGrid;
    }

	public void setEditPanelGrid(HtmlPanelGrid editPanelGrid) {
        this.editPanelGrid = editPanelGrid;
    }

	public HtmlPanelGrid getViewPanelGrid() {
        return populateViewPanel();
    }

	public void setViewPanelGrid(HtmlPanelGrid viewPanelGrid) {
        this.viewPanelGrid = viewPanelGrid;
    }

	public HtmlPanelGrid populateCreatePanel() {return null;}

	public HtmlPanelGrid populateEditPanel() {return null;}

	public HtmlPanelGrid populateViewPanel() {return null;}

	public Empleado getEmpleado() {
        if (empleado == null) {
            empleado = new Empleado();
        }
        return empleado;
    }

	public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

	public String onEdit() {
		
        insertAction = false;
        updateAction = true;
		
        return null;
    }

	public boolean isCreateDialogVisible() {
        return createDialogVisible;
    }

	public void setCreateDialogVisible(boolean createDialogVisible) {
        this.createDialogVisible = createDialogVisible;
    }

	public AutoComplete getAutocompleteCargo() {
		
		if(autocompleteCargo ==null){
			
			autocompleteCargo =  ComponentsGenerator.getAutocompleteCargo("autoCompleteCargoInput", "#{empleadoBean.empleado.cargo}");
			
		}
		
		return autocompleteCargo;
	}

	public void setAutocompleteCargo(AutoComplete autocompleteCargo) {
		this.autocompleteCargo = autocompleteCargo;
	}

	public String displayList() {
        createDialogVisible = false;
        findAllEmpleadoes();
        return "empleado";
    }

	public String displayCreateDialog() {
        empleado = new Empleado();
        insertAction = true;
        updateAction = false;
        
        createDialogVisible = true;
        return "empleado";
    }

	
	
	public String persist() {
        String message = "";
        
        
        //insercion historico cargo
        
        //acatualizacionhistorico cargo
        
        if (insertAction) {
<<<<<<< HEAD

        	if(empleadoService.findEmpleado(empleado.getEmpCedula())!= null){

        		message = "El empleado que esta intentando insertar ya existe.";

        	}else{

        		//inserta
        		empleado.setEmpEstado(Constantes.GENERAL_ESTADO_ACTIVO);
        		empleadoService.saveEmpleado(empleado);

        		//inserta regiustro de historial cargo
        		Historicocargo historialCargo=  new Historicocargo();
        		historialCargo.setHisCargo(empleado.getCargo());
        		historialCargo.setHisEmpleado(empleado);
        		historialCargo.setHisEstado(Constantes.GENERAL_ESTADO_ACTIVO);
        		historialCargo.setHisFechaInicio(new Date());

        		historicocargoService.saveHistoricocargo(historialCargo);

        		message = "message_successfully_created";
        	}
=======
        	//inserta
        	empleado.setEmpEstado(Constantes.GENERAL_ESTADO_ACTIVO);
            empleadoService.saveEmpleado(empleado);
            
            //inserta regiustro de historial cargo
            Historicocargo historialCargo=  new Historicocargo();
            historialCargo.setHisCargo(empleado.getCargo());
            historialCargo.setHisEmpleado(empleado);
            historialCargo.setHisEstado(Constantes.GENERAL_ESTADO_ACTIVO);
            historialCargo.setHisFechaInicio(((Date)fechaIngreso.getAttributes().get("value")));

            historicocargoService.saveHistoricocargo(historialCargo);
            
            message = "message_successfully_created";
>>>>>>> origin/master
        } else {
        	//actualiza
        	empleado.setEmpEstado(Constantes.GENERAL_ESTADO_ACTIVO);
            empleadoService.updateEmpleado(empleado);
            
            
            for(Historicocargo histAux :  empleado.getHistoricoCargos()){            	
            	if(histAux.getHisFechaFin() == null){
            		
            		if(histAux.getHisCargo().getCarCogigo().intValue() != empleado.getCargo().getCarCogigo().intValue()){
            			
            			histAux.setHisFechaFin(new Date());
            			histAux.setHisEstado(Constantes.GENERAL_ESTADO_INACTIVO);
            			historicocargoService.updateHistoricocargo(histAux);
            			
            			Historicocargo historialCargo=  new Historicocargo();
                        historialCargo.setHisCargo(empleado.getCargo());
                        historialCargo.setHisEmpleado(empleado);
                        historialCargo.setHisEstado(Constantes.GENERAL_ESTADO_ACTIVO);
                        historialCargo.setHisFechaInicio(((Date)fechaIngreso.getAttributes().get("value")));
            			
                        historicocargoService.saveHistoricocargo(historialCargo);
            		}
            	}
            }
            
            message = "message_successfully_updated";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialogWidget.hide()");
        context.execute("editDialogWidget.hide()");
        
        FacesMessage facesMessage = MessageFactory.getMessage(message, "Empleado");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllEmpleadoes();
    }

	public String delete() {
        // empleadoService.deleteEmpleado(empleado);
        empleadoService.ActEstado(empleado); // Actualiza el estado a 'Inactivo'
        FacesMessage facesMessage = MessageFactory.getMessage("message_successfully_deleted", "Empleado");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllEmpleadoes();
    }

	public void reset() {
        empleado = null;
        createDialogVisible = false;
    }

	public void handleDialogClose(CloseEvent event) {
        reset();
    }

	
	public SelectOneMenu getSelectEstadoEmpleado() {
		if(selectEstadoEmpleado == null){
			
			selectEstadoEmpleado=ComponentsGenerator.getAutocompleteEstadoActual("SelectEstadoEmp", "#{empleadoBean.empleado.empEstado}");
		}
		
		return selectEstadoEmpleado;
	}

	public void setSelectEstadoEmpleado(SelectOneMenu selectEstadoEmpleado) {
		this.selectEstadoEmpleado = selectEstadoEmpleado;
	}

	public Calendar getFechaIngreso() {
		if(fechaIngreso==null){
			
			fechaIngreso = ComponentsGenerator.getBasicCalendar("calendarFecIngEmpleado", "#{empleadoBean.empleado.empFechaIngreso}");
		}
		
		return fechaIngreso;
	}

	public void setFechaIngreso(Calendar fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}




	public AutoComplete getAutocompleteSalud() {
		
		if(autocompleteSalud == null){
			autocompleteSalud=ComponentsGenerator.getAutocompleteEntidad("autoCompleteSaludInput", "#{empleadoBean.empleado.entidadSalud}", Constantes.TIPO_ENTIDAD_SALUD);
		}
		
		return autocompleteSalud;
	}

	public void setAutocompleteSalud(AutoComplete autocompleteSalud) {
		this.autocompleteSalud = autocompleteSalud;
	}

	public AutoComplete getAutocompletePension() {
		
		if(autocompletePension == null){
			autocompletePension=ComponentsGenerator.getAutocompleteEntidad("autoCompletePensionInput", "#{empleadoBean.empleado.entidadPension}", Constantes.TIPO_ENTIDAD_PENSION);
		}
		return autocompletePension;
	}

	public void setAutocompletePension(AutoComplete autocompletePension) {
		this.autocompletePension = autocompletePension;
	}

	public AutoComplete getAutocompleteArl() {
		
		if(autocompleteArl == null){
			autocompleteArl=ComponentsGenerator.getAutocompleteEntidad("autoCompleteArlInput", "#{empleadoBean.empleado.entidadArp}", Constantes.TIPO_ENTIDAD_ARL);
		}
		
		return autocompleteArl;
	}

	public void setAutocompleteArl(AutoComplete autocompleteArl) {
		this.autocompleteArl = autocompleteArl;
	}

	public AutoComplete getAutocompleteCesantias() {
		
		if(autocompleteCesantias == null){
			autocompleteCesantias=ComponentsGenerator.getAutocompleteEntidad("autoCompleteCesantiasInput", "#{empleadoBean.empleado.entidadCesantias}", Constantes.TIPO_ENTIDAD_CESANTIAS);
		}
		
		return autocompleteCesantias;
	}

	public void setAutocompleteCesantias(AutoComplete autocompleteCesantias) {
		this.autocompleteCesantias = autocompleteCesantias;
	}

	public AutoComplete getAutocompleteCaja() {
		
		if(autocompleteCaja == null){
			autocompleteCaja=ComponentsGenerator.getAutocompleteEntidad("autoCompleteCCInput", "#{empleadoBean.empleado.entidadCajaCompensacion}", Constantes.TIPO_ENTIDAD_CAJA_COMPENSACION);
		}
		
		return autocompleteCaja;
	}

	public void setAutocompleteCaja(AutoComplete autocompleteCaja) {
		this.autocompleteCaja = autocompleteCaja;
	}




	public boolean isInsertAction() {
		return insertAction;
	}

	public void setInsertAction(boolean insertAction) {
		this.insertAction = insertAction;
	}

	public boolean isUpdateAction() {
		return updateAction;
	}

	public void setUpdateAction(boolean updateAction) {
		this.updateAction = updateAction;
	}




	private static final long serialVersionUID = 1L;
}
