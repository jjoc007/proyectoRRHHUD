package co.edu.udistrital.rrhh.web;
import co.edu.udistrital.rrhh.domain.Afiliacion;
import co.edu.udistrital.rrhh.domain.Empleado;
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

@ManagedBean(name = "conceptosLiquidacionBean")
@SessionScoped
@Configurable
@RooSerializable
public class ConceptosLiquidacionBean implements Serializable  {


	private boolean dataVisible = false;

	private List<String> columns;


	private boolean createDialogVisible = false;

	@PostConstruct
    public void init() {
        columns = new ArrayList<String>();
    }

	public List<String> getColumns() {
        return columns;
    }

	public boolean isDataVisible() {
        return dataVisible;
    }

	public void setDataVisible(boolean dataVisible) {
        this.dataVisible = dataVisible;
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
        return "conceptosLiquidacion";
    }

	public String displayCreateDialog() {
        createDialogVisible = true;
        return "conceptosLiquidacion";
    }

	public void reset() {
        createDialogVisible = false;
    }

	public void handleDialogClose(CloseEvent event) {
        reset();
    }

	private static final long serialVersionUID = 1L;
}
