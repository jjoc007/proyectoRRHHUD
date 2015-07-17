package co.edu.udistrital.rrhh.web;
import co.edu.udistrital.rrhh.domain.Rol;
import co.edu.udistrital.rrhh.service.RolService;
import co.edu.udistrital.rrhh.web.util.CampoValor;
import co.edu.udistrital.rrhh.web.util.Constantes;
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

	private String name = "Roles";

	private Rol rol;

	private List<Rol> allRols;

	private boolean dataVisible = false;

	private List<CampoValor> columns;

	private HtmlPanelGrid createPanelGrid;

	private HtmlPanelGrid editPanelGrid;

	private HtmlPanelGrid viewPanelGrid;

	private boolean createDialogVisible = false;

	@PostConstruct
    public void init() {
        columns = new ArrayList<CampoValor>();
        columns.add(new CampoValor("Nombre", "rolNombre"));
        columns.add(new CampoValor("Descripcion", "rolDescripcion"));

    }

	public String getName() {
        return name;
    }

	public List<CampoValor> getColumns() {
        return columns;
    }

	public List<Rol> getAllRols() {
        return allRols;
    }

	public void setAllRols(List<Rol> allRols) {
        this.allRols = allRols;
    }

	public String findAllRols() {
        allRols = rolService.findAllRolesActivos();
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
        
        OutputLabel rolNombreCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        rolNombreCreateOutput.setFor("rolNombreCreateInput");
        rolNombreCreateOutput.setId("rolNombreCreateOutput");
        rolNombreCreateOutput.setValue("Nombre:");
        htmlPanelGrid.getChildren().add(rolNombreCreateOutput);
        
        InputText rolNombreCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
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
        rolDescripcionCreateOutput.setValue("Descripcion:");
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
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateEditPanel() {return null;}

	public HtmlPanelGrid populateViewPanel() {return null;}

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
        rol.setRolEstado(Constantes.GENERAL_ESTADO_ACTIVO);
        if (rol.getRolId() != null) {
        	rol.setRolEstado(Constantes.GENERAL_ESTADO_ACTIVO);
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
