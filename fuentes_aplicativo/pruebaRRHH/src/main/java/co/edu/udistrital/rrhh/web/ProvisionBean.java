package co.edu.udistrital.rrhh.web;
import co.edu.udistrital.rrhh.domain.Provision;
import co.edu.udistrital.rrhh.service.ProvisionService;
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
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.validator.LengthValidator;

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
@ManagedBean(name = "provisionBean")
@SessionScoped
@RooSerializable
@RooJsfManagedBean(entity = Provision.class, beanName = "provisionBean")
public class ProvisionBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
    ProvisionService provisionService;

	private String name = "Provisions";

	private Provision provision;

	private List<Provision> allProvisions;

	private boolean dataVisible = false;

	private List<String> columns;

	private HtmlPanelGrid createPanelGrid;

	private HtmlPanelGrid editPanelGrid;

	private HtmlPanelGrid viewPanelGrid;

	private boolean createDialogVisible = false;

	@PostConstruct
    public void init() {
        columns = new ArrayList<String>();
        columns.add("proEmpleado");
        columns.add("proConcepto");
        columns.add("proPeriodo");
        columns.add("proValor");
        columns.add("proEstado");
    }

	public String getName() {
        return name;
    }

	public List<String> getColumns() {
        return columns;
    }

	public List<Provision> getAllProvisions() {
        return allProvisions;
    }

	public void setAllProvisions(List<Provision> allProvisions) {
        this.allProvisions = allProvisions;
    }

	public String findAllProvisions() {
        allProvisions = provisionService.findAllProvisions();
        dataVisible = !allProvisions.isEmpty();
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
        
        OutputLabel proEmpleadoCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        proEmpleadoCreateOutput.setFor("proEmpleadoCreateInput");
        proEmpleadoCreateOutput.setId("proEmpleadoCreateOutput");
        proEmpleadoCreateOutput.setValue("Pro Empleado:");
        htmlPanelGrid.getChildren().add(proEmpleadoCreateOutput);
        
        Spinner proEmpleadoCreateInput = (Spinner) application.createComponent(Spinner.COMPONENT_TYPE);
        proEmpleadoCreateInput.setId("proEmpleadoCreateInput");
        proEmpleadoCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{provisionBean.provision.proEmpleado}", Integer.class));
        proEmpleadoCreateInput.setRequired(true);
        
        htmlPanelGrid.getChildren().add(proEmpleadoCreateInput);
        
        Message proEmpleadoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        proEmpleadoCreateInputMessage.setId("proEmpleadoCreateInputMessage");
        proEmpleadoCreateInputMessage.setFor("proEmpleadoCreateInput");
        proEmpleadoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(proEmpleadoCreateInputMessage);
        
        OutputLabel proConceptoCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        proConceptoCreateOutput.setFor("proConceptoCreateInput");
        proConceptoCreateOutput.setId("proConceptoCreateOutput");
        proConceptoCreateOutput.setValue("Pro Concepto:");
        htmlPanelGrid.getChildren().add(proConceptoCreateOutput);
        
        Spinner proConceptoCreateInput = (Spinner) application.createComponent(Spinner.COMPONENT_TYPE);
        proConceptoCreateInput.setId("proConceptoCreateInput");
        proConceptoCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{provisionBean.provision.proConcepto}", Integer.class));
        proConceptoCreateInput.setRequired(true);
        
        htmlPanelGrid.getChildren().add(proConceptoCreateInput);
        
        Message proConceptoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        proConceptoCreateInputMessage.setId("proConceptoCreateInputMessage");
        proConceptoCreateInputMessage.setFor("proConceptoCreateInput");
        proConceptoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(proConceptoCreateInputMessage);
        
        OutputLabel proPeriodoCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        proPeriodoCreateOutput.setFor("proPeriodoCreateInput");
        proPeriodoCreateOutput.setId("proPeriodoCreateOutput");
        proPeriodoCreateOutput.setValue("Pro Periodo:");
        htmlPanelGrid.getChildren().add(proPeriodoCreateOutput);
        
        Calendar proPeriodoCreateInput = (Calendar) application.createComponent(Calendar.COMPONENT_TYPE);
        proPeriodoCreateInput.setId("proPeriodoCreateInput");
        proPeriodoCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{provisionBean.provision.proPeriodo}", Date.class));
        proPeriodoCreateInput.setNavigator(true);
        proPeriodoCreateInput.setEffect("slideDown");
        proPeriodoCreateInput.setPattern("dd/MM/yyyy");
        proPeriodoCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(proPeriodoCreateInput);
        
        Message proPeriodoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        proPeriodoCreateInputMessage.setId("proPeriodoCreateInputMessage");
        proPeriodoCreateInputMessage.setFor("proPeriodoCreateInput");
        proPeriodoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(proPeriodoCreateInputMessage);
        
        OutputLabel proValorCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        proValorCreateOutput.setFor("proValorCreateInput");
        proValorCreateOutput.setId("proValorCreateOutput");
        proValorCreateOutput.setValue("Pro Valor:");
        htmlPanelGrid.getChildren().add(proValorCreateOutput);
        
        InputText proValorCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        proValorCreateInput.setId("proValorCreateInput");
        proValorCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{provisionBean.provision.proValor}", Double.class));
        proValorCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(proValorCreateInput);
        
        Message proValorCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        proValorCreateInputMessage.setId("proValorCreateInputMessage");
        proValorCreateInputMessage.setFor("proValorCreateInput");
        proValorCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(proValorCreateInputMessage);
        
        OutputLabel proEstadoCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        proEstadoCreateOutput.setFor("proEstadoCreateInput");
        proEstadoCreateOutput.setId("proEstadoCreateOutput");
        proEstadoCreateOutput.setValue("Pro Estado:");
        htmlPanelGrid.getChildren().add(proEstadoCreateOutput);
        
        InputText proEstadoCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        proEstadoCreateInput.setId("proEstadoCreateInput");
        proEstadoCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{provisionBean.provision.proEstado}", String.class));
        LengthValidator proEstadoCreateInputValidator = new LengthValidator();
        proEstadoCreateInputValidator.setMaximum(1);
        proEstadoCreateInput.addValidator(proEstadoCreateInputValidator);
        proEstadoCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(proEstadoCreateInput);
        
        Message proEstadoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        proEstadoCreateInputMessage.setId("proEstadoCreateInputMessage");
        proEstadoCreateInputMessage.setFor("proEstadoCreateInput");
        proEstadoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(proEstadoCreateInputMessage);
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateEditPanel() {return null;}

	public HtmlPanelGrid populateViewPanel() { return null;}

	public Provision getProvision() {
        if (provision == null) {
            provision = new Provision();
        }
        return provision;
    }

	public void setProvision(Provision provision) {
        this.provision = provision;
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
        findAllProvisions();
        return "provision";
    }

	public String displayCreateDialog() {
        provision = new Provision();
        createDialogVisible = true;
        return "provision";
    }

	public String persist() {
        String message = "";
        if (provision.getProCodigo() != null) {
            provisionService.updateProvision(provision);
            message = "message_successfully_updated";
        } else {
            provisionService.saveProvision(provision);
            message = "message_successfully_created";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialogWidget.hide()");
        context.execute("editDialogWidget.hide()");
        
        FacesMessage facesMessage = MessageFactory.getMessage(message, "Provision");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllProvisions();
    }

	public String delete() {
        provisionService.deleteProvision(provision);
        FacesMessage facesMessage = MessageFactory.getMessage("message_successfully_deleted", "Provision");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllProvisions();
    }

	public void reset() {
        provision = null;
        createDialogVisible = false;
    }

	public void handleDialogClose(CloseEvent event) {
        reset();
    }
}
