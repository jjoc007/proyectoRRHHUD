package co.edu.udistrital.rrhh.web;
import co.edu.udistrital.rrhh.domain.Pago;
import co.edu.udistrital.rrhh.service.PagoService;
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
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.primefaces.component.spinner.Spinner;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@ManagedBean(name = "pagoBean")
@SessionScoped
@Configurable
@RooSerializable
@RooJsfManagedBean(entity = Pago.class, beanName = "pagoBean")
public class PagoBean implements Serializable  {

	@Autowired
    PagoService pagoService;

	private String name = "Pagoes";

	private Pago pago;

	private List<Pago> allPagoes;

	private boolean dataVisible = false;

	private List<String> columns;

	private HtmlPanelGrid createPanelGrid;

	private HtmlPanelGrid editPanelGrid;

	private HtmlPanelGrid viewPanelGrid;

	private boolean createDialogVisible = false;

	@PostConstruct
    public void init() {
        columns = new ArrayList<String>();
        columns.add("pagEmpleado");
        columns.add("pagValorPago");
        columns.add("conConcepto");
    }

	public String getName() {
        return name;
    }

	public List<String> getColumns() {
        return columns;
    }

	public List<Pago> getAllPagoes() {
        return allPagoes;
    }

	public void setAllPagoes(List<Pago> allPagoes) {
        this.allPagoes = allPagoes;
    }

