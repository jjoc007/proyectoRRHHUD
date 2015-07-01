package co.edu.udistrital.rrhh.web;
import co.edu.udistrital.rrhh.domain.Afiliacion;
import co.edu.udistrital.rrhh.service.AfiliacionService;
import co.edu.udistrital.rrhh.web.util.MessageFactory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
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
public class AfiliacionBean implements Serializable  {

	@Autowired
    AfiliacionService afiliacionService;

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
        return (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
    }

	public HtmlPanelGrid populateEditPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        return (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
    }

	public HtmlPanelGrid populateViewPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        return (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
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
