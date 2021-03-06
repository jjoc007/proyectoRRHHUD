package co.edu.udistrital.rrhh.web;
import co.edu.udistrital.rrhh.domain.Concepto;
import co.edu.udistrital.rrhh.service.ConceptoService;
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

	private String name = "Conceptos";

	private Concepto concepto;

	private List<Concepto> allConceptoes;

	private boolean dataVisible = false;

	private List<CampoValor> columns;

	private HtmlPanelGrid createPanelGrid;

	private HtmlPanelGrid editPanelGrid;

	private HtmlPanelGrid viewPanelGrid;

	private boolean createDialogVisible = false;

	@PostConstruct
    public void init() {
        columns = new ArrayList<CampoValor>();
        columns.add(new CampoValor("Nombre", "conNombre"));
        columns.add(new CampoValor("Descripcion", "conDescripcion"));
        columns.add(new CampoValor("valor empleado","conValor"));
        columns.add(new CampoValor("valor empresa","conValorEmpresa"));
       columns.add(new CampoValor("Tipo","conTipo"));
        columns.add(new CampoValor("Tipo percepcion","conTipoPercepcion"));
    }

	public String getName() {
        return name;
    }

	public List<CampoValor> getColumns() {
        return columns;
    }

	public List<Concepto> getAllConceptoes() {
        return allConceptoes;
    }

	public void setAllConceptoes(List<Concepto> allConceptoes) {
        this.allConceptoes = allConceptoes;
    }

	public String findAllConceptoes() {
       // allConceptoes = conceptoService.findAllConceptoes();
		allConceptoes = conceptoService.findAllConceptosByEstado(Constantes.GENERAL_ESTADO_ACTIVO);
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
        conTipoCreateOutput.setValue("Tipo Percepcion:");
        htmlPanelGrid.getChildren().add(conTipoCreateOutput);
        
        htmlPanelGrid.getChildren().add(ComponentsGenerator.getAutocompleteTipoConceptosPer("conTipoCreateInput", "#{conceptoBean.concepto.conTipoPercepcion}"));
        
        Message conTipoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        conTipoCreateInputMessage.setId("conTipoCreateInputMessage");
        conTipoCreateInputMessage.setFor("conTipoCreateInput");
        conTipoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(conTipoCreateInputMessage);
        
        OutputLabel conTipoValorCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        conTipoValorCreateOutput.setFor("conTipoValorCreateInput");
        conTipoValorCreateOutput.setId("conTipoValorCreateOutput");
        conTipoValorCreateOutput.setValue("Tipo Concepto:");
        htmlPanelGrid.getChildren().add(conTipoValorCreateOutput);
        
        htmlPanelGrid.getChildren().add(ComponentsGenerator.getAutocompleteTipoConceptos("conTipoValorCreateInput", "#{conceptoBean.concepto.conTipo}"));
        
        Message conTipoValorCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        conTipoValorCreateInputMessage.setId("conTipoValorCreateInputMessage");
        conTipoValorCreateInputMessage.setFor("conTipoValorCreateInput");
        conTipoValorCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(conTipoValorCreateInputMessage);
        
        OutputLabel conEstadoCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        conEstadoCreateOutput.setFor("conEstadoCreateInput");
        conEstadoCreateOutput.setId("conEstadoCreateOutput");
        conEstadoCreateOutput.setValue("Estado:");
        htmlPanelGrid.getChildren().add(conEstadoCreateOutput);
        
        htmlPanelGrid.getChildren().add(ComponentsGenerator.getAutocompleteEstadoActual("conEstadoCreateInput", "#{conceptoBean.concepto.conEstado}"));
        
        Message conEstadoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        conEstadoCreateInputMessage.setId("conEstadoCreateInputMessage");
        conEstadoCreateInputMessage.setFor("conEstadoCreateInput");
        conEstadoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(conEstadoCreateInputMessage);
        
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

        OutputLabel conValorEmpresaCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        conValorEmpresaCreateOutput.setFor("conValorEmpresaCreateInput");
        conValorEmpresaCreateOutput.setId("conValorEmpresaCreateOutput");
        conValorEmpresaCreateOutput.setValue("Valor Empresa:");
        htmlPanelGrid.getChildren().add(conValorEmpresaCreateOutput);
        
        InputText conValorEmpresaCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        conValorEmpresaCreateInput.setId("conValorEmpresaCreateInput");
        conValorEmpresaCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{conceptoBean.concepto.conValorEmpresa}", Double.class));
        conValorEmpresaCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(conValorEmpresaCreateInput);
        
        Message conValorEmrpesaCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        conValorEmrpesaCreateInputMessage.setId("conValorEmpresaCreateInputMessage");
        conValorEmrpesaCreateInputMessage.setFor("conValorEmpresaCreateInput");
        conValorEmrpesaCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(conValorEmrpesaCreateInputMessage);
        
        
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateEditPanel() {return null;}

	public HtmlPanelGrid populateViewPanel() {return null;}

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
        	concepto.setConEliminar("S");
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
		conceptoService.ActEstadoConcepto(concepto);  // Actualiza el estado a 'Inactivo'
        //conceptoService.deleteConcepto(concepto);
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