	public String findAllPagoes() {
        allPagoes = pagoService.findAllPagoes();
        dataVisible = !allPagoes.isEmpty();
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
        
        OutputLabel pagEmpleadoCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        pagEmpleadoCreateOutput.setFor("pagEmpleadoCreateInput");
        pagEmpleadoCreateOutput.setId("pagEmpleadoCreateOutput");
        pagEmpleadoCreateOutput.setValue("Pag Empleado:");
        htmlPanelGrid.getChildren().add(pagEmpleadoCreateOutput);
        
        Spinner pagEmpleadoCreateInput = (Spinner) application.createComponent(Spinner.COMPONENT_TYPE);
        pagEmpleadoCreateInput.setId("pagEmpleadoCreateInput");
        pagEmpleadoCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{pagoBean.pago.pagEmpleado}", Integer.class));
        pagEmpleadoCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(pagEmpleadoCreateInput);
        
        Message pagEmpleadoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        pagEmpleadoCreateInputMessage.setId("pagEmpleadoCreateInputMessage");
        pagEmpleadoCreateInputMessage.setFor("pagEmpleadoCreateInput");
        pagEmpleadoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(pagEmpleadoCreateInputMessage);
        
        OutputLabel pagValorPagoCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        pagValorPagoCreateOutput.setFor("pagValorPagoCreateInput");
        pagValorPagoCreateOutput.setId("pagValorPagoCreateOutput");
        pagValorPagoCreateOutput.setValue("Pag Valor Pago:");
        htmlPanelGrid.getChildren().add(pagValorPagoCreateOutput);
        
        InputText pagValorPagoCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        pagValorPagoCreateInput.setId("pagValorPagoCreateInput");
        pagValorPagoCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{pagoBean.pago.pagValorPago}", Double.class));
        pagValorPagoCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(pagValorPagoCreateInput);
        
        Message pagValorPagoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        pagValorPagoCreateInputMessage.setId("pagValorPagoCreateInputMessage");
        pagValorPagoCreateInputMessage.setFor("pagValorPagoCreateInput");
        pagValorPagoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(pagValorPagoCreateInputMessage);
        
        OutputLabel pagEstadoCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        pagEstadoCreateOutput.setFor("pagEstadoCreateInput");
        pagEstadoCreateOutput.setId("pagEstadoCreateOutput");
        pagEstadoCreateOutput.setValue("Pag Estado:");
        htmlPanelGrid.getChildren().add(pagEstadoCreateOutput);
        
        SelectBooleanCheckbox pagEstadoCreateInput = (SelectBooleanCheckbox) application.createComponent(SelectBooleanCheckbox.COMPONENT_TYPE);
        pagEstadoCreateInput.setId("pagEstadoCreateInput");
        pagEstadoCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{pagoBean.pago.pagEstado}", Boolean.class));
        pagEstadoCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(pagEstadoCreateInput);
        
        Message pagEstadoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        pagEstadoCreateInputMessage.setId("pagEstadoCreateInputMessage");
        pagEstadoCreateInputMessage.setFor("pagEstadoCreateInput");
        pagEstadoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(pagEstadoCreateInputMessage);
        
        OutputLabel conConceptoCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        conConceptoCreateOutput.setFor("conConceptoCreateInput");
        conConceptoCreateOutput.setId("conConceptoCreateOutput");
        conConceptoCreateOutput.setValue("Con Concepto:");
        htmlPanelGrid.getChildren().add(conConceptoCreateOutput);
        
        Spinner conConceptoCreateInput = (Spinner) application.createComponent(Spinner.COMPONENT_TYPE);
        conConceptoCreateInput.setId("conConceptoCreateInput");
        conConceptoCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{pagoBean.pago.conConcepto}", Integer.class));
        conConceptoCreateInput.setRequired(true);
        
        htmlPanelGrid.getChildren().add(conConceptoCreateInput);
        
        Message conConceptoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        conConceptoCreateInputMessage.setId("conConceptoCreateInputMessage");
        conConceptoCreateInputMessage.setFor("conConceptoCreateInput");
        conConceptoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(conConceptoCreateInputMessage);
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateEditPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        OutputLabel pagEmpleadoEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        pagEmpleadoEditOutput.setFor("pagEmpleadoEditInput");
        pagEmpleadoEditOutput.setId("pagEmpleadoEditOutput");
        pagEmpleadoEditOutput.setValue("Pag Empleado:");
        htmlPanelGrid.getChildren().add(pagEmpleadoEditOutput);
        
        Spinner pagEmpleadoEditInput = (Spinner) application.createComponent(Spinner.COMPONENT_TYPE);
        pagEmpleadoEditInput.setId("pagEmpleadoEditInput");
        pagEmpleadoEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{pagoBean.pago.pagEmpleado}", Integer.class));
        pagEmpleadoEditInput.setRequired(true);
        
        htmlPanelGrid.getChildren().add(pagEmpleadoEditInput);
        
        Message pagEmpleadoEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        pagEmpleadoEditInputMessage.setId("pagEmpleadoEditInputMessage");
        pagEmpleadoEditInputMessage.setFor("pagEmpleadoEditInput");
        pagEmpleadoEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(pagEmpleadoEditInputMessage);
        
        OutputLabel pagTipoEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        pagTipoEditOutput.setFor("pagTipoEditInput");
        pagTipoEditOutput.setId("pagTipoEditOutput");
        pagTipoEditOutput.setValue("Pag Tipo:");
        htmlPanelGrid.getChildren().add(pagTipoEditOutput);
        
        InputText pagTipoEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        pagTipoEditInput.setId("pagTipoEditInput");
        pagTipoEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{pagoBean.pago.pagTipo}", String.class));
        LengthValidator pagTipoEditInputValidator = new LengthValidator();
        pagTipoEditInputValidator.setMaximum(20);
        pagTipoEditInput.addValidator(pagTipoEditInputValidator);
        pagTipoEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(pagTipoEditInput);
        
        Message pagTipoEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        pagTipoEditInputMessage.setId("pagTipoEditInputMessage");
        pagTipoEditInputMessage.setFor("pagTipoEditInput");
        pagTipoEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(pagTipoEditInputMessage);
        
        OutputLabel pagValorPagoEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        pagValorPagoEditOutput.setFor("pagValorPagoEditInput");
        pagValorPagoEditOutput.setId("pagValorPagoEditOutput");
        pagValorPagoEditOutput.setValue("Pag Valor Pago:");
        htmlPanelGrid.getChildren().add(pagValorPagoEditOutput);
        
        InputText pagValorPagoEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        pagValorPagoEditInput.setId("pagValorPagoEditInput");
        pagValorPagoEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{pagoBean.pago.pagValorPago}", Double.class));
        pagValorPagoEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(pagValorPagoEditInput);
        
        Message pagValorPagoEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        pagValorPagoEditInputMessage.setId("pagValorPagoEditInputMessage");
        pagValorPagoEditInputMessage.setFor("pagValorPagoEditInput");
        pagValorPagoEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(pagValorPagoEditInputMessage);
        
        OutputLabel pagDescripcionEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        pagDescripcionEditOutput.setFor("pagDescripcionEditInput");
        pagDescripcionEditOutput.setId("pagDescripcionEditOutput");
        pagDescripcionEditOutput.setValue("Pag Descripcion:");
        htmlPanelGrid.getChildren().add(pagDescripcionEditOutput);
        
        InputText pagDescripcionEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        pagDescripcionEditInput.setId("pagDescripcionEditInput");
        pagDescripcionEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{pagoBean.pago.pagDescripcion}", String.class));
        pagDescripcionEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(pagDescripcionEditInput);
        
        Message pagDescripcionEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        pagDescripcionEditInputMessage.setId("pagDescripcionEditInputMessage");
        pagDescripcionEditInputMessage.setFor("pagDescripcionEditInput");
        pagDescripcionEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(pagDescripcionEditInputMessage);
        
        OutputLabel pagEstadoEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        pagEstadoEditOutput.setFor("pagEstadoEditInput");
        pagEstadoEditOutput.setId("pagEstadoEditOutput");
        pagEstadoEditOutput.setValue("Pag Estado:");
        htmlPanelGrid.getChildren().add(pagEstadoEditOutput);
        
        SelectBooleanCheckbox pagEstadoEditInput = (SelectBooleanCheckbox) application.createComponent(SelectBooleanCheckbox.COMPONENT_TYPE);
        pagEstadoEditInput.setId("pagEstadoEditInput");
        pagEstadoEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{pagoBean.pago.pagEstado}", Boolean.class));
        pagEstadoEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(pagEstadoEditInput);
        
        Message pagEstadoEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        pagEstadoEditInputMessage.setId("pagEstadoEditInputMessage");
        pagEstadoEditInputMessage.setFor("pagEstadoEditInput");
        pagEstadoEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(pagEstadoEditInputMessage);
        
        OutputLabel conConceptoEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        conConceptoEditOutput.setFor("conConceptoEditInput");
        conConceptoEditOutput.setId("conConceptoEditOutput");
        conConceptoEditOutput.setValue("Con Concepto:");
        htmlPanelGrid.getChildren().add(conConceptoEditOutput);
        
        Spinner conConceptoEditInput = (Spinner) application.createComponent(Spinner.COMPONENT_TYPE);
        conConceptoEditInput.setId("conConceptoEditInput");
        conConceptoEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{pagoBean.pago.conConcepto}", Integer.class));
        conConceptoEditInput.setRequired(true);
        
        htmlPanelGrid.getChildren().add(conConceptoEditInput);
        
        Message conConceptoEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        conConceptoEditInputMessage.setId("conConceptoEditInputMessage");
        conConceptoEditInputMessage.setFor("conConceptoEditInput");
        conConceptoEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(conConceptoEditInputMessage);
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateViewPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText pagEmpleadoLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        pagEmpleadoLabel.setId("pagEmpleadoLabel");
        pagEmpleadoLabel.setValue("Pag Empleado:");
        htmlPanelGrid.getChildren().add(pagEmpleadoLabel);
        
        HtmlOutputText pagEmpleadoValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        pagEmpleadoValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{pagoBean.pago.pagEmpleado}", String.class));
        htmlPanelGrid.getChildren().add(pagEmpleadoValue);
        
        HtmlOutputText pagTipoLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        pagTipoLabel.setId("pagTipoLabel");
        pagTipoLabel.setValue("Pag Tipo:");
        htmlPanelGrid.getChildren().add(pagTipoLabel);
        
        HtmlOutputText pagTipoValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        pagTipoValue.setId("pagTipoValue");
        pagTipoValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{pagoBean.pago.pagTipo}", String.class));
        htmlPanelGrid.getChildren().add(pagTipoValue);
        
        HtmlOutputText pagValorPagoLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        pagValorPagoLabel.setId("pagValorPagoLabel");
        pagValorPagoLabel.setValue("Pag Valor Pago:");
        htmlPanelGrid.getChildren().add(pagValorPagoLabel);
        
        HtmlOutputText pagValorPagoValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        pagValorPagoValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{pagoBean.pago.pagValorPago}", String.class));
        htmlPanelGrid.getChildren().add(pagValorPagoValue);
        
        HtmlOutputText pagDescripcionLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        pagDescripcionLabel.setId("pagDescripcionLabel");
        pagDescripcionLabel.setValue("Pag Descripcion:");
        htmlPanelGrid.getChildren().add(pagDescripcionLabel);
        
        HtmlOutputText pagDescripcionValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        pagDescripcionValue.setId("pagDescripcionValue");
        pagDescripcionValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{pagoBean.pago.pagDescripcion}", String.class));
        htmlPanelGrid.getChildren().add(pagDescripcionValue);
        
        HtmlOutputText pagEstadoLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        pagEstadoLabel.setId("pagEstadoLabel");
        pagEstadoLabel.setValue("Pag Estado:");
        htmlPanelGrid.getChildren().add(pagEstadoLabel);
        
        HtmlOutputText pagEstadoValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        pagEstadoValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{pagoBean.pago.pagEstado}", String.class));
        htmlPanelGrid.getChildren().add(pagEstadoValue);
        
        HtmlOutputText conConceptoLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        conConceptoLabel.setId("conConceptoLabel");
        conConceptoLabel.setValue("Con Concepto:");
        htmlPanelGrid.getChildren().add(conConceptoLabel);
        
        HtmlOutputText conConceptoValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        conConceptoValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{pagoBean.pago.conConcepto}", String.class));
        htmlPanelGrid.getChildren().add(conConceptoValue);
        
        return htmlPanelGrid;
    }

	public Pago getPago() {
        if (pago == null) {
            pago = new Pago();
        }
        return pago;
    }

	public void setPago(Pago pago) {
        this.pago = pago;
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
        findAllPagoes();
        return "pago";
    }

	public String displayCreateDialog() {
        pago = new Pago();
        createDialogVisible = true;
        return "pago";
    }

	public String persist() {
        String message = "";
        if (pago.getPagCodigo() != null) {
            pagoService.updatePago(pago);
            message = "message_successfully_updated";
        } else {
            pagoService.savePago(pago);
            message = "message_successfully_created";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialogWidget.hide()");
        context.execute("editDialogWidget.hide()");
        
        FacesMessage facesMessage = MessageFactory.getMessage(message, "Pago");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllPagoes();
    }

	public String delete() {
        pagoService.deletePago(pago);
        FacesMessage facesMessage = MessageFactory.getMessage("message_successfully_deleted", "Pago");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllPagoes();
    }

	public void reset() {
        pago = null;
        createDialogVisible = false;
    }

	public void handleDialogClose(CloseEvent event) {
        reset();
    }

	private static final long serialVersionUID = 1L;
}
