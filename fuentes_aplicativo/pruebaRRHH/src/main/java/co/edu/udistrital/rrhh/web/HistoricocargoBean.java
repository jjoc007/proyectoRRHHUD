package co.edu.udistrital.rrhh.web;
import co.edu.udistrital.rrhh.domain.Historicocargo;
import co.edu.udistrital.rrhh.service.HistoricocargoService;
import co.edu.udistrital.rrhh.web.util.MessageFactory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.faces.convert.DateTimeConverter;
import org.primefaces.component.calendar.Calendar;
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
@ManagedBean(name = "historicocargoBean")
@SessionScoped
@RooSerializable
@RooJsfManagedBean(entity = Historicocargo.class, beanName = "historicocargoBean")
public class HistoricocargoBean implements Serializable {

	@Autowired
    HistoricocargoService historicocargoService;

	private String name = "Historicocargoes";

	private Historicocargo historicocargo;

	private List<Historicocargo> allHistoricocargoes;

	private boolean dataVisible = false;

	private List<String> columns;

	private HtmlPanelGrid createPanelGrid;

	private HtmlPanelGrid editPanelGrid;

	private HtmlPanelGrid viewPanelGrid;

	private boolean createDialogVisible = false;

	@PostConstruct
    public void init() {
        columns = new ArrayList<String>();
        columns.add("hisEmpleado");
        columns.add("hisCargo");
        columns.add("hisFechaInicio");
        columns.add("hisFechaFinal");
        columns.add("hisSalario");
    }

	public String getName() {
        return name;
    }

	public List<String> getColumns() {
        return columns;
    }

	public List<Historicocargo> getAllHistoricocargoes() {
        return allHistoricocargoes;
    }

	public void setAllHistoricocargoes(List<Historicocargo> allHistoricocargoes) {
        this.allHistoricocargoes = allHistoricocargoes;
    }

