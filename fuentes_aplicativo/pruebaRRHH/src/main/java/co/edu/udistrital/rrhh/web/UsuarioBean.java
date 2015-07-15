package co.edu.udistrital.rrhh.web;
import co.edu.udistrital.rrhh.domain.Usuario;
import co.edu.udistrital.rrhh.service.UsuarioService;
import co.edu.udistrital.rrhh.web.util.CampoValor;
import co.edu.udistrital.rrhh.web.util.ComponentsGenerator;
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
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.validator.LengthValidator;
import javax.faces.validator.RegexValidator;

import org.hibernate.validator.internal.constraintvalidators.EmailValidator;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.message.Message;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.password.Password;
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

	private List<CampoValor> columns;

	private HtmlPanelGrid createPanelGrid;

	private HtmlPanelGrid editPanelGrid;

	private HtmlPanelGrid viewPanelGrid;

	private boolean createDialogVisible = false;

	@PostConstruct
    public void init() {
        columns = new ArrayList<CampoValor>();
        columns.add( new CampoValor("Nombre", "usuNombre") );
        columns.add( new CampoValor("Rol", "nombreRol") );
        columns.add(new CampoValor("Correo", "usuCorreo"));
    }

	public String getName() {
        return name;
    }

	public List<CampoValor> getColumns() {
        return columns;
    }

	public List<Usuario> getAllUsuarios() {
        return allUsuarios;
    }

	public void setAllUsuarios(List<Usuario> allUsuarios) {
        this.allUsuarios = allUsuarios;
    }

	public String findAllUsuarios() {
        allUsuarios = usuarioService.findAllUsuariosActivos();
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
        
        
        
        htmlPanelGrid.getChildren().add(ComponentsGenerator.getBasicOutputLabel("usuUserCreateInput", "usuUserCreateOutput", "Usuario:"));
        
        InputText usuUserCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        usuUserCreateInput.setId("usuUserCreateInput");
        usuUserCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{usuarioBean.usuario.usuUsuario}", String.class));
        LengthValidator usuUserCreateInputValidator = new LengthValidator();
        usuUserCreateInputValidator.setMaximum(50);
        usuUserCreateInput.addValidator(usuUserCreateInputValidator);
        usuUserCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(usuUserCreateInput);
        
        Message usuUserCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        usuUserCreateInputMessage.setId("usuUserCreateInputMessage");
        usuUserCreateInputMessage.setFor("usuUserCreateInput");
        usuUserCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(usuUserCreateInputMessage);
        
        
        
        OutputLabel usuNombreCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        usuNombreCreateOutput.setFor("usuNombreCreateInput");
        usuNombreCreateOutput.setId("usuNombreCreateOutput");
        usuNombreCreateOutput.setValue("Nombre:");
        htmlPanelGrid.getChildren().add(usuNombreCreateOutput);
        
        InputText usuNombreCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
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
        usuClaveCreateOutput.setValue("Clave:");
        htmlPanelGrid.getChildren().add(usuClaveCreateOutput);
        
        Password usuClaveCreateInput = (Password) application.createComponent(Password.COMPONENT_TYPE);
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
        
        
        OutputLabel usuRolCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        usuRolCreateOutput.setFor("usuRolCreateInput");
        usuRolCreateOutput.setId("usuRolCreateOutput");
        usuRolCreateOutput.setValue("Rol:");
        htmlPanelGrid.getChildren().add(usuRolCreateOutput);
        
        htmlPanelGrid.getChildren().add(ComponentsGenerator.getAutocompleteRol("usuRolCreateInput", "#{usuarioBean.usuario.usuRol}"));
        
        Message usuRolCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        usuRolCreateInputMessage.setId("usuRolCreateInputMessage");
        usuRolCreateInputMessage.setFor("usuRolCreateInput");
        usuRolCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(usuRolCreateInputMessage);
        
        OutputLabel usuCorreoCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        usuCorreoCreateOutput.setFor("usuCorreoCreateInput");
        usuCorreoCreateOutput.setId("usuCorreoCreateOutput");
        usuCorreoCreateOutput.setValue("Correo:");
        htmlPanelGrid.getChildren().add(usuCorreoCreateOutput);
        
        InputText usuCorreoCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        usuCorreoCreateInput.setId("usuCorreoCreateInput");
        usuCorreoCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{usuarioBean.usuario.usuCorreo}", String.class));
        LengthValidator usuCorreoCreateInputValidator = new LengthValidator();
        usuCorreoCreateInputValidator.setMaximum(80);
        usuCorreoCreateInput.addValidator(usuCorreoCreateInputValidator);
        usuCorreoCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(usuCorreoCreateInput);
        javax.faces.validator.RegexValidator reg =  new RegexValidator();
        reg.setPattern("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        usuCorreoCreateInput.addValidator(reg);
        
        Message usuCorreoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        usuCorreoCreateInputMessage.setId("usuCorreoCreateInputMessage");
        usuCorreoCreateInputMessage.setFor("usuCorreoCreateInput");
        usuCorreoCreateInputMessage.setDisplay("icon");

        htmlPanelGrid.getChildren().add(usuCorreoCreateInputMessage);
        
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateEditPanel() {return null;}

	public HtmlPanelGrid populateViewPanel() {return null;}

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
        usuario.setUsuEstado(Constantes.GENERAL_ESTADO_ACTIVO);
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
