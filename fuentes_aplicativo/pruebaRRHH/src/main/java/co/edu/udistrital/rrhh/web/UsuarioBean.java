package co.edu.udistrital.rrhh.web;
import co.edu.udistrital.rrhh.domain.Usuario;
import co.edu.udistrital.rrhh.service.UsuarioService;
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
@ManagedBean(name = "usuarioBean")
@SessionScoped
@RooSerializable
@RooJsfManagedBean(entity = Usuario.class, beanName = "usuarioBean")
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
    UsuarioService usuarioService;

	private String name = "Usuarios";

	private Usuario usuario;

	private List<Usuario> allUsuarios;

	private boolean dataVisible = false;

	private List<String> columns;

	private HtmlPanelGrid createPanelGrid;

	private HtmlPanelGrid editPanelGrid;

	private HtmlPanelGrid viewPanelGrid;

	private boolean createDialogVisible = false;

	@PostConstruct
    public void init() {
        columns = new ArrayList<String>();
        columns.add("usuNombre");
        columns.add("usuClave");
        columns.add("usuEstado");
        columns.add("usuCorreo");
    }

	public String getName() {
        return name;
    }

	public List<String> getColumns() {
        return columns;
    }

	public List<Usuario> getAllUsuarios() {
        return allUsuarios;
    }

	public void setAllUsuarios(List<Usuario> allUsuarios) {
        this.allUsuarios = allUsuarios;
    }

	public String findAllUsuarios() {
        allUsuarios = usuarioService.findAllUsuarios();
        dataVisible = !allUsuarios.isEmpty();
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
        
        OutputLabel usuNombreCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        usuNombreCreateOutput.setFor("usuNombreCreateInput");
        usuNombreCreateOutput.setId("usuNombreCreateOutput");
        usuNombreCreateOutput.setValue("Usu Nombre:");
        htmlPanelGrid.getChildren().add(usuNombreCreateOutput);
        
        InputTextarea usuNombreCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        usuNombreCreateInput.setId("usuNombreCreateInput");
        usuNombreCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{usuarioBean.usuario.usuNombre}", String.class));
        LengthValidator usuNombreCreateInputValidator = new LengthValidator();
        usuNombreCreateInputValidator.setMaximum(50);
        usuNombreCreateInput.addValidator(usuNombreCreateInputValidator);
        usuNombreCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(usuNombreCreateInput);
        
        Message usuNombreCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        usuNombreCreateInputMessage.setId("usuNombreCreateInputMessage");
        usuNombreCreateInputMessage.setFor("usuNombreCreateInput");
        usuNombreCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(usuNombreCreateInputMessage);
        
        OutputLabel usuClaveCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        usuClaveCreateOutput.setFor("usuClaveCreateInput");
        usuClaveCreateOutput.setId("usuClaveCreateOutput");
        usuClaveCreateOutput.setValue("Usu Clave:");
        htmlPanelGrid.getChildren().add(usuClaveCreateOutput);
        
        InputTextarea usuClaveCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        usuClaveCreateInput.setId("usuClaveCreateInput");
        usuClaveCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{usuarioBean.usuario.usuClave}", String.class));
        LengthValidator usuClaveCreateInputValidator = new LengthValidator();
        usuClaveCreateInputValidator.setMaximum(50);
        usuClaveCreateInput.addValidator(usuClaveCreateInputValidator);
        usuClaveCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(usuClaveCreateInput);
        
        Message usuClaveCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        usuClaveCreateInputMessage.setId("usuClaveCreateInputMessage");
        usuClaveCreateInputMessage.setFor("usuClaveCreateInput");
        usuClaveCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(usuClaveCreateInputMessage);
        
        OutputLabel usuEstadoCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        usuEstadoCreateOutput.setFor("usuEstadoCreateInput");
        usuEstadoCreateOutput.setId("usuEstadoCreateOutput");
        usuEstadoCreateOutput.setValue("Usu Estado:");
        htmlPanelGrid.getChildren().add(usuEstadoCreateOutput);
        
        InputText usuEstadoCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        usuEstadoCreateInput.setId("usuEstadoCreateInput");
        usuEstadoCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{usuarioBean.usuario.usuEstado}", String.class));
        LengthValidator usuEstadoCreateInputValidator = new LengthValidator();
        usuEstadoCreateInputValidator.setMaximum(1);
        usuEstadoCreateInput.addValidator(usuEstadoCreateInputValidator);
        usuEstadoCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(usuEstadoCreateInput);
        
        Message usuEstadoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        usuEstadoCreateInputMessage.setId("usuEstadoCreateInputMessage");
        usuEstadoCreateInputMessage.setFor("usuEstadoCreateInput");
        usuEstadoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(usuEstadoCreateInputMessage);
        
        OutputLabel usuCorreoCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        usuCorreoCreateOutput.setFor("usuCorreoCreateInput");
        usuCorreoCreateOutput.setId("usuCorreoCreateOutput");
        usuCorreoCreateOutput.setValue("Usu Correo:");
        htmlPanelGrid.getChildren().add(usuCorreoCreateOutput);
        
        InputTextarea usuCorreoCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        usuCorreoCreateInput.setId("usuCorreoCreateInput");
        usuCorreoCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{usuarioBean.usuario.usuCorreo}", String.class));
        LengthValidator usuCorreoCreateInputValidator = new LengthValidator();
        usuCorreoCreateInputValidator.setMaximum(80);
        usuCorreoCreateInput.addValidator(usuCorreoCreateInputValidator);
        usuCorreoCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(usuCorreoCreateInput);
        
        Message usuCorreoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        usuCorreoCreateInputMessage.setId("usuCorreoCreateInputMessage");
        usuCorreoCreateInputMessage.setFor("usuCorreoCreateInput");
        usuCorreoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(usuCorreoCreateInputMessage);
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateEditPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        OutputLabel usuNombreEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        usuNombreEditOutput.setFor("usuNombreEditInput");
        usuNombreEditOutput.setId("usuNombreEditOutput");
        usuNombreEditOutput.setValue("Usu Nombre:");
        htmlPanelGrid.getChildren().add(usuNombreEditOutput);
        
        InputTextarea usuNombreEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        usuNombreEditInput.setId("usuNombreEditInput");
        usuNombreEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{usuarioBean.usuario.usuNombre}", String.class));
        LengthValidator usuNombreEditInputValidator = new LengthValidator();
        usuNombreEditInputValidator.setMaximum(50);
        usuNombreEditInput.addValidator(usuNombreEditInputValidator);
        usuNombreEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(usuNombreEditInput);
        
        Message usuNombreEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        usuNombreEditInputMessage.setId("usuNombreEditInputMessage");
        usuNombreEditInputMessage.setFor("usuNombreEditInput");
        usuNombreEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(usuNombreEditInputMessage);
        
        OutputLabel usuClaveEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        usuClaveEditOutput.setFor("usuClaveEditInput");
        usuClaveEditOutput.setId("usuClaveEditOutput");
        usuClaveEditOutput.setValue("Usu Clave:");
        htmlPanelGrid.getChildren().add(usuClaveEditOutput);
        
        InputTextarea usuClaveEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        usuClaveEditInput.setId("usuClaveEditInput");
        usuClaveEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{usuarioBean.usuario.usuClave}", String.class));
        LengthValidator usuClaveEditInputValidator = new LengthValidator();
        usuClaveEditInputValidator.setMaximum(50);
        usuClaveEditInput.addValidator(usuClaveEditInputValidator);
        usuClaveEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(usuClaveEditInput);
        
        Message usuClaveEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        usuClaveEditInputMessage.setId("usuClaveEditInputMessage");
        usuClaveEditInputMessage.setFor("usuClaveEditInput");
        usuClaveEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(usuClaveEditInputMessage);
        
        OutputLabel usuEstadoEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        usuEstadoEditOutput.setFor("usuEstadoEditInput");
        usuEstadoEditOutput.setId("usuEstadoEditOutput");
        usuEstadoEditOutput.setValue("Usu Estado:");
        htmlPanelGrid.getChildren().add(usuEstadoEditOutput);
        
        InputText usuEstadoEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        usuEstadoEditInput.setId("usuEstadoEditInput");
        usuEstadoEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{usuarioBean.usuario.usuEstado}", String.class));
        LengthValidator usuEstadoEditInputValidator = new LengthValidator();
        usuEstadoEditInputValidator.setMaximum(1);
        usuEstadoEditInput.addValidator(usuEstadoEditInputValidator);
        usuEstadoEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(usuEstadoEditInput);
        
        Message usuEstadoEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        usuEstadoEditInputMessage.setId("usuEstadoEditInputMessage");
        usuEstadoEditInputMessage.setFor("usuEstadoEditInput");
        usuEstadoEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(usuEstadoEditInputMessage);
        
        OutputLabel usuCorreoEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        usuCorreoEditOutput.setFor("usuCorreoEditInput");
        usuCorreoEditOutput.setId("usuCorreoEditOutput");
        usuCorreoEditOutput.setValue("Usu Correo:");
        htmlPanelGrid.getChildren().add(usuCorreoEditOutput);
        
        InputTextarea usuCorreoEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        usuCorreoEditInput.setId("usuCorreoEditInput");
        usuCorreoEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{usuarioBean.usuario.usuCorreo}", String.class));
        LengthValidator usuCorreoEditInputValidator = new LengthValidator();
        usuCorreoEditInputValidator.setMaximum(80);
        usuCorreoEditInput.addValidator(usuCorreoEditInputValidator);
        usuCorreoEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(usuCorreoEditInput);
        
        Message usuCorreoEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        usuCorreoEditInputMessage.setId("usuCorreoEditInputMessage");
        usuCorreoEditInputMessage.setFor("usuCorreoEditInput");
        usuCorreoEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(usuCorreoEditInputMessage);
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateViewPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText usuNombreLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        usuNombreLabel.setId("usuNombreLabel");
        usuNombreLabel.setValue("Usu Nombre:");
        htmlPanelGrid.getChildren().add(usuNombreLabel);
        
        InputTextarea usuNombreValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        usuNombreValue.setId("usuNombreValue");
        usuNombreValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{usuarioBean.usuario.usuNombre}", String.class));
        usuNombreValue.setReadonly(true);
        usuNombreValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(usuNombreValue);
        
        HtmlOutputText usuClaveLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        usuClaveLabel.setId("usuClaveLabel");
        usuClaveLabel.setValue("Usu Clave:");
        htmlPanelGrid.getChildren().add(usuClaveLabel);
        
        InputTextarea usuClaveValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        usuClaveValue.setId("usuClaveValue");
        usuClaveValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{usuarioBean.usuario.usuClave}", String.class));
        usuClaveValue.setReadonly(true);
        usuClaveValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(usuClaveValue);
        
        HtmlOutputText usuEstadoLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        usuEstadoLabel.setId("usuEstadoLabel");
        usuEstadoLabel.setValue("Usu Estado:");
        htmlPanelGrid.getChildren().add(usuEstadoLabel);
        
        HtmlOutputText usuEstadoValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        usuEstadoValue.setId("usuEstadoValue");
        usuEstadoValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{usuarioBean.usuario.usuEstado}", String.class));
        htmlPanelGrid.getChildren().add(usuEstadoValue);
        
        HtmlOutputText usuCorreoLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        usuCorreoLabel.setId("usuCorreoLabel");
        usuCorreoLabel.setValue("Usu Correo:");
        htmlPanelGrid.getChildren().add(usuCorreoLabel);
        
        InputTextarea usuCorreoValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        usuCorreoValue.setId("usuCorreoValue");
        usuCorreoValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{usuarioBean.usuario.usuCorreo}", String.class));
        usuCorreoValue.setReadonly(true);
        usuCorreoValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(usuCorreoValue);
        
        return htmlPanelGrid;
    }

	public Usuario getUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }

	public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        findAllUsuarios();
        return "usuario";
    }

	public String displayCreateDialog() {
        usuario = new Usuario();
        createDialogVisible = true;
        return "usuario";
    }

	public String persist() {
        String message = "";
        if (usuario.getUsuUsuario() != null) {
            usuarioService.updateUsuario(usuario);
            message = "message_successfully_updated";
        } else {
            usuarioService.saveUsuario(usuario);
            message = "message_successfully_created";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialogWidget.hide()");
        context.execute("editDialogWidget.hide()");
        
        FacesMessage facesMessage = MessageFactory.getMessage(message, "Usuario");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllUsuarios();
    }

	public String delete() {
        usuarioService.deleteUsuario(usuario);
        FacesMessage facesMessage = MessageFactory.getMessage("message_successfully_deleted", "Usuario");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllUsuarios();
    }

	public void reset() {
        usuario = null;
        createDialogVisible = false;
    }

	public void handleDialogClose(CloseEvent event) {
        reset();
    }
}
