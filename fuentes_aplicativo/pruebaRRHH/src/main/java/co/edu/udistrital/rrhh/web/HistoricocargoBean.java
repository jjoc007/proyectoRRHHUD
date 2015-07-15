package co.edu.udistrital.rrhh.web;
import co.edu.udistrital.rrhh.domain.Historicocargo;
import co.edu.udistrital.rrhh.service.HistoricocargoService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlPanelGrid;
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

	private String name = "Historico Cargos";

	private Historicocargo historicocargo;

	private List<Historicocargo> allHistoricocargoes;

	private boolean dataVisible = false;


	private HtmlPanelGrid createPanelGrid;

	private HtmlPanelGrid editPanelGrid;

	private HtmlPanelGrid viewPanelGrid;

	private boolean createDialogVisible = false;

	@PostConstruct
    public void init() {
    }

	public String getName() {
        return name;
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

	public HtmlPanelGrid populateCreatePanel() {return null;}

	public HtmlPanelGrid populateEditPanel() {return null;}

	public HtmlPanelGrid populateViewPanel() {return null;}

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

	public String persist() {return null;}

	public String delete() {return null;}

	public void reset() {
        historicocargo = null;
        createDialogVisible = false;
    }

	public void handleDialogClose(CloseEvent event) {
        reset();
    }

	private static final long serialVersionUID = 1L;
}
