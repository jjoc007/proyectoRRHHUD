package co.edu.udistrital.rrhh.web;
import co.edu.udistrital.rrhh.domain.Afiliacion;
import co.edu.udistrital.rrhh.domain.Empleado;
import co.edu.udistrital.rrhh.domain.Entidad;
import co.edu.udistrital.rrhh.service.AfiliacionService;
import co.edu.udistrital.rrhh.service.EmpleadoService;
import co.edu.udistrital.rrhh.service.EntidadService;
import co.edu.udistrital.rrhh.web.converter.EmpleadoConverter;
import co.edu.udistrital.rrhh.web.converter.EntidadConverter;
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
import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.message.Message;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

@ManagedBean(name = "afiliacionBean")
@SessionScoped
@Configurable
@RooSerializable
@RooJsfManagedBean(entity = Afiliacion.class, beanName = "afiliacionBean")
public class AfiliacionBean implements Serializable{

	@Autowired
    AfiliacionService afiliacionService;

	@Autowired
    EmpleadoService empleadoService;

	@Autowired
    EntidadService entidadService;

	private String name = "Afiliacions";

	private Afiliacion afiliacion;

	private List<Afiliacion> allAfiliacions;

	private boolean dataVisible = false;

	private List<String> columns;

	private HtmlPanelGrid createPanelGrid;

	private HtmlPanelGrid editPanelGrid;

	private HtmlPanelGrid viewPanelGrid;

	private boolean createDialogVisible = false;

	@PostConstruct
    public void init() {
        columns = new ArrayList<String>();
    }

	public String getName() {
        return name;
    }

	public List<String> getColumns() {
        return columns;
    }

	public List<Afiliacion> getAllAfiliacions() {
        return allAfiliacions;
    }

	public void setAllAfiliacions(List<Afiliacion> allAfiliacions) {
        this.allAfiliacions = allAfiliacions;
    }

