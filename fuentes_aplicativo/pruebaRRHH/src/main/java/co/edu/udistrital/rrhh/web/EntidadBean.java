package co.edu.udistrital.rrhh.web;
import co.edu.udistrital.rrhh.domain.Entidad;
import co.edu.udistrital.rrhh.service.EntidadService;
import co.edu.udistrital.rrhh.web.util.ComponentsGenerator;
import co.edu.udistrital.rrhh.web.util.MessageFactory;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.faces.validator.LengthValidator;

import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.message.Message;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.spinner.Spinner;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@ManagedBean(name = "entidadBean")
@SessionScoped
@Configurable
@RooSerializable
@RooJsfManagedBean(entity = Entidad.class, beanName = "entidadBean")
public class EntidadBean implements Serializable{

	@Autowired
    EntidadService entidadService;

	private String name = "Entidades";

	private Entidad entidad;

	private List<Entidad> allEntidads;

	private boolean dataVisible = false;

	private List<String> columns;

	private HtmlPanelGrid createPanelGrid;

	private HtmlPanelGrid editPanelGrid;

	private HtmlPanelGrid viewPanelGrid;

	private boolean createDialogVisible = false;

	@PostConstruct
    public void init() {
        columns = new ArrayList<String>();
        columns.add("entNombre");
        columns.add("entCuenta");
        columns.add("entAporteEmpleado");
        columns.add("entAporteEmpresa");
    }

	public String getName() {
        return name;
    }

	public List<String> getColumns() {
        return columns;
    }

	public List<Entidad> getAllEntidads() {
        return allEntidads;
    }

	public void setAllEntidads(List<Entidad> allEntidads) {
        this.allEntidads = allEntidads;
    }

