package co.edu.udistrital.rrhh.web;
import co.edu.udistrital.rrhh.domain.Concepto;
import co.edu.udistrital.rrhh.service.ConceptoService;
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
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@ManagedBean(name = "conceptoBean")
@SessionScoped
@Configurable
@RooSerializable
@RooJsfManagedBean(entity = Concepto.class, beanName = "conceptoBean")
public class ConceptoBean implements Serializable {

	@Autowired
    ConceptoService conceptoService;

	private String name = "Conceptoes";

	private Concepto concepto;

	private List<Concepto> allConceptoes;

	private boolean dataVisible = false;

	private List<String> columns;

	private HtmlPanelGrid createPanelGrid;

	private HtmlPanelGrid editPanelGrid;

	private HtmlPanelGrid viewPanelGrid;

	private boolean createDialogVisible = false;

	@PostConstruct
    public void init() {
        columns = new ArrayList<String>();
        columns.add("conNombre");
        columns.add("conDescripcion");
        columns.add("conTipo");
        columns.add("conValor");
    }

	public String getName() {
        return name;
    }

	public List<String> getColumns() {
        return columns;
    }

	public List<Concepto> getAllConceptoes() {
        return allConceptoes;
    }

	public void setAllConceptoes(List<Concepto> allConceptoes) {
        this.allConceptoes = allConceptoes;
    }