	public String findAllAfiliacions() {
        allAfiliacions = afiliacionService.findAllAfiliacions();
        dataVisible = !allAfiliacions.isEmpty();
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
        
        OutputLabel afiEmpleadoCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        afiEmpleadoCreateOutput.setFor("afiEmpleadoCreateInput");
        afiEmpleadoCreateOutput.setId("afiEmpleadoCreateOutput");
        afiEmpleadoCreateOutput.setValue("Afi Empleado:");
        htmlPanelGrid.getChildren().add(afiEmpleadoCreateOutput);
        
        AutoComplete afiEmpleadoCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        afiEmpleadoCreateInput.setId("afiEmpleadoCreateInput");
        afiEmpleadoCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{afiliacionBean.afiliacion.afiEmpleado}", Empleado.class));
        afiEmpleadoCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{afiliacionBean.completeAfiEmpleado}", List.class, new Class[] { String.class }));
        afiEmpleadoCreateInput.setDropdown(true);
        afiEmpleadoCreateInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "afiEmpleado", String.class));
        afiEmpleadoCreateInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{afiEmpleado.empCedula} #{afiEmpleado.empNombre} #{afiEmpleado.empFechaIngreso} #{afiEmpleado.empFechaSalida}", String.class));
        afiEmpleadoCreateInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{afiEmpleado}", Empleado.class));
        afiEmpleadoCreateInput.setConverter(new EmpleadoConverter());
        afiEmpleadoCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(afiEmpleadoCreateInput);
        
        Message afiEmpleadoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        afiEmpleadoCreateInputMessage.setId("afiEmpleadoCreateInputMessage");
        afiEmpleadoCreateInputMessage.setFor("afiEmpleadoCreateInput");
        afiEmpleadoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(afiEmpleadoCreateInputMessage);
        
        OutputLabel afiEntidadCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        afiEntidadCreateOutput.setFor("afiEntidadCreateInput");
        afiEntidadCreateOutput.setId("afiEntidadCreateOutput");
        afiEntidadCreateOutput.setValue("Afi Entidad:");
        htmlPanelGrid.getChildren().add(afiEntidadCreateOutput);
        
        AutoComplete afiEntidadCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        afiEntidadCreateInput.setId("afiEntidadCreateInput");
        afiEntidadCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{afiliacionBean.afiliacion.afiEntidad}", Entidad.class));
        afiEntidadCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{afiliacionBean.completeAfiEntidad}", List.class, new Class[] { String.class }));
        afiEntidadCreateInput.setDropdown(true);
        afiEntidadCreateInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "afiEntidad", String.class));
        afiEntidadCreateInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{afiEntidad.entCodigo} #{afiEntidad.entNombre} #{afiEntidad.entCuenta} #{afiEntidad.entAporteEmpleado}", String.class));
        afiEntidadCreateInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{afiEntidad}", Entidad.class));
        afiEntidadCreateInput.setConverter(new EntidadConverter());
        afiEntidadCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(afiEntidadCreateInput);
        
        Message afiEntidadCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        afiEntidadCreateInputMessage.setId("afiEntidadCreateInputMessage");
        afiEntidadCreateInputMessage.setFor("afiEntidadCreateInput");
        afiEntidadCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(afiEntidadCreateInputMessage);
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateEditPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        OutputLabel afiEmpleadoEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        afiEmpleadoEditOutput.setFor("afiEmpleadoEditInput");
        afiEmpleadoEditOutput.setId("afiEmpleadoEditOutput");
        afiEmpleadoEditOutput.setValue("Afi Empleado:");
        htmlPanelGrid.getChildren().add(afiEmpleadoEditOutput);
        
        AutoComplete afiEmpleadoEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        afiEmpleadoEditInput.setId("afiEmpleadoEditInput");
        afiEmpleadoEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{afiliacionBean.afiliacion.afiEmpleado}", Empleado.class));
        afiEmpleadoEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{afiliacionBean.completeAfiEmpleado}", List.class, new Class[] { String.class }));
        afiEmpleadoEditInput.setDropdown(true);
        afiEmpleadoEditInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "afiEmpleado", String.class));
        afiEmpleadoEditInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{afiEmpleado.empCedula} #{afiEmpleado.empNombre} #{afiEmpleado.empFechaIngreso} #{afiEmpleado.empFechaSalida}", String.class));
        afiEmpleadoEditInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{afiEmpleado}", Empleado.class));
        afiEmpleadoEditInput.setConverter(new EmpleadoConverter());
        afiEmpleadoEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(afiEmpleadoEditInput);
        
        Message afiEmpleadoEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        afiEmpleadoEditInputMessage.setId("afiEmpleadoEditInputMessage");
        afiEmpleadoEditInputMessage.setFor("afiEmpleadoEditInput");
        afiEmpleadoEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(afiEmpleadoEditInputMessage);
        
        OutputLabel afiEntidadEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        afiEntidadEditOutput.setFor("afiEntidadEditInput");
        afiEntidadEditOutput.setId("afiEntidadEditOutput");
        afiEntidadEditOutput.setValue("Afi Entidad:");
        htmlPanelGrid.getChildren().add(afiEntidadEditOutput);
        
        AutoComplete afiEntidadEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        afiEntidadEditInput.setId("afiEntidadEditInput");
        afiEntidadEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{afiliacionBean.afiliacion.afiEntidad}", Entidad.class));
        afiEntidadEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{afiliacionBean.completeAfiEntidad}", List.class, new Class[] { String.class }));
        afiEntidadEditInput.setDropdown(true);
        afiEntidadEditInput.setValueExpression("var", expressionFactory.createValueExpression(elContext, "afiEntidad", String.class));
        afiEntidadEditInput.setValueExpression("itemLabel", expressionFactory.createValueExpression(elContext, "#{afiEntidad.entCodigo} #{afiEntidad.entNombre} #{afiEntidad.entCuenta} #{afiEntidad.entAporteEmpleado}", String.class));
        afiEntidadEditInput.setValueExpression("itemValue", expressionFactory.createValueExpression(elContext, "#{afiEntidad}", Entidad.class));
        afiEntidadEditInput.setConverter(new EntidadConverter());
        afiEntidadEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(afiEntidadEditInput);
        
        Message afiEntidadEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        afiEntidadEditInputMessage.setId("afiEntidadEditInputMessage");
        afiEntidadEditInputMessage.setFor("afiEntidadEditInput");
        afiEntidadEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(afiEntidadEditInputMessage);
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateViewPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText afiEmpleadoLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        afiEmpleadoLabel.setId("afiEmpleadoLabel");
        afiEmpleadoLabel.setValue("Afi Empleado:");
        htmlPanelGrid.getChildren().add(afiEmpleadoLabel);
        
        HtmlOutputText afiEmpleadoValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        afiEmpleadoValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{afiliacionBean.afiliacion.afiEmpleado}", Empleado.class));
        afiEmpleadoValue.setConverter(new EmpleadoConverter());
        htmlPanelGrid.getChildren().add(afiEmpleadoValue);
        
        HtmlOutputText afiEntidadLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        afiEntidadLabel.setId("afiEntidadLabel");
        afiEntidadLabel.setValue("Afi Entidad:");
        htmlPanelGrid.getChildren().add(afiEntidadLabel);
        
        HtmlOutputText afiEntidadValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        afiEntidadValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{afiliacionBean.afiliacion.afiEntidad}", Entidad.class));
        afiEntidadValue.setConverter(new EntidadConverter());
        htmlPanelGrid.getChildren().add(afiEntidadValue);
        
        return htmlPanelGrid;
    }

	public Afiliacion getAfiliacion() {
        if (afiliacion == null) {
            afiliacion = new Afiliacion();
        }
        return afiliacion;
    }

	public void setAfiliacion(Afiliacion afiliacion) {
        this.afiliacion = afiliacion;
    }

	public List<Empleado> completeAfiEmpleado(String query) {
        List<Empleado> suggestions = new ArrayList<Empleado>();
        for (Empleado empleado : empleadoService.findAllEmpleadoes()) {
            String empleadoStr = String.valueOf(empleado.getEmpCedula() +  " "  + empleado.getEmpNombre() +  " "  + empleado.getEmpFechaIngreso() +  " "  + empleado.getEmpFechaSalida());
            if (empleadoStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(empleado);
            }
        }
        return suggestions;
    }

	public List<Entidad> completeAfiEntidad(String query) {
        List<Entidad> suggestions = new ArrayList<Entidad>();
        for (Entidad entidad : entidadService.findAllEntidads()) {
            String entidadStr = String.valueOf(entidad.getEntCodigo() +  " "  + entidad.getEntNombre() +  " "  + entidad.getEntCuenta() +  " "  + entidad.getEntAporteEmpleado());
            if (entidadStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(entidad);
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
        findAllAfiliacions();
        return "afiliacion";
    }

	public String displayCreateDialog() {
        afiliacion = new Afiliacion();
        createDialogVisible = true;
        return "afiliacion";
    }

	public String persist() {
        String message = "";
        if (afiliacion.getId() != null) {
            afiliacionService.updateAfiliacion(afiliacion);
            message = "message_successfully_updated";
        } else {
            afiliacionService.saveAfiliacion(afiliacion);
            message = "message_successfully_created";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialogWidget.hide()");
        context.execute("editDialogWidget.hide()");
        
        FacesMessage facesMessage = MessageFactory.getMessage(message, "Afiliacion");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllAfiliacions();
    }

	public String delete() {
        afiliacionService.deleteAfiliacion(afiliacion);
        FacesMessage facesMessage = MessageFactory.getMessage("message_successfully_deleted", "Afiliacion");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllAfiliacions();
    }

	public void reset() {
        afiliacion = null;
        createDialogVisible = false;
    }

	public void handleDialogClose(CloseEvent event) {
        reset();
    }

	private static final long serialVersionUID = 1L;
}