	public String findAllHistoricocargoes() {
        allHistoricocargoes = historicocargoService.findAllHistoricocargoes();
        dataVisible = !allHistoricocargoes.isEmpty();
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
        
        OutputLabel hisEmpleadoCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        hisEmpleadoCreateOutput.setFor("hisEmpleadoCreateInput");
        hisEmpleadoCreateOutput.setId("hisEmpleadoCreateOutput");
        hisEmpleadoCreateOutput.setValue("His Empleado:");
        htmlPanelGrid.getChildren().add(hisEmpleadoCreateOutput);
        
        Spinner hisEmpleadoCreateInput = (Spinner) application.createComponent(Spinner.COMPONENT_TYPE);
        hisEmpleadoCreateInput.setId("hisEmpleadoCreateInput");
        hisEmpleadoCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{historicocargoBean.historicocargo.hisEmpleado}", Integer.class));
        hisEmpleadoCreateInput.setRequired(true);
        
        htmlPanelGrid.getChildren().add(hisEmpleadoCreateInput);
        
        Message hisEmpleadoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        hisEmpleadoCreateInputMessage.setId("hisEmpleadoCreateInputMessage");
        hisEmpleadoCreateInputMessage.setFor("hisEmpleadoCreateInput");
        hisEmpleadoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(hisEmpleadoCreateInputMessage);
        
        OutputLabel hisCargoCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        hisCargoCreateOutput.setFor("hisCargoCreateInput");
        hisCargoCreateOutput.setId("hisCargoCreateOutput");
        hisCargoCreateOutput.setValue("His Cargo:");
        htmlPanelGrid.getChildren().add(hisCargoCreateOutput);
        
        Spinner hisCargoCreateInput = (Spinner) application.createComponent(Spinner.COMPONENT_TYPE);
        hisCargoCreateInput.setId("hisCargoCreateInput");
        hisCargoCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{historicocargoBean.historicocargo.hisCargo}", Integer.class));
        hisCargoCreateInput.setRequired(true);
        
        htmlPanelGrid.getChildren().add(hisCargoCreateInput);
        
        Message hisCargoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        hisCargoCreateInputMessage.setId("hisCargoCreateInputMessage");
        hisCargoCreateInputMessage.setFor("hisCargoCreateInput");
        hisCargoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(hisCargoCreateInputMessage);
        
        OutputLabel hisFechaInicioCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        hisFechaInicioCreateOutput.setFor("hisFechaInicioCreateInput");
        hisFechaInicioCreateOutput.setId("hisFechaInicioCreateOutput");
        hisFechaInicioCreateOutput.setValue("His Fecha Inicio:");
        htmlPanelGrid.getChildren().add(hisFechaInicioCreateOutput);
        
        Calendar hisFechaInicioCreateInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        hisFechaInicioCreateInput.setId("hisFechaInicioCreateInput");
        hisFechaInicioCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{historicocargoBean.historicocargo.hisFechaInicio}", Date.class));
        hisFechaInicioCreateInput.setNavigator(true);
        hisFechaInicioCreateInput.setEffect("slideDown");
        hisFechaInicioCreateInput.setPattern("dd/MM/yyyy");
        hisFechaInicioCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(hisFechaInicioCreateInput);
        
        Message hisFechaInicioCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        hisFechaInicioCreateInputMessage.setId("hisFechaInicioCreateInputMessage");
        hisFechaInicioCreateInputMessage.setFor("hisFechaInicioCreateInput");
        hisFechaInicioCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(hisFechaInicioCreateInputMessage);
        
        OutputLabel hisFechaFinalCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        hisFechaFinalCreateOutput.setFor("hisFechaFinalCreateInput");
        hisFechaFinalCreateOutput.setId("hisFechaFinalCreateOutput");
        hisFechaFinalCreateOutput.setValue("His Fecha Final:");
        htmlPanelGrid.getChildren().add(hisFechaFinalCreateOutput);
        
        Calendar hisFechaFinalCreateInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        hisFechaFinalCreateInput.setId("hisFechaFinalCreateInput");
        hisFechaFinalCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{historicocargoBean.historicocargo.hisFechaFinal}", Date.class));
        hisFechaFinalCreateInput.setNavigator(true);
        hisFechaFinalCreateInput.setEffect("slideDown");
        hisFechaFinalCreateInput.setPattern("dd/MM/yyyy");
        hisFechaFinalCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(hisFechaFinalCreateInput);
        
        Message hisFechaFinalCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        hisFechaFinalCreateInputMessage.setId("hisFechaFinalCreateInputMessage");
        hisFechaFinalCreateInputMessage.setFor("hisFechaFinalCreateInput");
        hisFechaFinalCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(hisFechaFinalCreateInputMessage);
        
        OutputLabel hisSalarioCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        hisSalarioCreateOutput.setFor("hisSalarioCreateInput");
        hisSalarioCreateOutput.setId("hisSalarioCreateOutput");
        hisSalarioCreateOutput.setValue("His Salario:");
        htmlPanelGrid.getChildren().add(hisSalarioCreateOutput);
        
        InputText hisSalarioCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        hisSalarioCreateInput.setId("hisSalarioCreateInput");
        hisSalarioCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{historicocargoBean.historicocargo.hisSalario}", Double.class));
        hisSalarioCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(hisSalarioCreateInput);
        
        Message hisSalarioCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        hisSalarioCreateInputMessage.setId("hisSalarioCreateInputMessage");
        hisSalarioCreateInputMessage.setFor("hisSalarioCreateInput");
        hisSalarioCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(hisSalarioCreateInputMessage);
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateEditPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        OutputLabel hisEmpleadoEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        hisEmpleadoEditOutput.setFor("hisEmpleadoEditInput");
        hisEmpleadoEditOutput.setId("hisEmpleadoEditOutput");
        hisEmpleadoEditOutput.setValue("His Empleado:");
        htmlPanelGrid.getChildren().add(hisEmpleadoEditOutput);
        
        Spinner hisEmpleadoEditInput = (Spinner) application.createComponent(Spinner.COMPONENT_TYPE);
        hisEmpleadoEditInput.setId("hisEmpleadoEditInput");
        hisEmpleadoEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{historicocargoBean.historicocargo.hisEmpleado}", Integer.class));
        hisEmpleadoEditInput.setRequired(true);
        
        htmlPanelGrid.getChildren().add(hisEmpleadoEditInput);
        
        Message hisEmpleadoEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        hisEmpleadoEditInputMessage.setId("hisEmpleadoEditInputMessage");
        hisEmpleadoEditInputMessage.setFor("hisEmpleadoEditInput");
        hisEmpleadoEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(hisEmpleadoEditInputMessage);
        
        OutputLabel hisCargoEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        hisCargoEditOutput.setFor("hisCargoEditInput");
        hisCargoEditOutput.setId("hisCargoEditOutput");
        hisCargoEditOutput.setValue("His Cargo:");
        htmlPanelGrid.getChildren().add(hisCargoEditOutput);
        
        Spinner hisCargoEditInput = (Spinner) application.createComponent(Spinner.COMPONENT_TYPE);
        hisCargoEditInput.setId("hisCargoEditInput");
        hisCargoEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{historicocargoBean.historicocargo.hisCargo}", Integer.class));
        hisCargoEditInput.setRequired(true);
        
        htmlPanelGrid.getChildren().add(hisCargoEditInput);
        
        Message hisCargoEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        hisCargoEditInputMessage.setId("hisCargoEditInputMessage");
        hisCargoEditInputMessage.setFor("hisCargoEditInput");
        hisCargoEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(hisCargoEditInputMessage);
        
        OutputLabel hisFechaInicioEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        hisFechaInicioEditOutput.setFor("hisFechaInicioEditInput");
        hisFechaInicioEditOutput.setId("hisFechaInicioEditOutput");
        hisFechaInicioEditOutput.setValue("His Fecha Inicio:");
        htmlPanelGrid.getChildren().add(hisFechaInicioEditOutput);
        
        Calendar hisFechaInicioEditInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        hisFechaInicioEditInput.setId("hisFechaInicioEditInput");
        hisFechaInicioEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{historicocargoBean.historicocargo.hisFechaInicio}", Date.class));
        hisFechaInicioEditInput.setNavigator(true);
        hisFechaInicioEditInput.setEffect("slideDown");
        hisFechaInicioEditInput.setPattern("dd/MM/yyyy");
        hisFechaInicioEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(hisFechaInicioEditInput);
        
        Message hisFechaInicioEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        hisFechaInicioEditInputMessage.setId("hisFechaInicioEditInputMessage");
        hisFechaInicioEditInputMessage.setFor("hisFechaInicioEditInput");
        hisFechaInicioEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(hisFechaInicioEditInputMessage);
        
        OutputLabel hisFechaFinalEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        hisFechaFinalEditOutput.setFor("hisFechaFinalEditInput");
        hisFechaFinalEditOutput.setId("hisFechaFinalEditOutput");
        hisFechaFinalEditOutput.setValue("His Fecha Final:");
        htmlPanelGrid.getChildren().add(hisFechaFinalEditOutput);
        
        Calendar hisFechaFinalEditInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        hisFechaFinalEditInput.setId("hisFechaFinalEditInput");
        hisFechaFinalEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{historicocargoBean.historicocargo.hisFechaFinal}", Date.class));
        hisFechaFinalEditInput.setNavigator(true);
        hisFechaFinalEditInput.setEffect("slideDown");
        hisFechaFinalEditInput.setPattern("dd/MM/yyyy");
        hisFechaFinalEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(hisFechaFinalEditInput);
        
        Message hisFechaFinalEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        hisFechaFinalEditInputMessage.setId("hisFechaFinalEditInputMessage");
        hisFechaFinalEditInputMessage.setFor("hisFechaFinalEditInput");
        hisFechaFinalEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(hisFechaFinalEditInputMessage);
        
        OutputLabel hisSalarioEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        hisSalarioEditOutput.setFor("hisSalarioEditInput");
        hisSalarioEditOutput.setId("hisSalarioEditOutput");
        hisSalarioEditOutput.setValue("His Salario:");
        htmlPanelGrid.getChildren().add(hisSalarioEditOutput);
        
        InputText hisSalarioEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        hisSalarioEditInput.setId("hisSalarioEditInput");
        hisSalarioEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{historicocargoBean.historicocargo.hisSalario}", Double.class));
        hisSalarioEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(hisSalarioEditInput);
        
        Message hisSalarioEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        hisSalarioEditInputMessage.setId("hisSalarioEditInputMessage");
        hisSalarioEditInputMessage.setFor("hisSalarioEditInput");
        hisSalarioEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(hisSalarioEditInputMessage);
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateViewPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText hisEmpleadoLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        hisEmpleadoLabel.setId("hisEmpleadoLabel");
        hisEmpleadoLabel.setValue("His Empleado:");
        htmlPanelGrid.getChildren().add(hisEmpleadoLabel);
        
        HtmlOutputText hisEmpleadoValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        hisEmpleadoValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{historicocargoBean.historicocargo.hisEmpleado}", String.class));
        htmlPanelGrid.getChildren().add(hisEmpleadoValue);
        
        HtmlOutputText hisCargoLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        hisCargoLabel.setId("hisCargoLabel");
        hisCargoLabel.setValue("His Cargo:");
        htmlPanelGrid.getChildren().add(hisCargoLabel);
        
        HtmlOutputText hisCargoValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        hisCargoValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{historicocargoBean.historicocargo.hisCargo}", String.class));
        htmlPanelGrid.getChildren().add(hisCargoValue);
        
        HtmlOutputText hisFechaInicioLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        hisFechaInicioLabel.setId("hisFechaInicioLabel");
        hisFechaInicioLabel.setValue("His Fecha Inicio:");
        htmlPanelGrid.getChildren().add(hisFechaInicioLabel);
        
        HtmlOutputText hisFechaInicioValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        hisFechaInicioValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{historicocargoBean.historicocargo.hisFechaInicio}", Calendar.class));
        DateTimeConverter hisFechaInicioValueConverter = (DateTimeConverter) application.createConverter(DateTimeConverter.CONVERTER_ID);
        hisFechaInicioValueConverter.setPattern("dd/MM/yyyy");
        hisFechaInicioValue.setConverter(hisFechaInicioValueConverter);
        htmlPanelGrid.getChildren().add(hisFechaInicioValue);
        
        HtmlOutputText hisFechaFinalLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        hisFechaFinalLabel.setId("hisFechaFinalLabel");
        hisFechaFinalLabel.setValue("His Fecha Final:");
        htmlPanelGrid.getChildren().add(hisFechaFinalLabel);
        
        HtmlOutputText hisFechaFinalValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        hisFechaFinalValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{historicocargoBean.historicocargo.hisFechaFinal}", Calendar.class));
        DateTimeConverter hisFechaFinalValueConverter = (DateTimeConverter) application.createConverter(DateTimeConverter.CONVERTER_ID);
        hisFechaFinalValueConverter.setPattern("dd/MM/yyyy");
        hisFechaFinalValue.setConverter(hisFechaFinalValueConverter);
        htmlPanelGrid.getChildren().add(hisFechaFinalValue);
        
        HtmlOutputText hisSalarioLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        hisSalarioLabel.setId("hisSalarioLabel");
        hisSalarioLabel.setValue("His Salario:");
        htmlPanelGrid.getChildren().add(hisSalarioLabel);
        
        HtmlOutputText hisSalarioValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        hisSalarioValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{historicocargoBean.historicocargo.hisSalario}", String.class));
        htmlPanelGrid.getChildren().add(hisSalarioValue);
        
        return htmlPanelGrid;
    }

