package co.edu.udistrital.rrhh.web;
import co.edu.udistrital.rrhh.domain.Cargo;
import co.edu.udistrital.rrhh.service.CargoService;
import co.edu.udistrital.rrhh.web.util.CampoValor;
import co.edu.udistrital.rrhh.web.util.ComponentsGenerator;
import co.edu.udistrital.rrhh.web.util.Constantes;
import co.edu.udistrital.rrhh.web.util.MessageFactory;
import co.edu.udistrital.rrhh.web.util.NominaException;

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

@ManagedBean(name = "cargoBean")
@SessionScoped
@Configurable
@RooSerializable
@RooJsfManagedBean(entity = Cargo.class, beanName = "cargoBean")
public class CargoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
    CargoService cargoService;

	private String name = "Cargos";

	private Cargo cargo;

	private List<Cargo> allCargoes;

	private boolean dataVisible = false;

	private List<CampoValor> columns;

	private HtmlPanelGrid createPanelGrid;

	private HtmlPanelGrid editPanelGrid;

	private HtmlPanelGrid viewPanelGrid;

	private boolean createDialogVisible = false;

	@PostConstruct
    public void init() {
        columns = new ArrayList<CampoValor>();
        columns.add(new CampoValor("Nombre ", "carNombre"));
        columns.add(new CampoValor("Descripcion ", "carDescripcion"));
        columns.add(new CampoValor("Salario", "stringrmatedSalary"));
    }

	public String getName() {
        return name;
    }

	public List<CampoValor> getColumns() {
        return columns;
    }

	public List<Cargo> getAllCargoes() {
        return allCargoes;
    }

	public void setAllCargoes(List<Cargo> allCargoes) {
        this.allCargoes = allCargoes;
    }

	public String findAllCargoes() {
      //  allCargoes = cargoService.findAllCargoes();
        allCargoes = cargoService.findAllCargoAct(Constantes.GENERAL_ESTADO_INACTIVO);
        dataVisible = !allCargoes.isEmpty();
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
        
        // Codigo del cargo 
        
       
        
        // 
        OutputLabel carNombreCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        carNombreCreateOutput.setFor("carNombreCreateInput");
        carNombreCreateOutput.setId("carNombreCreateOutput");
        carNombreCreateOutput.setValue("Nombre:");
        htmlPanelGrid.getChildren().add(carNombreCreateOutput);
        
        InputText carNombreCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        carNombreCreateInput.setId("carNombreCreateInput");
        carNombreCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{cargoBean.cargo.carNombre}", String.class));
        LengthValidator carNombreCreateInputValidator = new LengthValidator();
        carNombreCreateInputValidator.setMaximum(50);
        carNombreCreateInput.addValidator(carNombreCreateInputValidator);
        carNombreCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(carNombreCreateInput);
        
        Message carNombreCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        carNombreCreateInputMessage.setId("carNombreCreateInputMessage");
        carNombreCreateInputMessage.setFor("carNombreCreateInput");
        carNombreCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(carNombreCreateInputMessage);
        
        OutputLabel carDescripcionCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        carDescripcionCreateOutput.setFor("carDescripcionCreateInput");
        carDescripcionCreateOutput.setId("carDescripcionCreateOutput");
        carDescripcionCreateOutput.setValue("Descripcion: ");
        htmlPanelGrid.getChildren().add(carDescripcionCreateOutput);
        
        InputText carDescripcionCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        carDescripcionCreateInput.setId("carDescripcionCreateInput");
        carDescripcionCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{cargoBean.cargo.carDescripcion}", String.class));
        carDescripcionCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(carDescripcionCreateInput);
        
        Message carDescripcionCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        carDescripcionCreateInputMessage.setId("carDescripcionCreateInputMessage");
        carDescripcionCreateInputMessage.setFor("carDescripcionCreateInput");
        carDescripcionCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(carDescripcionCreateInputMessage);
        
        
        OutputLabel carSalarioCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        carSalarioCreateOutput.setFor("carSalarioCreateInput");
        carSalarioCreateOutput.setId("carSalarioCreateOutput");
        carSalarioCreateOutput.setValue("Salario:");
        htmlPanelGrid.getChildren().add(carSalarioCreateOutput);
        
        InputText carSalarioCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        carSalarioCreateInput.setId("carSalarioCreateInput");
        carSalarioCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{cargoBean.cargo.carSalario}", Double.class));
        carSalarioCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(carSalarioCreateInput);
        
        Message carSalarioCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        carSalarioCreateInputMessage.setId("carSalarioCreateInputMessage");
        carSalarioCreateInputMessage.setFor("carSalarioCreateInput");
        carSalarioCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(carSalarioCreateInputMessage);
        
        // Estado del cargo 
        OutputLabel carEstadoCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        carEstadoCreateOutput.setFor("carEstadoCreateInput");
        carEstadoCreateOutput.setId("carEstadoCreateOutput");
        carEstadoCreateOutput.setValue("Estado:");
        htmlPanelGrid.getChildren().add(carEstadoCreateOutput);
        
        htmlPanelGrid.getChildren().add(ComponentsGenerator.getAutocompleteEstadoActual("carEstadoCreateInput", "#{cargoBean.cargo.carEstado}"));
        
        Message carEstadoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        carEstadoCreateInputMessage.setId("carEstadoCreateInputMessage");
        carEstadoCreateInputMessage.setFor("carEstadoCreateInput");
        carEstadoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(carEstadoCreateInputMessage);
        //
        
        return htmlPanelGrid;
    }

	public HtmlPanelGrid populateEditPanel() {return null;}

	public HtmlPanelGrid populateViewPanel() {return null;}

	public Cargo getCargo() {
        if (cargo == null) {
            cargo = new Cargo();
        }
        return cargo;
    }

	public void setCargo(Cargo cargo) {
        this.cargo = cargo;
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
        findAllCargoes();
        return "cargo";
    }

	public String displayCreateDialog() {
        cargo = new Cargo();
        createDialogVisible = true;
        return "cargo";
    }

	public String persist() {
		String message = "";
		try {
			if (cargo.getCarCogigo() != null) {

				cargoService.saveCargo(cargo);

				message = "message_successfully_updated";
			} else {
				cargoService.saveCargo(cargo);
				message = "message_successfully_created";
			}
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("createDialogWidget.hide()");
			context.execute("editDialogWidget.hide()");

			FacesMessage facesMessage = MessageFactory.getMessage(message, "Cargo");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			reset();
			return findAllCargoes();

		} catch (NominaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesMessage facesMessage = MessageFactory.getMessage(e.getMessage(), "Cargo");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			return null;
		}
	}

	public String delete() {
		try {
			cargoService.ActEstadoCargo(cargo);
			
			//cargoService.deleteCargo(cargo);
	        FacesMessage facesMessage = MessageFactory.getMessage("message_successfully_deleted", "Cargo");
	        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	        reset();
			
		} catch (NominaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			 FacesMessage facesMessage = MessageFactory.getMessage(e.getMessage(), "Cargo");
		        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		        reset();
		}  // Actualiza el estado a 'Inactivo'
		
        return findAllCargoes();
    }
     
	
	public void reset() {
        cargo = null;
        createDialogVisible = false;
    }

	public void handleDialogClose(CloseEvent event) {
        reset();
    }
}
