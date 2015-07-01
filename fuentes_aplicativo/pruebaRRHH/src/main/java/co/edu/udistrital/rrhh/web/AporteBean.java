package co.edu.udistrital.rrhh.web;
import co.edu.udistrital.rrhh.domain.Aporte;
import co.edu.udistrital.rrhh.service.AporteService;
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
import org.primefaces.component.message.Message;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.spinner.Spinner;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@Configurable
@ManagedBean(name = "aporteBean")
@SessionScoped
@RooSerializable
@RooJsfManagedBean(entity = Aporte.class, beanName = "aporteBean")
public class AporteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
    AporteService aporteService;

	private String name = "Aportes";

	private Aporte aporte;

	private List<Aporte> allAportes;

	private boolean dataVisible = false;

	private List<String> columns;

	private HtmlPanelGrid createPanelGrid;

	private HtmlPanelGrid editPanelGrid;

	private HtmlPanelGrid viewPanelGrid;

	private boolean createDialogVisible = false;

	@PostConstruct
    public void init() {
        columns = new ArrayList<String>();
        columns.add("apoEntidad");
        columns.add("apoTipo");
        columns.add("apoPeriodo");
        columns.add("apoValor");
    }

	public String getName() {
        return name;
    }

	public List<String> getColumns() {
        return columns;
    }

	public List<Aporte> getAllAportes() {
        return allAportes;
    }

	public void setAllAportes(List<Aporte> allAportes) {
        this.allAportes = allAportes;
    }

	public String findAllAportes() {
        allAportes = aporteService.findAllAportes();
        dataVisible = !allAportes.isEmpty();
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
        
        OutputLabel apoEntidadCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        apoEntidadCreateOutput.setFor("apoEntidadCreateInput");
        apoEntidadCreateOutput.setId("apoEntidadCreateOutput");
        apoEntidadCreateOutput.setValue("Apo Entidad:");
        htmlPanelGrid.getChildren().add(apoEntidadCreateOutput);
        
        Spinner apoEntidadCreateInput = (Spinner) application.createComponent(Spinner.COMPONENT_TYPE);
        apoEntidadCreateInput.setId("apoEntidadCreateInput");
        apoEntidadCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{aporteBean.aporte.apoEntidad}", Integer.class));
        apoEntidadCreateInput.setRequired(true);
        
        htmlPanelGrid.getChildren().add(apoEntidadCreateInput);
        
        Message apoEntidadCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        apoEntidadCreateInputMessage.setId("apoEntidadCreateInputMessage");
        apoEntidadCreateInputMessage.setFor("apoEntidadCreateInput");
        apoEntidadCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(apoEntidadCreateInputMessage);
        
        OutputLabel apoTipoCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        apoTipoCreateOutput.setFor("apoTipoCreateInput");
        apoTipoCreateOutput.setId("apoTipoCreateOutput");
        apoTipoCreateOutput.setValue("Apo Tipo:");
        htmlPanelGrid.getChildren().add(apoTipoCreateOutput);
        
        InputText apoTipoCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        apoTipoCreateInput.setId("apoTipoCreateInput");
        apoTipoCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{aporteBean.aporte.apoTipo}", String.class));
        LengthValidator apoTipoCreateInputValidator = new LengthValidator();
        apoTipoCreateInputValidator.setMaximum(20);
        apoTipoCreateInput.addValidator(apoTipoCreateInputValidator);
        apoTipoCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(apoTipoCreateInput);
        
        Message apoTipoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        apoTipoCreateInputMessage.setId("apoTipoCreateInputMessage");
        apoTipoCreateInputMessage.setFor("apoTipoCreateInput");
        apoTipoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(apoTipoCreateInputMessage);
        
        OutputLabel apoPeriodoCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        apoPeriodoCreateOutput.setFor("apoPeriodoCreateInput");
        apoPeriodoCreateOutput.setId("apoPeriodoCreateOutput");
        apoPeriodoCreateOutput.setValue("Apo Periodo:");
        htmlPanelGrid.getChildren().add(apoPeriodoCreateOutput);
        
        InputText apoPeriodoCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        apoPeriodoCreateInput.setId("apoPeriodoCreateInput");
        apoPeriodoCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{aporteBean.aporte.apoPeriodo}", String.class));
        LengthValidator apoPeriodoCreateInputValidator = new LengthValidator();
        apoPeriodoCreateInputValidator.setMaximum(20);
        apoPeriodoCreateInput.addValidator(apoPeriodoCreateInputValidator);
        apoPeriodoCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(apoPeriodoCreateInput);
        
        Message apoPeriodoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        apoPeriodoCreateInputMessage.setId("apoPeriodoCreateInputMessage");
        apoPeriodoCreateInputMessage.setFor("apoPeriodoCreateInput");
        apoPeriodoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(apoPeriodoCreateInputMessage);
        
        OutputLabel apoValorCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        apoValorCreateOutput.setFor("apoValorCreateInput");
        apoValorCreateOutput.setId("apoValorCreateOutput");
        apoValorCreateOutput.setValue("Apo Valor:");
        htmlPanelGrid.getChildren().add(apoValorCreateOutput);
        
        InputText apoValorCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        apoValorCreateInput.setId("apoValorCreateInput");
        apoValorCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{aporteBean.aporte.apoValor}", Double.class));
        apoValorCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(apoValorCreateInput);
        
        Message apoValorCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        apoValorCreateInputMessage.setId("apoValorCreateInputMessage");
        apoValorCreateInputMessage.setFor("apoValorCreateInput");
        apoValorCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(apoValorCreateInputMessage);
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateEditPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        OutputLabel apoEntidadEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        apoEntidadEditOutput.setFor("apoEntidadEditInput");
        apoEntidadEditOutput.setId("apoEntidadEditOutput");
        apoEntidadEditOutput.setValue("Apo Entidad:");
        htmlPanelGrid.getChildren().add(apoEntidadEditOutput);
        
        Spinner apoEntidadEditInput = (Spinner) application.createComponent(Spinner.COMPONENT_TYPE);
        apoEntidadEditInput.setId("apoEntidadEditInput");
        apoEntidadEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{aporteBean.aporte.apoEntidad}", Integer.class));
        apoEntidadEditInput.setRequired(true);
        
        htmlPanelGrid.getChildren().add(apoEntidadEditInput);
        
        Message apoEntidadEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        apoEntidadEditInputMessage.setId("apoEntidadEditInputMessage");
        apoEntidadEditInputMessage.setFor("apoEntidadEditInput");
        apoEntidadEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(apoEntidadEditInputMessage);
        
        OutputLabel apoTipoEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        apoTipoEditOutput.setFor("apoTipoEditInput");
        apoTipoEditOutput.setId("apoTipoEditOutput");
        apoTipoEditOutput.setValue("Apo Tipo:");
        htmlPanelGrid.getChildren().add(apoTipoEditOutput);
        
        InputText apoTipoEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        apoTipoEditInput.setId("apoTipoEditInput");
        apoTipoEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{aporteBean.aporte.apoTipo}", String.class));
        LengthValidator apoTipoEditInputValidator = new LengthValidator();
        apoTipoEditInputValidator.setMaximum(20);
        apoTipoEditInput.addValidator(apoTipoEditInputValidator);
        apoTipoEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(apoTipoEditInput);
        
        Message apoTipoEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        apoTipoEditInputMessage.setId("apoTipoEditInputMessage");
        apoTipoEditInputMessage.setFor("apoTipoEditInput");
        apoTipoEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(apoTipoEditInputMessage);
        
        OutputLabel apoPeriodoEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        apoPeriodoEditOutput.setFor("apoPeriodoEditInput");
        apoPeriodoEditOutput.setId("apoPeriodoEditOutput");
        apoPeriodoEditOutput.setValue("Apo Periodo:");
        htmlPanelGrid.getChildren().add(apoPeriodoEditOutput);
        
        InputText apoPeriodoEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        apoPeriodoEditInput.setId("apoPeriodoEditInput");
        apoPeriodoEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{aporteBean.aporte.apoPeriodo}", String.class));
        LengthValidator apoPeriodoEditInputValidator = new LengthValidator();
        apoPeriodoEditInputValidator.setMaximum(20);
        apoPeriodoEditInput.addValidator(apoPeriodoEditInputValidator);
        apoPeriodoEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(apoPeriodoEditInput);
        
        Message apoPeriodoEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        apoPeriodoEditInputMessage.setId("apoPeriodoEditInputMessage");
        apoPeriodoEditInputMessage.setFor("apoPeriodoEditInput");
        apoPeriodoEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(apoPeriodoEditInputMessage);
        
        OutputLabel apoValorEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        apoValorEditOutput.setFor("apoValorEditInput");
        apoValorEditOutput.setId("apoValorEditOutput");
        apoValorEditOutput.setValue("Apo Valor:");
        htmlPanelGrid.getChildren().add(apoValorEditOutput);
        
        InputText apoValorEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        apoValorEditInput.setId("apoValorEditInput");
        apoValorEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{aporteBean.aporte.apoValor}", Double.class));
        apoValorEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(apoValorEditInput);
        
        Message apoValorEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        apoValorEditInputMessage.setId("apoValorEditInputMessage");
        apoValorEditInputMessage.setFor("apoValorEditInput");
        apoValorEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(apoValorEditInputMessage);
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateViewPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText apoEntidadLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        apoEntidadLabel.setId("apoEntidadLabel");
        apoEntidadLabel.setValue("Apo Entidad:");
        htmlPanelGrid.getChildren().add(apoEntidadLabel);
        
        HtmlOutputText apoEntidadValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        apoEntidadValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{aporteBean.aporte.apoEntidad}", String.class));
        htmlPanelGrid.getChildren().add(apoEntidadValue);
        
        HtmlOutputText apoTipoLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        apoTipoLabel.setId("apoTipoLabel");
        apoTipoLabel.setValue("Apo Tipo:");
        htmlPanelGrid.getChildren().add(apoTipoLabel);
        
        HtmlOutputText apoTipoValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        apoTipoValue.setId("apoTipoValue");
        apoTipoValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{aporteBean.aporte.apoTipo}", String.class));
        htmlPanelGrid.getChildren().add(apoTipoValue);
        
        HtmlOutputText apoPeriodoLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        apoPeriodoLabel.setId("apoPeriodoLabel");
        apoPeriodoLabel.setValue("Apo Periodo:");
        htmlPanelGrid.getChildren().add(apoPeriodoLabel);
        
        HtmlOutputText apoPeriodoValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        apoPeriodoValue.setId("apoPeriodoValue");
        apoPeriodoValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{aporteBean.aporte.apoPeriodo}", String.class));
        htmlPanelGrid.getChildren().add(apoPeriodoValue);
        
        HtmlOutputText apoValorLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        apoValorLabel.setId("apoValorLabel");
        apoValorLabel.setValue("Apo Valor:");
        htmlPanelGrid.getChildren().add(apoValorLabel);
        
        HtmlOutputText apoValorValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        apoValorValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{aporteBean.aporte.apoValor}", String.class));
        htmlPanelGrid.getChildren().add(apoValorValue);
        
        return htmlPanelGrid;
    }

	public Aporte getAporte() {
        if (aporte == null) {
            aporte = new Aporte();
        }
        return aporte;
    }

	public void setAporte(Aporte aporte) {
        this.aporte = aporte;
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
        findAllAportes();
        return "aporte";
    }

	public String displayCreateDialog() {
        aporte = new Aporte();
        createDialogVisible = true;
        return "aporte";
    }

	public String persist() {
        String message = "";
        if (aporte.getApoCodigo() != null) {
            aporteService.updateAporte(aporte);
            message = "message_successfully_updated";
        } else {
            aporteService.saveAporte(aporte);
            message = "message_successfully_created";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialogWidget.hide()");
        context.execute("editDialogWidget.hide()");
        
        FacesMessage facesMessage = MessageFactory.getMessage(message, "Aporte");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllAportes();
    }

	public String delete() {
        aporteService.deleteAporte(aporte);
        FacesMessage facesMessage = MessageFactory.getMessage("message_successfully_deleted", "Aporte");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllAportes();
    }

	public void reset() {
        aporte = null;
        createDialogVisible = false;
    }

	public void handleDialogClose(CloseEvent event) {
        reset();
    }
}
