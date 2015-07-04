package co.edu.udistrital.rrhh.web;
import co.edu.udistrital.rrhh.domain.Rol;
import co.edu.udistrital.rrhh.service.RolService;
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
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@Configurable
@ManagedBean(name = "rolBean")
@SessionScoped
@RooSerializable
@RooJsfManagedBean(entity = Rol.class, beanName = "rolBean")
public class RolBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
    RolService rolService;

	private String name = "Rols";

	private Rol rol;

	private List<Rol> allRols;

	private boolean dataVisible = false;

	private List<String> columns;

	private HtmlPanelGrid createPanelGrid;

	private HtmlPanelGrid editPanelGrid;

	private HtmlPanelGrid viewPanelGrid;

	private boolean createDialogVisible = false;

	@PostConstruct
    public void init() {
        columns = new ArrayList<String>();
        columns.add("rolNombre");
        columns.add("rolDescripcion");
        columns.add("rolEstado");
    }

	public String getName() {
        return name;
    }

	public List<String> getColumns() {
        return columns;
    }

	public List<Rol> getAllRols() {
        return allRols;
    }

	public void setAllRols(List<Rol> allRols) {
        this.allRols = allRols;
    }

	public String findAllRols() {
        allRols = rolService.findAllRols();
        dataVisible = !allRols.isEmpty();
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
        
        OutputLabel rolNombreCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        rolNombreCreateOutput.setFor("rolNombreCreateInput");
        rolNombreCreateOutput.setId("rolNombreCreateOutput");
        rolNombreCreateOutput.setValue("Rol Nombre:");
        htmlPanelGrid.getChildren().add(rolNombreCreateOutput);
        
        InputTextarea rolNombreCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        rolNombreCreateInput.setId("rolNombreCreateInput");
        rolNombreCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{rolBean.rol.rolNombre}", String.class));
        LengthValidator rolNombreCreateInputValidator = new LengthValidator();
        rolNombreCreateInputValidator.setMaximum(50);
        rolNombreCreateInput.addValidator(rolNombreCreateInputValidator);
        rolNombreCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(rolNombreCreateInput);
        
        Message rolNombreCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        rolNombreCreateInputMessage.setId("rolNombreCreateInputMessage");
        rolNombreCreateInputMessage.setFor("rolNombreCreateInput");
        rolNombreCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(rolNombreCreateInputMessage);
        
        OutputLabel rolDescripcionCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        rolDescripcionCreateOutput.setFor("rolDescripcionCreateInput");
        rolDescripcionCreateOutput.setId("rolDescripcionCreateOutput");
        rolDescripcionCreateOutput.setValue("Rol Descripcion:");
        htmlPanelGrid.getChildren().add(rolDescripcionCreateOutput);
        
        InputText rolDescripcionCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        rolDescripcionCreateInput.setId("rolDescripcionCreateInput");
        rolDescripcionCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{rolBean.rol.rolDescripcion}", String.class));
        rolDescripcionCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(rolDescripcionCreateInput);
        
        Message rolDescripcionCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        rolDescripcionCreateInputMessage.setId("rolDescripcionCreateInputMessage");
        rolDescripcionCreateInputMessage.setFor("rolDescripcionCreateInput");
        rolDescripcionCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(rolDescripcionCreateInputMessage);
        
        OutputLabel rolEstadoCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        rolEstadoCreateOutput.setFor("rolEstadoCreateInput");
        rolEstadoCreateOutput.setId("rolEstadoCreateOutput");
        rolEstadoCreateOutput.setValue("Rol Estado:");
        htmlPanelGrid.getChildren().add(rolEstadoCreateOutput);
        
        InputText rolEstadoCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        rolEstadoCreateInput.setId("rolEstadoCreateInput");
        rolEstadoCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{rolBean.rol.rolEstado}", String.class));
        LengthValidator rolEstadoCreateInputValidator = new LengthValidator();
        rolEstadoCreateInputValidator.setMaximum(1);
        rolEstadoCreateInput.addValidator(rolEstadoCreateInputValidator);
        rolEstadoCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(rolEstadoCreateInput);
        
        Message rolEstadoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        rolEstadoCreateInputMessage.setId("rolEstadoCreateInputMessage");
        rolEstadoCreateInputMessage.setFor("rolEstadoCreateInput");
        rolEstadoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(rolEstadoCreateInputMessage);
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateEditPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        OutputLabel rolNombreEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        rolNombreEditOutput.setFor("rolNombreEditInput");
        rolNombreEditOutput.setId("rolNombreEditOutput");
        rolNombreEditOutput.setValue("Rol Nombre:");
        htmlPanelGrid.getChildren().add(rolNombreEditOutput);
        
        InputTextarea rolNombreEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        rolNombreEditInput.setId("rolNombreEditInput");
        rolNombreEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{rolBean.rol.rolNombre}", String.class));
        LengthValidator rolNombreEditInputValidator = new LengthValidator();
        rolNombreEditInputValidator.setMaximum(50);
        rolNombreEditInput.addValidator(rolNombreEditInputValidator);
        rolNombreEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(rolNombreEditInput);
        
        Message rolNombreEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        rolNombreEditInputMessage.setId("rolNombreEditInputMessage");
        rolNombreEditInputMessage.setFor("rolNombreEditInput");
        rolNombreEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(rolNombreEditInputMessage);
        
        OutputLabel rolDescripcionEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        rolDescripcionEditOutput.setFor("rolDescripcionEditInput");
        rolDescripcionEditOutput.setId("rolDescripcionEditOutput");
        rolDescripcionEditOutput.setValue("Rol Descripcion:");
        htmlPanelGrid.getChildren().add(rolDescripcionEditOutput);
        
        InputText rolDescripcionEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        rolDescripcionEditInput.setId("rolDescripcionEditInput");
        rolDescripcionEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{rolBean.rol.rolDescripcion}", String.class));
        rolDescripcionEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(rolDescripcionEditInput);
        
        Message rolDescripcionEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        rolDescripcionEditInputMessage.setId("rolDescripcionEditInputMessage");
        rolDescripcionEditInputMessage.setFor("rolDescripcionEditInput");
        rolDescripcionEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(rolDescripcionEditInputMessage);
        
        OutputLabel rolEstadoEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        rolEstadoEditOutput.setFor("rolEstadoEditInput");
        rolEstadoEditOutput.setId("rolEstadoEditOutput");
        rolEstadoEditOutput.setValue("Rol Estado:");
        htmlPanelGrid.getChildren().add(rolEstadoEditOutput);
        
        InputText rolEstadoEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        rolEstadoEditInput.setId("rolEstadoEditInput");
        rolEstadoEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{rolBean.rol.rolEstado}", String.class));
        LengthValidator rolEstadoEditInputValidator = new LengthValidator();
        rolEstadoEditInputValidator.setMaximum(1);
        rolEstadoEditInput.addValidator(rolEstadoEditInputValidator);
        rolEstadoEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(rolEstadoEditInput);
        
        Message rolEstadoEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        rolEstadoEditInputMessage.setId("rolEstadoEditInputMessage");
        rolEstadoEditInputMessage.setFor("rolEstadoEditInput");
        rolEstadoEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(rolEstadoEditInputMessage);
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateViewPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText rolNombreLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        rolNombreLabel.setId("rolNombreLabel");
        rolNombreLabel.setValue("Rol Nombre:");
        htmlPanelGrid.getChildren().add(rolNombreLabel);
        
        InputTextarea rolNombreValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        rolNombreValue.setId("rolNombreValue");
        rolNombreValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{rolBean.rol.rolNombre}", String.class));
        rolNombreValue.setReadonly(true);
        rolNombreValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(rolNombreValue);
        
        HtmlOutputText rolDescripcionLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        rolDescripcionLabel.setId("rolDescripcionLabel");
        rolDescripcionLabel.setValue("Rol Descripcion:");
        htmlPanelGrid.getChildren().add(rolDescripcionLabel);
        
        HtmlOutputText rolDescripcionValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        rolDescripcionValue.setId("rolDescripcionValue");
        rolDescripcionValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{rolBean.rol.rolDescripcion}", String.class));
        htmlPanelGrid.getChildren().add(rolDescripcionValue);
        
        HtmlOutputText rolEstadoLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        rolEstadoLabel.setId("rolEstadoLabel");
        rolEstadoLabel.setValue("Rol Estado:");
        htmlPanelGrid.getChildren().add(rolEstadoLabel);
        
        HtmlOutputText rolEstadoValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        rolEstadoValue.setId("rolEstadoValue");
        rolEstadoValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{rolBean.rol.rolEstado}", String.class));
        htmlPanelGrid.getChildren().add(rolEstadoValue);
        
        return htmlPanelGrid;
    }

	public Rol getRol() {
        if (rol == null) {
            rol = new Rol();
        }
        return rol;
    }

	public void setRol(Rol rol) {
        this.rol = rol;
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
        findAllRols();
        return "rol";
    }

	public String displayCreateDialog() {
        rol = new Rol();
        createDialogVisible = true;
        return "rol";
    }

	public String persist() {
        String message = "";
        if (rol.getRolId() != null) {
            rolService.updateRol(rol);
            message = "message_successfully_updated";
        } else {
            rolService.saveRol(rol);
            message = "message_successfully_created";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialogWidget.hide()");
        context.execute("editDialogWidget.hide()");
        
        FacesMessage facesMessage = MessageFactory.getMessage(message, "Rol");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllRols();
    }

	public String delete() {
        rolService.deleteRol(rol);
        FacesMessage facesMessage = MessageFactory.getMessage("message_successfully_deleted", "Rol");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllRols();
    }

	public void reset() {
        rol = null;
        createDialogVisible = false;
    }

	public void handleDialogClose(CloseEvent event) {
        reset();
    }
}
