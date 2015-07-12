package co.edu.udistrital.rrhh.web;
import co.edu.udistrital.rrhh.domain.Empleado;
import co.edu.udistrital.rrhh.domain.Historicocargo;
import co.edu.udistrital.rrhh.service.EmpleadoService;
import co.edu.udistrital.rrhh.service.HistoricocargoService;
import co.edu.udistrital.rrhh.web.util.ComponentsGenerator;
import co.edu.udistrital.rrhh.web.util.Constantes;
import co.edu.udistrital.rrhh.web.util.MessageFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.validator.LengthValidator;

import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.message.Message;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.component.spinner.Spinner;
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

	private List<String> columns;

	private HtmlPanelGrid createPanelGrid;

	private HtmlPanelGrid editPanelGrid;

	private HtmlPanelGrid viewPanelGrid;

	private boolean createDialogVisible = false;
	
	private boolean insertAction = false;
	private boolean updateAction = false;
	
	private AutoComplete autocompleteCargo;
	private SelectOneMenu selectEstadoEmpleado;
	private Calendar fechaIngreso;

	@PostConstruct
    public void init() {
        columns = new ArrayList<String>();
        columns.add("empCedula");
        columns.add("empNombre");
        columns.add("empFechaIngreso");
        columns.add("empFechaSalida");
        columns.add("empCuentaPago");
        columns.add("empEstado");
    }

	public String getName() {
        return name;
    }

	public List<String> getColumns() {
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
        return null;
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

	public HtmlPanelGrid populateCreatePanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        // jcri 
        
        
        OutputLabel empCedulaCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        empCedulaCreateOutput.setFor("empCedulaCreateOutput");
        empCedulaCreateOutput.setId("empCedulaCreateOutput");
        empCedulaCreateOutput.setValue("Cedula:");
        htmlPanelGrid.getChildren().add(empCedulaCreateOutput);
        
        InputText empCedulaCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        empCedulaCreateInput.setId("empCedulaCreateInput");
        empCedulaCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{empleadoBean.empleado.empCedula}", String.class));
        empCedulaCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(empCedulaCreateInput);
        
        Message empCedulaCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        empCedulaCreateInputMessage.setId("empCedulaCreateInputMessage");
        empCedulaCreateInputMessage.setFor("empCedulaCreateInput");
        empCedulaCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(empCedulaCreateInputMessage);
        
        // jcri 
        
        OutputLabel empNombreCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        empNombreCreateOutput.setFor("empNombreCreateInput");
        empNombreCreateOutput.setId("empNombreCreateOutput");
        empNombreCreateOutput.setValue("Emp Nombre:");
        htmlPanelGrid.getChildren().add(empNombreCreateOutput);
        
        InputTextarea empNombreCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        empNombreCreateInput.setId("empNombreCreateInput");
        empNombreCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{empleadoBean.empleado.empNombre}", String.class));
        LengthValidator empNombreCreateInputValidator = new LengthValidator();
        empNombreCreateInputValidator.setMaximum(50);
        empNombreCreateInput.addValidator(empNombreCreateInputValidator);
        empNombreCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(empNombreCreateInput);
        
        Message empNombreCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        empNombreCreateInputMessage.setId("empNombreCreateInputMessage");
        empNombreCreateInputMessage.setFor("empNombreCreateInput");
        empNombreCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(empNombreCreateInputMessage);
        
        OutputLabel empFechaIngresoCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        empFechaIngresoCreateOutput.setFor("empFechaIngresoCreateInput");
        empFechaIngresoCreateOutput.setId("empFechaIngresoCreateOutput");
        empFechaIngresoCreateOutput.setValue("Emp Fecha Ingreso:");
        htmlPanelGrid.getChildren().add(empFechaIngresoCreateOutput);
        
        Calendar empFechaIngresoCreateInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        empFechaIngresoCreateInput.setId("empFechaIngresoCreateInput");
        empFechaIngresoCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{empleadoBean.empleado.empFechaIngreso}", Date.class));
        empFechaIngresoCreateInput.setNavigator(true);
        empFechaIngresoCreateInput.setEffect("slideDown");
        empFechaIngresoCreateInput.setPattern("dd/MM/yyyy");
        empFechaIngresoCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(empFechaIngresoCreateInput);
        
        Message empFechaIngresoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        empFechaIngresoCreateInputMessage.setId("empFechaIngresoCreateInputMessage");
        empFechaIngresoCreateInputMessage.setFor("empFechaIngresoCreateInput");
        empFechaIngresoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(empFechaIngresoCreateInputMessage);
        
        /*
        OutputLabel empFechaSalidaCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        empFechaSalidaCreateOutput.setFor("empFechaSalidaCreateInput");
        empFechaSalidaCreateOutput.setId("empFechaSalidaCreateOutput");
        empFechaSalidaCreateOutput.setValue("Emp Fecha Salida:");
        htmlPanelGrid.getChildren().add(empFechaSalidaCreateOutput);
        
        Calendar empFechaSalidaCreateInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        empFechaSalidaCreateInput.setId("empFechaSalidaCreateInput");
        empFechaSalidaCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{empleadoBean.empleado.empFechaSalida}", Date.class));
        empFechaSalidaCreateInput.setNavigator(true);
        empFechaSalidaCreateInput.setEffect("slideDown");
        empFechaSalidaCreateInput.setPattern("dd/MM/yyyy");
        empFechaSalidaCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(empFechaSalidaCreateInput);
        
        Message empFechaSalidaCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        empFechaSalidaCreateInputMessage.setId("empFechaSalidaCreateInputMessage");
        empFechaSalidaCreateInputMessage.setFor("empFechaSalidaCreateInput");
        empFechaSalidaCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(empFechaSalidaCreateInputMessage);
        */
        
        
        OutputLabel empCuentaPagoCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        empCuentaPagoCreateOutput.setFor("empCuentaPagoCreateInput");
        empCuentaPagoCreateOutput.setId("empCuentaPagoCreateOutput");
        empCuentaPagoCreateOutput.setValue("Emp Cuenta Pago:");
        htmlPanelGrid.getChildren().add(empCuentaPagoCreateOutput);
        
        Spinner empCuentaPagoCreateInput = (Spinner) application.createComponent(Spinner.COMPONENT_TYPE);
        empCuentaPagoCreateInput.setId("empCuentaPagoCreateInput");
        empCuentaPagoCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{empleadoBean.empleado.empCuentaPago}", Integer.class));
        empCuentaPagoCreateInput.setRequired(true);
        
        htmlPanelGrid.getChildren().add(empCuentaPagoCreateInput);
        
        Message empCuentaPagoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        empCuentaPagoCreateInputMessage.setId("empCuentaPagoCreateInputMessage");
        empCuentaPagoCreateInputMessage.setFor("empCuentaPagoCreateInput");
        empCuentaPagoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(empCuentaPagoCreateInputMessage);
        
        OutputLabel empEstadoCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        empEstadoCreateOutput.setFor("empEstadoCreateInput");
        empEstadoCreateOutput.setId("empEstadoCreateOutput");
        empEstadoCreateOutput.setValue("Emp Estado:");
        htmlPanelGrid.getChildren().add(empEstadoCreateOutput);
        
        InputText empEstadoCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        empEstadoCreateInput.setId("empEstadoCreateInput");
        empEstadoCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{empleadoBean.empleado.empEstado}", String.class));
        LengthValidator empEstadoCreateInputValidator = new LengthValidator();
        empEstadoCreateInputValidator.setMaximum(1);
        empEstadoCreateInput.addValidator(empEstadoCreateInputValidator);
        empEstadoCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(empEstadoCreateInput);
        
        Message empEstadoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        empEstadoCreateInputMessage.setId("empEstadoCreateInputMessage");
        empEstadoCreateInputMessage.setFor("empEstadoCreateInput");
        empEstadoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(empEstadoCreateInputMessage);
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateEditPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        OutputLabel empNombreEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        empNombreEditOutput.setFor("empNombreEditInput");
        empNombreEditOutput.setId("empNombreEditOutput");
        empNombreEditOutput.setValue("Emp Nombre:");
        htmlPanelGrid.getChildren().add(empNombreEditOutput);
        
        InputTextarea empNombreEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        empNombreEditInput.setId("empNombreEditInput");
        empNombreEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{empleadoBean.empleado.empNombre}", String.class));
        LengthValidator empNombreEditInputValidator = new LengthValidator();
        empNombreEditInputValidator.setMaximum(50);
        empNombreEditInput.addValidator(empNombreEditInputValidator);
        empNombreEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(empNombreEditInput);
        
        Message empNombreEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        empNombreEditInputMessage.setId("empNombreEditInputMessage");
        empNombreEditInputMessage.setFor("empNombreEditInput");
        empNombreEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(empNombreEditInputMessage);
        
        OutputLabel empFechaIngresoEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        empFechaIngresoEditOutput.setFor("empFechaIngresoEditInput");
        empFechaIngresoEditOutput.setId("empFechaIngresoEditOutput");
        empFechaIngresoEditOutput.setValue("Emp Fecha Ingreso:");
        htmlPanelGrid.getChildren().add(empFechaIngresoEditOutput);
        
        Calendar empFechaIngresoEditInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        empFechaIngresoEditInput.setId("empFechaIngresoEditInput");
        empFechaIngresoEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{empleadoBean.empleado.empFechaIngreso}", Date.class));
        empFechaIngresoEditInput.setNavigator(true);
        empFechaIngresoEditInput.setEffect("slideDown");
        empFechaIngresoEditInput.setPattern("dd/MM/yyyy");
        empFechaIngresoEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(empFechaIngresoEditInput);
        
        Message empFechaIngresoEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        empFechaIngresoEditInputMessage.setId("empFechaIngresoEditInputMessage");
        empFechaIngresoEditInputMessage.setFor("empFechaIngresoEditInput");
        empFechaIngresoEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(empFechaIngresoEditInputMessage);
        
        OutputLabel empFechaSalidaEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        empFechaSalidaEditOutput.setFor("empFechaSalidaEditInput");
        empFechaSalidaEditOutput.setId("empFechaSalidaEditOutput");
        empFechaSalidaEditOutput.setValue("Emp Fecha Salida:");
        htmlPanelGrid.getChildren().add(empFechaSalidaEditOutput);
        
        Calendar empFechaSalidaEditInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        empFechaSalidaEditInput.setId("empFechaSalidaEditInput");
        empFechaSalidaEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{empleadoBean.empleado.empFechaSalida}", Date.class));
        empFechaSalidaEditInput.setNavigator(true);
        empFechaSalidaEditInput.setEffect("slideDown");
        empFechaSalidaEditInput.setPattern("dd/MM/yyyy");
        empFechaSalidaEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(empFechaSalidaEditInput);
        
        Message empFechaSalidaEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        empFechaSalidaEditInputMessage.setId("empFechaSalidaEditInputMessage");
        empFechaSalidaEditInputMessage.setFor("empFechaSalidaEditInput");
        empFechaSalidaEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(empFechaSalidaEditInputMessage);
        
        OutputLabel empCuentaPagoEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        empCuentaPagoEditOutput.setFor("empCuentaPagoEditInput");
        empCuentaPagoEditOutput.setId("empCuentaPagoEditOutput");
        empCuentaPagoEditOutput.setValue("Emp Cuenta Pago:");
        htmlPanelGrid.getChildren().add(empCuentaPagoEditOutput);
        
        Spinner empCuentaPagoEditInput = (Spinner) application.createComponent(Spinner.COMPONENT_TYPE);
        empCuentaPagoEditInput.setId("empCuentaPagoEditInput");
        empCuentaPagoEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{empleadoBean.empleado.empCuentaPago}", Integer.class));
        empCuentaPagoEditInput.setRequired(true);
        
        htmlPanelGrid.getChildren().add(empCuentaPagoEditInput);
        
        Message empCuentaPagoEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        empCuentaPagoEditInputMessage.setId("empCuentaPagoEditInputMessage");
        empCuentaPagoEditInputMessage.setFor("empCuentaPagoEditInput");
        empCuentaPagoEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(empCuentaPagoEditInputMessage);
        
        OutputLabel empEstadoEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        empEstadoEditOutput.setFor("empEstadoEditInput");
        empEstadoEditOutput.setId("empEstadoEditOutput");
        empEstadoEditOutput.setValue("Emp Estado:");
        htmlPanelGrid.getChildren().add(empEstadoEditOutput);
        
        InputText empEstadoEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        empEstadoEditInput.setId("empEstadoEditInput");
        empEstadoEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{empleadoBean.empleado.empEstado}", String.class));
        LengthValidator empEstadoEditInputValidator = new LengthValidator();
        empEstadoEditInputValidator.setMaximum(1);
        empEstadoEditInput.addValidator(empEstadoEditInputValidator);
        empEstadoEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(empEstadoEditInput);
        
        Message empEstadoEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        empEstadoEditInputMessage.setId("empEstadoEditInputMessage");
        empEstadoEditInputMessage.setFor("empEstadoEditInput");
        empEstadoEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(empEstadoEditInputMessage);
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateViewPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText empNombreLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        empNombreLabel.setId("empNombreLabel");
        empNombreLabel.setValue("Emp Nombre:");
        htmlPanelGrid.getChildren().add(empNombreLabel);
        
        InputTextarea empNombreValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        empNombreValue.setId("empNombreValue");
        empNombreValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{empleadoBean.empleado.empNombre}", String.class));
        empNombreValue.setReadonly(true);
        empNombreValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(empNombreValue);
        
        HtmlOutputText empFechaIngresoLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        empFechaIngresoLabel.setId("empFechaIngresoLabel");
        empFechaIngresoLabel.setValue("Emp Fecha Ingreso:");
        htmlPanelGrid.getChildren().add(empFechaIngresoLabel);
        
        HtmlOutputText empFechaIngresoValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        empFechaIngresoValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{empleadoBean.empleado.empFechaIngreso}", Calendar.class));
        DateTimeConverter empFechaIngresoValueConverter = (DateTimeConverter) application.createConverter(DateTimeConverter.CONVERTER_ID);
        empFechaIngresoValueConverter.setPattern("dd/MM/yyyy");
        empFechaIngresoValue.setConverter(empFechaIngresoValueConverter);
        htmlPanelGrid.getChildren().add(empFechaIngresoValue);
        
        HtmlOutputText empFechaSalidaLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        empFechaSalidaLabel.setId("empFechaSalidaLabel");
        empFechaSalidaLabel.setValue("Emp Fecha Salida:");
        htmlPanelGrid.getChildren().add(empFechaSalidaLabel);
        
        HtmlOutputText empFechaSalidaValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        empFechaSalidaValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{empleadoBean.empleado.empFechaSalida}", Calendar.class));
        DateTimeConverter empFechaSalidaValueConverter = (DateTimeConverter) application.createConverter(DateTimeConverter.CONVERTER_ID);
        empFechaSalidaValueConverter.setPattern("dd/MM/yyyy");
        empFechaSalidaValue.setConverter(empFechaSalidaValueConverter);
        htmlPanelGrid.getChildren().add(empFechaSalidaValue);
        
        HtmlOutputText empCuentaPagoLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        empCuentaPagoLabel.setId("empCuentaPagoLabel");
        empCuentaPagoLabel.setValue("Emp Cuenta Pago:");
        htmlPanelGrid.getChildren().add(empCuentaPagoLabel);
        
        HtmlOutputText empCuentaPagoValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        empCuentaPagoValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{empleadoBean.empleado.empCuentaPago}", String.class));
        htmlPanelGrid.getChildren().add(empCuentaPagoValue);
        
        HtmlOutputText empEstadoLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        empEstadoLabel.setId("empEstadoLabel");
        empEstadoLabel.setValue("Emp Estado:");
        htmlPanelGrid.getChildren().add(empEstadoLabel);
        
        HtmlOutputText empEstadoValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        empEstadoValue.setId("empEstadoValue");
        empEstadoValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{empleadoBean.empleado.empEstado}", String.class));
        htmlPanelGrid.getChildren().add(empEstadoValue);
        
        return htmlPanelGrid;
    }

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
        	//inserta
            empleadoService.saveEmpleado(empleado);
            
            //inserta regiustro de historial cargo
            Historicocargo historialCargo=  new Historicocargo();
            historialCargo.setHisCargo(empleado.getCargo());
            historialCargo.setHisEmpleado(empleado);
            historialCargo.setHisEstado(Constantes.GENERAL_ESTADO_ACTIVO);
            historialCargo.setHisFechaInicio(new Date());

            historicocargoService.saveHistoricocargo(historialCargo);
            
            message = "message_successfully_updated";
        } else {
        	//actualiza
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
                        historialCargo.setHisFechaInicio(new Date());
            			
                        historicocargoService.saveHistoricocargo(historialCargo);
            		}
            	}
            }
            
            message = "message_successfully_created";
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




	private static final long serialVersionUID = 1L;
}