	public Historicocargo getHistoricocargo() {
        if (historicocargo == null) {
            historicocargo = new Historicocargo();
        }
        return historicocargo;
    }

	public void setHistoricocargo(Historicocargo historicocargo) {
        this.historicocargo = historicocargo;
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
        findAllHistoricocargoes();
        return "historicocargo";
    }

	public String displayCreateDialog() {
        historicocargo = new Historicocargo();
        createDialogVisible = true;
        return "historicocargo";
    }

	public String persist() {
        String message = "";
        if (historicocargo.getHisCodigo() != null) {
            historicocargoService.updateHistoricocargo(historicocargo);
            message = "message_successfully_updated";
        } else {
            historicocargoService.saveHistoricocargo(historicocargo);
            message = "message_successfully_created";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialogWidget.hide()");
        context.execute("editDialogWidget.hide()");
        
        FacesMessage facesMessage = MessageFactory.getMessage(message, "Historicocargo");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllHistoricocargoes();
    }

	public String delete() {
        historicocargoService.deleteHistoricocargo(historicocargo);
        FacesMessage facesMessage = MessageFactory.getMessage("message_successfully_deleted", "Historicocargo");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllHistoricocargoes();
    }

	public void reset() {
        historicocargo = null;
        createDialogVisible = false;
    }

	public void handleDialogClose(CloseEvent event) {
        reset();
    }

	private static final long serialVersionUID = 1L;
}
