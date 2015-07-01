package co.edu.udistrital.rrhh.web;
import co.edu.udistrital.rrhh.domain.Concepto;
import co.edu.udistrital.rrhh.domain.Empleado;
import co.edu.udistrital.rrhh.domain.Pago;
import co.edu.udistrital.rrhh.service.ConceptoService;
import co.edu.udistrital.rrhh.service.EmpleadoService;
import co.edu.udistrital.rrhh.service.PagoService;
import co.edu.udistrital.rrhh.web.converter.ConceptoConverter;
import co.edu.udistrital.rrhh.web.converter.EmpleadoConverter;
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
import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.message.Message;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
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
public class PagoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
    PagoService pagoService;

	@Autowired
    EmpleadoService empleadoService;

	@Autowired
    ConceptoService conceptoService;

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
        columns.add("pagTipo");
        columns.add("pagValorPago");
        columns.add("pagDescripcion");
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
        
        AutoComplete pagEmpleadoCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        pagEmpleadoCreateInput.setId("pagEmpleadoCreateInput");
        pagEmpleadoCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{pagoBean.pago.pagEmpleado}", Empleado.class));
        pagEmpleadoCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{pagoBean.completePagEmpleado}", List.class, new Class[] { String.class }));
        pagEmpleadoCreateInput.setDropdown(true);
        pagEmpleadoCreateInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "pagEmpleado", String.class));
        pagEmpleadoCreateInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{pagEmpleado.empCedula} #{pagEmpleado.empNombre} #{pagEmpleado.empFechaIngreso} #{pagEmpleado.empFechaSalida}", String.class));
        pagEmpleadoCreateInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{pagEmpleado}", Empleado.class));
        pagEmpleadoCreateInput.setConverter(new EmpleadoConverter());
        pagEmpleadoCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(pagEmpleadoCreateInput);
        
        Message pagEmpleadoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        pagEmpleadoCreateInputMessage.setId("pagEmpleadoCreateInputMessage");
        pagEmpleadoCreateInputMessage.setFor("pagEmpleadoCreateInput");
        pagEmpleadoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(pagEmpleadoCreateInputMessage);
        
        OutputLabel conConceptoCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        conConceptoCreateOutput.setFor("conConceptoCreateInput");
        conConceptoCreateOutput.setId("conConceptoCreateOutput");
        conConceptoCreateOutput.setValue("Con Concepto:");
        htmlPanelGrid.getChildren().add(conConceptoCreateOutput);
        
        AutoComplete conConceptoCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        conConceptoCreateInput.setId("conConceptoCreateInput");
        conConceptoCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{pagoBean.pago.conConcepto}", Concepto.class));
        conConceptoCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{pagoBean.completeConConcepto}", List.class, new Class[] { String.class }));
        conConceptoCreateInput.setDropdown(true);
        conConceptoCreateInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "conConcepto", String.class));
        conConceptoCreateInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{conConcepto.conPago} #{conConcepto.conNombre} #{conConcepto.conDescripcion} #{conConcepto.conTipo}", String.class));
        conConceptoCreateInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{conConcepto}", Concepto.class));
        conConceptoCreateInput.setConverter(new ConceptoConverter());
        conConceptoCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(conConceptoCreateInput);
        
        Message conConceptoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        conConceptoCreateInputMessage.setId("conConceptoCreateInputMessage");
        conConceptoCreateInputMessage.setFor("conConceptoCreateInput");
        conConceptoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(conConceptoCreateInputMessage);
        
        OutputLabel pagTipoCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        pagTipoCreateOutput.setFor("pagTipoCreateInput");
        pagTipoCreateOutput.setId("pagTipoCreateOutput");
        pagTipoCreateOutput.setValue("Pag Tipo:");
        htmlPanelGrid.getChildren().add(pagTipoCreateOutput);
        
        InputText pagTipoCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        pagTipoCreateInput.setId("pagTipoCreateInput");
        pagTipoCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{pagoBean.pago.pagTipo}", String.class));
        LengthValidator pagTipoCreateInputValidator = new LengthValidator();
        pagTipoCreateInputValidator.setMaximum(20);
        pagTipoCreateInput.addValidator(pagTipoCreateInputValidator);
        pagTipoCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(pagTipoCreateInput);
        
        Message pagTipoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        pagTipoCreateInputMessage.setId("pagTipoCreateInputMessage");
        pagTipoCreateInputMessage.setFor("pagTipoCreateInput");
        pagTipoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(pagTipoCreateInputMessage);
        
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
        
        OutputLabel pagDescripcionCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        pagDescripcionCreateOutput.setFor("pagDescripcionCreateInput");
        pagDescripcionCreateOutput.setId("pagDescripcionCreateOutput");
        pagDescripcionCreateOutput.setValue("Pag Descripcion:");
        htmlPanelGrid.getChildren().add(pagDescripcionCreateOutput);
        
        InputText pagDescripcionCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        pagDescripcionCreateInput.setId("pagDescripcionCreateInput");
        pagDescripcionCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{pagoBean.pago.pagDescripcion}", String.class));
        pagDescripcionCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(pagDescripcionCreateInput);
        
        Message pagDescripcionCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        pagDescripcionCreateInputMessage.setId("pagDescripcionCreateInputMessage");
        pagDescripcionCreateInputMessage.setFor("pagDescripcionCreateInput");
        pagDescripcionCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(pagDescripcionCreateInputMessage);
        
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
        
        AutoComplete pagEmpleadoEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        pagEmpleadoEditInput.setId("pagEmpleadoEditInput");
        pagEmpleadoEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{pagoBean.pago.pagEmpleado}", Empleado.class));
        pagEmpleadoEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{pagoBean.completePagEmpleado}", List.class, new Class[] { String.class }));
        pagEmpleadoEditInput.setDropdown(true);
        pagEmpleadoEditInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "pagEmpleado", String.class));
        pagEmpleadoEditInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{pagEmpleado.empCedula} #{pagEmpleado.empNombre} #{pagEmpleado.empFechaIngreso} #{pagEmpleado.empFechaSalida}", String.class));
        pagEmpleadoEditInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{pagEmpleado}", Empleado.class));
        pagEmpleadoEditInput.setConverter(new EmpleadoConverter());
        pagEmpleadoEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(pagEmpleadoEditInput);
        
        Message pagEmpleadoEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        pagEmpleadoEditInputMessage.setId("pagEmpleadoEditInputMessage");
        pagEmpleadoEditInputMessage.setFor("pagEmpleadoEditInput");
        pagEmpleadoEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(pagEmpleadoEditInputMessage);
        
        OutputLabel conConceptoEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        conConceptoEditOutput.setFor("conConceptoEditInput");
        conConceptoEditOutput.setId("conConceptoEditOutput");
        conConceptoEditOutput.setValue("Con Concepto:");
        htmlPanelGrid.getChildren().add(conConceptoEditOutput);
        
        AutoComplete conConceptoEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        conConceptoEditInput.setId("conConceptoEditInput");
        conConceptoEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{pagoBean.pago.conConcepto}", Concepto.class));
        conConceptoEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{pagoBean.completeConConcepto}", List.class, new Class[] { String.class }));
        conConceptoEditInput.setDropdown(true);
        conConceptoEditInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "conConcepto", String.class));
        conConceptoEditInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{conConcepto.conPago} #{conConcepto.conNombre} #{conConcepto.conDescripcion} #{conConcepto.conTipo}", String.class));
        conConceptoEditInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{conConcepto}", Concepto.class));
        conConceptoEditInput.setConverter(new ConceptoConverter());
        conConceptoEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(conConceptoEditInput);
        
        Message conConceptoEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        conConceptoEditInputMessage.setId("conConceptoEditInputMessage");
        conConceptoEditInputMessage.setFor("conConceptoEditInput");
        conConceptoEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(conConceptoEditInputMessage);
        
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
        pagEmpleadoValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{pagoBean.pago.pagEmpleado}", Empleado.class));
        pagEmpleadoValue.setConverter(new EmpleadoConverter());
        htmlPanelGrid.getChildren().add(pagEmpleadoValue);
        
        HtmlOutputText conConceptoLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        conConceptoLabel.setId("conConceptoLabel");
        conConceptoLabel.setValue("Con Concepto:");
        htmlPanelGrid.getChildren().add(conConceptoLabel);
        
        HtmlOutputText conConceptoValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        conConceptoValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{pagoBean.pago.conConcepto}", Concepto.class));
        conConceptoValue.setConverter(new ConceptoConverter());
        htmlPanelGrid.getChildren().add(conConceptoValue);
        
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

	public List<Empleado> completePagEmpleado(String query) {
        List<Empleado> suggestions = new ArrayList<Empleado>();
        for (Empleado empleado : empleadoService.findAllEmpleadoes()) {
            String empleadoStr = String.valueOf(empleado.getEmpCedula() +  " "  + empleado.getEmpNombre() +  " "  + empleado.getEmpFechaIngreso() +  " "  + empleado.getEmpFechaSalida());
            if (empleadoStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(empleado);
            }
        }
        return suggestions;
    }

	public List<Concepto> completeConConcepto(String query) {
        List<Concepto> suggestions = new ArrayList<Concepto>();
        for (Concepto concepto : conceptoService.findAllConceptoes()) {
            String conceptoStr = String.valueOf(concepto.getConNombre() +  " "  + concepto.getConDescripcion() +  " "  + concepto.getConTipo());
            if (conceptoStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(concepto);
            }
        }
        return suggestions;
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
}
