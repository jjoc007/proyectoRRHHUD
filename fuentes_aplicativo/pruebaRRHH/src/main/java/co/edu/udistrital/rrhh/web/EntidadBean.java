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
            editPanelGrid = populateCreatePanel();
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
        
        InputText entCuentaCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        entCuentaCreateInput.setId("entCuentaCreateInput");
        entCuentaCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{entidadBean.entidad.entCuenta}", Integer.class));
        entCuentaCreateInput.setRequired(true);
        
        htmlPanelGrid.getChildren().add(entCuentaCreateInput);
        
        Message entCuentaCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        entCuentaCreateInputMessage.setId("entCuentaCreateInputMessage");
        entCuentaCreateInputMessage.setFor("entCuentaCreateInput");
        entCuentaCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(entCuentaCreateInputMessage);
        
        
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

	public HtmlPanelGrid populateEditPanel() {return null;}

	public HtmlPanelGrid populateViewPanel() {return null;}

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