	public String findAllEntidads() {
        allEntidads = entidadService.findAllEntidads();
        dataVisible = !allEntidads.isEmpty();
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
        
        OutputLabel entNombreCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        entNombreCreateOutput.setFor("entNombreCreateInput");
        entNombreCreateOutput.setId("entNombreCreateOutput");
        entNombreCreateOutput.setValue("Ent Nombre:");
        htmlPanelGrid.getChildren().add(entNombreCreateOutput);
        
        InputTextarea entNombreCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        entNombreCreateInput.setId("entNombreCreateInput");
        entNombreCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{entidadBean.entidad.entNombre}", String.class));
        LengthValidator entNombreCreateInputValidator = new LengthValidator();
        entNombreCreateInputValidator.setMaximum(50);
        entNombreCreateInput.addValidator(entNombreCreateInputValidator);
        entNombreCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(entNombreCreateInput);
        
        Message entNombreCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        entNombreCreateInputMessage.setId("entNombreCreateInputMessage");
        entNombreCreateInputMessage.setFor("entNombreCreateInput");
        entNombreCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(entNombreCreateInputMessage);
        
        OutputLabel entCuentaCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        entCuentaCreateOutput.setFor("entCuentaCreateInput");
        entCuentaCreateOutput.setId("entCuentaCreateOutput");
        entCuentaCreateOutput.setValue("Ent Cuenta:");
        htmlPanelGrid.getChildren().add(entCuentaCreateOutput);
        
        Spinner entCuentaCreateInput = (Spinner) application.createComponent(Spinner.COMPONENT_TYPE);
        entCuentaCreateInput.setId("entCuentaCreateInput");
        entCuentaCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{entidadBean.entidad.entCuenta}", Integer.class));
        entCuentaCreateInput.setRequired(true);
        
        htmlPanelGrid.getChildren().add(entCuentaCreateInput);
        
        Message entCuentaCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        entCuentaCreateInputMessage.setId("entCuentaCreateInputMessage");
        entCuentaCreateInputMessage.setFor("entCuentaCreateInput");
        entCuentaCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(entCuentaCreateInputMessage);
        
        OutputLabel entAporteEmpleadoCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        entAporteEmpleadoCreateOutput.setFor("entAporteEmpleadoCreateInput");
        entAporteEmpleadoCreateOutput.setId("entAporteEmpleadoCreateOutput");
        entAporteEmpleadoCreateOutput.setValue("Ent Aporte Empleado:");
        htmlPanelGrid.getChildren().add(entAporteEmpleadoCreateOutput);
        
        InputText entAporteEmpleadoCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        entAporteEmpleadoCreateInput.setId("entAporteEmpleadoCreateInput");
        entAporteEmpleadoCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{entidadBean.entidad.entAporteEmpleado}", Double.class));
        entAporteEmpleadoCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(entAporteEmpleadoCreateInput);
        
        Message entAporteEmpleadoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        entAporteEmpleadoCreateInputMessage.setId("entAporteEmpleadoCreateInputMessage");
        entAporteEmpleadoCreateInputMessage.setFor("entAporteEmpleadoCreateInput");
        entAporteEmpleadoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(entAporteEmpleadoCreateInputMessage);
        
        OutputLabel entAporteEmpresaCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        entAporteEmpresaCreateOutput.setFor("entAporteEmpresaCreateInput");
        entAporteEmpresaCreateOutput.setId("entAporteEmpresaCreateOutput");
        entAporteEmpresaCreateOutput.setValue("Ent Aporte Empresa:");
        htmlPanelGrid.getChildren().add(entAporteEmpresaCreateOutput);
        
        InputText entAporteEmpresaCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        entAporteEmpresaCreateInput.setId("entAporteEmpresaCreateInput");
        entAporteEmpresaCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{entidadBean.entidad.entAporteEmpresa}", Double.class));
        entAporteEmpresaCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(entAporteEmpresaCreateInput);
        
        Message entAporteEmpresaCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        entAporteEmpresaCreateInputMessage.setId("entAporteEmpresaCreateInputMessage");
        entAporteEmpresaCreateInputMessage.setFor("entAporteEmpresaCreateInput");
        entAporteEmpresaCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(entAporteEmpresaCreateInputMessage);
        
        OutputLabel entTipoCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        entTipoCreateOutput.setFor("entTipoCreateInput");
        entTipoCreateOutput.setId("entTipoCreateOutput");
        entTipoCreateOutput.setValue("Tipo:");
        htmlPanelGrid.getChildren().add(entTipoCreateOutput);
        
        htmlPanelGrid.getChildren().add(ComponentsGenerator.getAutocompleteTipoEntidad("entTipoCreateInput", "#{entidadBean.entidad.entTipo}"));
        
        Message entTipoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        entTipoCreateInputMessage.setId("entTiporeateInputMessage");
        entTipoCreateInputMessage.setFor("entTipoCreateInput");
        entTipoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(entTipoCreateInputMessage);
        
        OutputLabel entEstadoCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        entEstadoCreateOutput.setFor("entEstadoCreateInput");
        entEstadoCreateOutput.setId("entEstadoCreateOutput");
        entEstadoCreateOutput.setValue("Estado:");
        htmlPanelGrid.getChildren().add(entEstadoCreateOutput);
        
        htmlPanelGrid.getChildren().add(ComponentsGenerator.getAutocompleteEstadoActual("entEstadoCreateInput", "#{entidadBean.entidad.entEstado}"));
        
        Message entEstadoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        entEstadoCreateInputMessage.setId("entEstadoreateInputMessage");
        entEstadoCreateInputMessage.setFor("entEstadoCreateInput");
        entEstadoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(entEstadoCreateInputMessage);
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateEditPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        OutputLabel entNombreEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        entNombreEditOutput.setFor("entNombreEditInput");
        entNombreEditOutput.setId("entNombreEditOutput");
        entNombreEditOutput.setValue("Ent Nombre:");
        htmlPanelGrid.getChildren().add(entNombreEditOutput);
        
        InputTextarea entNombreEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        entNombreEditInput.setId("entNombreEditInput");
        entNombreEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{entidadBean.entidad.entNombre}", String.class));
        LengthValidator entNombreEditInputValidator = new LengthValidator();
        entNombreEditInputValidator.setMaximum(50);
        entNombreEditInput.addValidator(entNombreEditInputValidator);
        entNombreEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(entNombreEditInput);
        
        Message entNombreEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        entNombreEditInputMessage.setId("entNombreEditInputMessage");
        entNombreEditInputMessage.setFor("entNombreEditInput");
        entNombreEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(entNombreEditInputMessage);
        
        OutputLabel entCuentaEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        entCuentaEditOutput.setFor("entCuentaEditInput");
        entCuentaEditOutput.setId("entCuentaEditOutput");
        entCuentaEditOutput.setValue("Ent Cuenta:");
        htmlPanelGrid.getChildren().add(entCuentaEditOutput);
        
        Spinner entCuentaEditInput = (Spinner) application.createComponent(Spinner.COMPONENT_TYPE);
        entCuentaEditInput.setId("entCuentaEditInput");
        entCuentaEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{entidadBean.entidad.entCuenta}", Integer.class));
        entCuentaEditInput.setRequired(true);
        
        htmlPanelGrid.getChildren().add(entCuentaEditInput);
        
        Message entCuentaEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        entCuentaEditInputMessage.setId("entCuentaEditInputMessage");
        entCuentaEditInputMessage.setFor("entCuentaEditInput");
        entCuentaEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(entCuentaEditInputMessage);
        
        OutputLabel entAporteEmpleadoEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        entAporteEmpleadoEditOutput.setFor("entAporteEmpleadoEditInput");
        entAporteEmpleadoEditOutput.setId("entAporteEmpleadoEditOutput");
        entAporteEmpleadoEditOutput.setValue("Ent Aporte Empleado:");
        htmlPanelGrid.getChildren().add(entAporteEmpleadoEditOutput);
        
        InputText entAporteEmpleadoEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        entAporteEmpleadoEditInput.setId("entAporteEmpleadoEditInput");
        entAporteEmpleadoEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{entidadBean.entidad.entAporteEmpleado}", Double.class));
        entAporteEmpleadoEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(entAporteEmpleadoEditInput);
        
        Message entAporteEmpleadoEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        entAporteEmpleadoEditInputMessage.setId("entAporteEmpleadoEditInputMessage");
        entAporteEmpleadoEditInputMessage.setFor("entAporteEmpleadoEditInput");
        entAporteEmpleadoEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(entAporteEmpleadoEditInputMessage);
        
        OutputLabel entAporteEmpresaEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        entAporteEmpresaEditOutput.setFor("entAporteEmpresaEditInput");
        entAporteEmpresaEditOutput.setId("entAporteEmpresaEditOutput");
        entAporteEmpresaEditOutput.setValue("Ent Aporte Empresa:");
        htmlPanelGrid.getChildren().add(entAporteEmpresaEditOutput);
        
        InputText entAporteEmpresaEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        entAporteEmpresaEditInput.setId("entAporteEmpresaEditInput");
        entAporteEmpresaEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{entidadBean.entidad.entAporteEmpresa}", Double.class));
        entAporteEmpresaEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(entAporteEmpresaEditInput);
        
        Message entAporteEmpresaEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        entAporteEmpresaEditInputMessage.setId("entAporteEmpresaEditInputMessage");
        entAporteEmpresaEditInputMessage.setFor("entAporteEmpresaEditInput");
        entAporteEmpresaEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(entAporteEmpresaEditInputMessage);
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateViewPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText entNombreLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        entNombreLabel.setId("entNombreLabel");
        entNombreLabel.setValue("Ent Nombre:");
        htmlPanelGrid.getChildren().add(entNombreLabel);
        
        InputTextarea entNombreValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        entNombreValue.setId("entNombreValue");
        entNombreValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{entidadBean.entidad.entNombre}", String.class));
        entNombreValue.setReadonly(true);
        entNombreValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(entNombreValue);
        
        HtmlOutputText entCuentaLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        entCuentaLabel.setId("entCuentaLabel");
        entCuentaLabel.setValue("Ent Cuenta:");
        htmlPanelGrid.getChildren().add(entCuentaLabel);
        
        HtmlOutputText entCuentaValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        entCuentaValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{entidadBean.entidad.entCuenta}", String.class));
        htmlPanelGrid.getChildren().add(entCuentaValue);
        
        HtmlOutputText entAporteEmpleadoLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        entAporteEmpleadoLabel.setId("entAporteEmpleadoLabel");
        entAporteEmpleadoLabel.setValue("Ent Aporte Empleado:");
        htmlPanelGrid.getChildren().add(entAporteEmpleadoLabel);
        
        HtmlOutputText entAporteEmpleadoValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        entAporteEmpleadoValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{entidadBean.entidad.entAporteEmpleado}", String.class));
        htmlPanelGrid.getChildren().add(entAporteEmpleadoValue);
        
        HtmlOutputText entAporteEmpresaLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        entAporteEmpresaLabel.setId("entAporteEmpresaLabel");
        entAporteEmpresaLabel.setValue("Ent Aporte Empresa:");
        htmlPanelGrid.getChildren().add(entAporteEmpresaLabel);
        
        HtmlOutputText entAporteEmpresaValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        entAporteEmpresaValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{entidadBean.entidad.entAporteEmpresa}", String.class));
        htmlPanelGrid.getChildren().add(entAporteEmpresaValue);
        
        return htmlPanelGrid;
    }

	public Entidad getEntidad() {
        if (entidad == null) {
            entidad = new Entidad();
        }
        return entidad;
    }

	public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
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
        findAllEntidads();
        return "entidad";
    }

	public String displayCreateDialog() {
        entidad = new Entidad();
        createDialogVisible = true;
        return "entidad";
    }

	public String persist() {
        String message = "";
        if (entidad.getEntCodigo() != null) {
            entidadService.updateEntidad(entidad);
            message = "message_successfully_updated";
        } else {
            entidadService.saveEntidad(entidad);
            message = "message_successfully_created";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialogWidget.hide()");
        context.execute("editDialogWidget.hide()");
        
        FacesMessage facesMessage = MessageFactory.getMessage(message, "Entidad");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllEntidads();
    }

	public String delete() {
        entidadService.deleteEntidad(entidad);
        FacesMessage facesMessage = MessageFactory.getMessage("message_successfully_deleted", "Entidad");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllEntidads();
    }

	public void reset() {
        entidad = null;
        createDialogVisible = false;
    }

	public void handleDialogClose(CloseEvent event) {
        reset();
    }

	private static final long serialVersionUID = 1L;
}