	public String findAllConceptoes() {
        allConceptoes = conceptoService.findAllConceptoes();
        dataVisible = !allConceptoes.isEmpty();
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
        
        htmlPanelGrid.getChildren().add(ComponentsGenerator.getBasicOutputLabel("conIdCreateInput", "conIdCreateOutput", "Codigo"));
        
        InputText conIdCreateInput = ComponentsGenerator.getBasicInputText("conIdCreateInput",  "#{conceptoBean.concepto.conCodigo}", ComponentsGenerator.INTEGER);
        LengthValidator conIdCreateInputValidator = new LengthValidator();
        conIdCreateInputValidator.setMaximum(50);
        conIdCreateInput.addValidator(conIdCreateInputValidator);
        htmlPanelGrid.getChildren().add(conIdCreateInput);
        htmlPanelGrid.getChildren().add(ComponentsGenerator.getBasicMessage("conIdCreateInput", "conIdCreateInputMessage"));
        
        
        OutputLabel conNombreCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        conNombreCreateOutput.setFor("conNombreCreateInput");
        conNombreCreateOutput.setId("conNombreCreateOutput");
        conNombreCreateOutput.setValue("Con Nombre:");
        htmlPanelGrid.getChildren().add(conNombreCreateOutput);
        
        InputTextarea conNombreCreateInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        conNombreCreateInput.setId("conNombreCreateInput");
        conNombreCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{conceptoBean.concepto.conNombre}", String.class));
        LengthValidator conNombreCreateInputValidator = new LengthValidator();
        conNombreCreateInputValidator.setMaximum(50);
        conNombreCreateInput.addValidator(conNombreCreateInputValidator);
        conNombreCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(conNombreCreateInput);
        
        Message conNombreCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        conNombreCreateInputMessage.setId("conNombreCreateInputMessage");
        conNombreCreateInputMessage.setFor("conNombreCreateInput");
        conNombreCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(conNombreCreateInputMessage);
        
        OutputLabel conDescripcionCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        conDescripcionCreateOutput.setFor("conDescripcionCreateInput");
        conDescripcionCreateOutput.setId("conDescripcionCreateOutput");
        conDescripcionCreateOutput.setValue("Con Descripcion:");
        htmlPanelGrid.getChildren().add(conDescripcionCreateOutput);
        
        InputText conDescripcionCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        conDescripcionCreateInput.setId("conDescripcionCreateInput");
        conDescripcionCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{conceptoBean.concepto.conDescripcion}", String.class));
        conDescripcionCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(conDescripcionCreateInput);
        
        Message conDescripcionCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        conDescripcionCreateInputMessage.setId("conDescripcionCreateInputMessage");
        conDescripcionCreateInputMessage.setFor("conDescripcionCreateInput");
        conDescripcionCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(conDescripcionCreateInputMessage);
        
        OutputLabel conTipoCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        conTipoCreateOutput.setFor("conTipoCreateInput");
        conTipoCreateOutput.setId("conTipoCreateOutput");
        conTipoCreateOutput.setValue("Con Tipo:");
        htmlPanelGrid.getChildren().add(conTipoCreateOutput);
        
        InputText conTipoCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        conTipoCreateInput.setId("conTipoCreateInput");
        conTipoCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{conceptoBean.concepto.conTipo}", String.class));
        LengthValidator conTipoCreateInputValidator = new LengthValidator();
        conTipoCreateInputValidator.setMaximum(20);
        conTipoCreateInput.addValidator(conTipoCreateInputValidator);
        conTipoCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(conTipoCreateInput);
        
        Message conTipoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        conTipoCreateInputMessage.setId("conTipoCreateInputMessage");
        conTipoCreateInputMessage.setFor("conTipoCreateInput");
        conTipoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(conTipoCreateInputMessage);
        
        OutputLabel conValorCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        conValorCreateOutput.setFor("conValorCreateInput");
        conValorCreateOutput.setId("conValorCreateOutput");
        conValorCreateOutput.setValue("Con Valor:");
        htmlPanelGrid.getChildren().add(conValorCreateOutput);
        
        InputText conValorCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        conValorCreateInput.setId("conValorCreateInput");
        conValorCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{conceptoBean.concepto.conValor}", Double.class));
        conValorCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(conValorCreateInput);
        
        Message conValorCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        conValorCreateInputMessage.setId("conValorCreateInputMessage");
        conValorCreateInputMessage.setFor("conValorCreateInput");
        conValorCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(conValorCreateInputMessage);
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateEditPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        OutputLabel conNombreEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        conNombreEditOutput.setFor("conNombreEditInput");
        conNombreEditOutput.setId("conNombreEditOutput");
        conNombreEditOutput.setValue("Con Nombre:");
        htmlPanelGrid.getChildren().add(conNombreEditOutput);
        
        InputTextarea conNombreEditInput = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        conNombreEditInput.setId("conNombreEditInput");
        conNombreEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{conceptoBean.concepto.conNombre}", String.class));
        LengthValidator conNombreEditInputValidator = new LengthValidator();
        conNombreEditInputValidator.setMaximum(50);
        conNombreEditInput.addValidator(conNombreEditInputValidator);
        conNombreEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(conNombreEditInput);
        
        Message conNombreEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        conNombreEditInputMessage.setId("conNombreEditInputMessage");
        conNombreEditInputMessage.setFor("conNombreEditInput");
        conNombreEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(conNombreEditInputMessage);
        
        OutputLabel conDescripcionEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        conDescripcionEditOutput.setFor("conDescripcionEditInput");
        conDescripcionEditOutput.setId("conDescripcionEditOutput");
        conDescripcionEditOutput.setValue("Con Descripcion:");
        htmlPanelGrid.getChildren().add(conDescripcionEditOutput);
        
        InputText conDescripcionEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        conDescripcionEditInput.setId("conDescripcionEditInput");
        conDescripcionEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{conceptoBean.concepto.conDescripcion}", String.class));
        conDescripcionEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(conDescripcionEditInput);
        
        Message conDescripcionEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        conDescripcionEditInputMessage.setId("conDescripcionEditInputMessage");
        conDescripcionEditInputMessage.setFor("conDescripcionEditInput");
        conDescripcionEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(conDescripcionEditInputMessage);
        
        OutputLabel conTipoEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        conTipoEditOutput.setFor("conTipoEditInput");
        conTipoEditOutput.setId("conTipoEditOutput");
        conTipoEditOutput.setValue("Con Tipo:");
        htmlPanelGrid.getChildren().add(conTipoEditOutput);
        
        InputText conTipoEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        conTipoEditInput.setId("conTipoEditInput");
        conTipoEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{conceptoBean.concepto.conTipo}", String.class));
        LengthValidator conTipoEditInputValidator = new LengthValidator();
        conTipoEditInputValidator.setMaximum(20);
        conTipoEditInput.addValidator(conTipoEditInputValidator);
        conTipoEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(conTipoEditInput);
        
        Message conTipoEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        conTipoEditInputMessage.setId("conTipoEditInputMessage");
        conTipoEditInputMessage.setFor("conTipoEditInput");
        conTipoEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(conTipoEditInputMessage);
        
        OutputLabel conValorEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        conValorEditOutput.setFor("conValorEditInput");
        conValorEditOutput.setId("conValorEditOutput");
        conValorEditOutput.setValue("Con Valor:");
        htmlPanelGrid.getChildren().add(conValorEditOutput);
        
        InputText conValorEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        conValorEditInput.setId("conValorEditInput");
        conValorEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{conceptoBean.concepto.conValor}", Double.class));
        conValorEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(conValorEditInput);
        
        Message conValorEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        conValorEditInputMessage.setId("conValorEditInputMessage");
        conValorEditInputMessage.setFor("conValorEditInput");
        conValorEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(conValorEditInputMessage);
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateViewPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText conNombreLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        conNombreLabel.setId("conNombreLabel");
        conNombreLabel.setValue("Con Nombre:");
        htmlPanelGrid.getChildren().add(conNombreLabel);
        
        InputTextarea conNombreValue = (InputTextarea) application.createComponent(InputTextarea.COMPONENT_TYPE);
        conNombreValue.setId("conNombreValue");
        conNombreValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{conceptoBean.concepto.conNombre}", String.class));
        conNombreValue.setReadonly(true);
        conNombreValue.setDisabled(true);
        htmlPanelGrid.getChildren().add(conNombreValue);
        
        HtmlOutputText conDescripcionLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        conDescripcionLabel.setId("conDescripcionLabel");
        conDescripcionLabel.setValue("Con Descripcion:");
        htmlPanelGrid.getChildren().add(conDescripcionLabel);
        
        HtmlOutputText conDescripcionValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        conDescripcionValue.setId("conDescripcionValue");
        conDescripcionValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{conceptoBean.concepto.conDescripcion}", String.class));
        htmlPanelGrid.getChildren().add(conDescripcionValue);
        
        HtmlOutputText conTipoLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        conTipoLabel.setId("conTipoLabel");
        conTipoLabel.setValue("Con Tipo:");
        htmlPanelGrid.getChildren().add(conTipoLabel);
        
        HtmlOutputText conTipoValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        conTipoValue.setId("conTipoValue");
        conTipoValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{conceptoBean.concepto.conTipo}", String.class));
        htmlPanelGrid.getChildren().add(conTipoValue);
        
        HtmlOutputText conValorLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        conValorLabel.setId("conValorLabel");
        conValorLabel.setValue("Con Valor:");
        htmlPanelGrid.getChildren().add(conValorLabel);
        
        HtmlOutputText conValorValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        conValorValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{conceptoBean.concepto.conValor}", String.class));
        htmlPanelGrid.getChildren().add(conValorValue);
        
        return htmlPanelGrid;
    }

	public Concepto getConcepto() {
        if (concepto == null) {
            concepto = new Concepto();
        }
        return concepto;
    }

	public void setConcepto(Concepto concepto) {
        this.concepto = concepto;
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
        findAllConceptoes();
        return "concepto";
    }

	public String displayCreateDialog() {
        concepto = new Concepto();
        createDialogVisible = true;
        return "concepto";
    }

	public String persist() {
        String message = "";
        if (concepto.getConCodigo() != null) {
            conceptoService.updateConcepto(concepto);
            message = "message_successfully_updated";
        } else {
            conceptoService.saveConcepto(concepto);
            message = "message_successfully_created";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialogWidget.hide()");
        context.execute("editDialogWidget.hide()");
        
        FacesMessage facesMessage = MessageFactory.getMessage(message, "Concepto");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllConceptoes();
    }

	public String delete() {
        conceptoService.deleteConcepto(concepto);
        FacesMessage facesMessage = MessageFactory.getMessage("message_successfully_deleted", "Concepto");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllConceptoes();
    }

	public void reset() {
        concepto = null;
        createDialogVisible = false;
    }

	public void handleDialogClose(CloseEvent event) {
        reset();
    }

	private static final long serialVersionUID = 1L;
}
