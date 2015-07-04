package co.edu.udistrital.rrhh.web;
import co.edu.udistrital.rrhh.domain.Cargo;
import co.edu.udistrital.rrhh.service.CargoService;
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

@ManagedBean(name = "cargoBean")
@SessionScoped
@Configurable
@RooSerializable
@RooJsfManagedBean(entity = Cargo.class, beanName = "cargoBean")
public class CargoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
    CargoService cargoService;

	private String name = "Cargoes";

	private Cargo cargo;

	private List<Cargo> allCargoes;

	private boolean dataVisible = false;

	private List<String> columns;

	private HtmlPanelGrid createPanelGrid;

	private HtmlPanelGrid editPanelGrid;

	private HtmlPanelGrid viewPanelGrid;

	private boolean createDialogVisible = false;

	@PostConstruct
    public void init() {
        columns = new ArrayList<String>();
        columns.add("carNombre");
        columns.add("carSalario");
    }

	public String getName() {
        return name;
    }

	public List<String> getColumns() {
        return columns;
    }

	public List<Cargo> getAllCargoes() {
        return allCargoes;
    }

	public void setAllCargoes(List<Cargo> allCargoes) {
        this.allCargoes = allCargoes;
    }

	public String findAllCargoes() {
        allCargoes = cargoService.findAllCargoes();
        dataVisible = !allCargoes.isEmpty();
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
        
        // Codigo del cargo 
        
        OutputLabel carCogigoCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        carCogigoCreateOutput.setFor("carCogigoCreateInput");
        carCogigoCreateOutput.setId("carCogigoCreateOutput");
        carCogigoCreateOutput.setValue("Codigo:");
        htmlPanelGrid.getChildren().add(carCogigoCreateOutput);
        
        InputTextarea carCogigoCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        carCogigoCreateInput.setId("carCogigoCreateInput");
        carCogigoCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{cargoBean.cargo.carCogigo}", String.class));
        LengthValidator carCogigoCreateInputValidator = new LengthValidator();
        carCogigoCreateInputValidator.setMaximum(50);
        carCogigoCreateInput.addValidator(carCogigoCreateInputValidator);
        carCogigoCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(carCogigoCreateInput);
        
        Message carCogigoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        carCogigoCreateInputMessage.setId("carCogigoCreateInputMessage");
        carCogigoCreateInputMessage.setFor("carCogigoCreateInput");
        carCogigoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(carCogigoCreateInputMessage);
        
        // 
        OutputLabel carNombreCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        carNombreCreateOutput.setFor("carNombreCreateInput");
        carNombreCreateOutput.setId("carNombreCreateOutput");
        carNombreCreateOutput.setValue("Car Nombre:");
        htmlPanelGrid.getChildren().add(carNombreCreateOutput);
        
        InputTextarea carNombreCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        carNombreCreateInput.setId("carNombreCreateInput");
        carNombreCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{cargoBean.cargo.carNombre}", String.class));
        LengthValidator carNombreCreateInputValidator = new LengthValidator();
        carNombreCreateInputValidator.setMaximum(50);
        carNombreCreateInput.addValidator(carNombreCreateInputValidator);
        carNombreCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(carNombreCreateInput);
        
        Message carNombreCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        carNombreCreateInputMessage.setId("carNombreCreateInputMessage");
        carNombreCreateInputMessage.setFor("carNombreCreateInput");
        carNombreCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(carNombreCreateInputMessage);
        
        OutputLabel carSalarioCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        carSalarioCreateOutput.setFor("carSalarioCreateInput");
        carSalarioCreateOutput.setId("carSalarioCreateOutput");
        carSalarioCreateOutput.setValue("Car Salario:");
        htmlPanelGrid.getChildren().add(carSalarioCreateOutput);
        
        InputText carSalarioCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        carSalarioCreateInput.setId("carSalarioCreateInput");
        carSalarioCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{cargoBean.cargo.carSalario}", Double.class));
        carSalarioCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(carSalarioCreateInput);
        
        Message carSalarioCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        carSalarioCreateInputMessage.setId("carSalarioCreateInputMessage");
        carSalarioCreateInputMessage.setFor("carSalarioCreateInput");
        carSalarioCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(carSalarioCreateInputMessage);
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateEditPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        OutputLabel carNombreEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        carNombreEditOutput.setFor("carNombreEditInput");
        carNombreEditOutput.setId("carNombreEditOutput");
        carNombreEditOutput.setValue("Car Nombre:");
        htmlPanelGrid.getChildren().add(carNombreEditOutput);
        
        InputTextarea carNombreEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        carNombreEditInput.setId("carNombreEditInput");
        carNombreEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{cargoBean.cargo.carNombre}", String.class));
        LengthValidator carNombreEditInputValidator = new LengthValidator();
        carNombreEditInputValidator.setMaximum(50);
        carNombreEditInput.addValidator(carNombreEditInputValidator);
        carNombreEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(carNombreEditInput);
        
        Message carNombreEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        carNombreEditInputMessage.setId("carNombreEditInputMessage");
        carNombreEditInputMessage.setFor("carNombreEditInput");
        carNombreEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(carNombreEditInputMessage);
        
        OutputLabel carSalarioEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        carSalarioEditOutput.setFor("carSalarioEditInput");
        carSalarioEditOutput.setId("carSalarioEditOutput");
        carSalarioEditOutput.setValue("Car Salario:");
        htmlPanelGrid.getChildren().add(carSalarioEditOutput);
        
        InputText carSalarioEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        carSalarioEditInput.setId("carSalarioEditInput");
        carSalarioEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{cargoBean.cargo.carSalario}", Double.class));
        carSalarioEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(carSalarioEditInput);
        
        Message carSalarioEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        carSalarioEditInputMessage.setId("carSalarioEditInputMessage");
        carSalarioEditInputMessage.setFor("carSalarioEditInput");
        carSalarioEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(carSalarioEditInputMessage);
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateViewPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText carNombreLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        carNombreLabel.setId("carNombreLabel");
        carNombreLabel.setValue("Car Nombre:");
        htmlPanelGrid.getChildren().add(carNombreLabel);
        
        InputTextarea carNombreValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        carNombreValue.setId("carNombreValue");
        carNombreValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{cargoBean.cargo.carNombre}", String.class));
        carNombreValue.setReadonly(true);
        carNombreValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(carNombreValue);
        
        HtmlOutputText carSalarioLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        carSalarioLabel.setId("carSalarioLabel");
        carSalarioLabel.setValue("Car Salario:");
        htmlPanelGrid.getChildren().add(carSalarioLabel);
        
        HtmlOutputText carSalarioValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        carSalarioValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{cargoBean.cargo.carSalario}", String.class));
        htmlPanelGrid.getChildren().add(carSalarioValue);
        
        return htmlPanelGrid;
    }

	public Cargo getCargo() {
        if (cargo == null) {
            cargo = new Cargo();
        }
        return cargo;
    }

	public void setCargo(Cargo cargo) {
        this.cargo = cargo;
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
        findAllCargoes();
        return "cargo";
    }

	public String displayCreateDialog() {
        cargo = new Cargo();
        createDialogVisible = true;
        return "cargo";
    }

	public String persist() {
        String message = "";
        if (cargo.getCarCogigo() != null) {
            cargoService.updateCargo(cargo);
            message = "message_successfully_updated";
        } else {
            cargoService.saveCargo(cargo);
            message = "message_successfully_created";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialogWidget.hide()");
        context.execute("editDialogWidget.hide()");
        
        FacesMessage facesMessage = MessageFactory.getMessage(message, "Cargo");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllCargoes();
    }

	public String delete() {
        cargoService.deleteCargo(cargo);
        FacesMessage facesMessage = MessageFactory.getMessage("message_successfully_deleted", "Cargo");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllCargoes();
    }

	public void reset() {
        cargo = null;
        createDialogVisible = false;
    }

	public void handleDialogClose(CloseEvent event) {
        reset();
    }
}
