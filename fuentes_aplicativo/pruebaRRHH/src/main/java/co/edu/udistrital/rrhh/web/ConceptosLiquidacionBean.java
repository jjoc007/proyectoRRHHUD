package co.edu.udistrital.rrhh.web;
import co.edu.udistrital.rrhh.domain.Concepto;
import co.edu.udistrital.rrhh.domain.Empleado;
import co.edu.udistrital.rrhh.domain.Pago;
import co.edu.udistrital.rrhh.service.ConceptoService;
import co.edu.udistrital.rrhh.service.EmpleadoService;
import co.edu.udistrital.rrhh.service.PagoService;
import co.edu.udistrital.rrhh.web.util.Constantes;
import co.edu.udistrital.rrhh.web.util.Utilidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.serializable.RooSerializable;

@ManagedBean(name = "conceptosLiquidacionBean")
@ViewScoped
@Configurable
@RooSerializable
public class ConceptosLiquidacionBean implements Serializable  {


	private boolean dataVisible = false;
	
	@Autowired
    EmpleadoService empleadoService;
	
	@Autowired
	ConceptoService conceptoService; 
	
	@Autowired
	PagoService pagoService; 
	
	private List<Empleado> allEmpleados;
	private List<Empleado> allEmpleadosWithPagos;
	public Calendar periodo;
	public String periodoLiquidar;
	
	
	private boolean createDialogVisible = false;

	@PostConstruct
    public void init() {
		findAllEmpleadoes();
    }
	
	
	public String findAllEmpleadoes() {
        allEmpleados = empleadoService.findAllEmpleadosAct(Constantes.ESTADO_EMPL_ACTIVO);
        fillPagosEmpleado();
        dataVisible = !allEmpleados.isEmpty();
        return null;
    }
	

	public void fillPagosEmpleado(){
			
		List<Concepto> conceptos =  new ArrayList<Concepto>();
		allEmpleadosWithPagos = new ArrayList<Empleado>();
		
		List<Integer> conceptosCons =  new ArrayList<Integer>();
		List<String> tipoPer =  new ArrayList<String>();
		
		conceptosCons.add(Constantes.CONCEPTO_TRANSPORTE);
		conceptosCons.add(Constantes.CONCEPTO_SALUD);
		conceptosCons.add(Constantes.CONCEPTO_PENSION);
		conceptosCons.add(Constantes.CONCEPTO_CESANTIAS);
		conceptosCons.add(Constantes.CONCEPTO_INTERESES_CESANTIAS);
		conceptosCons.add(Constantes.CONCEPTO_PRIMA);
		conceptosCons.add(Constantes.CONCEPTO_VACACIONES);
		conceptosCons.add(Constantes.CONCEPTO_CAJA_COMPENSACION);
		
		tipoPer.add(Constantes.TIPO_CONCEPTO_DEVENGO);
		tipoPer.add(Constantes.TIPO_CONCEPTO_DEDUCIDO);
		
		//Busca todos los conceptos que sean de tipo devengo y deducido		
		conceptos = conceptoService.findAllConceptoLiq(Constantes.GENERAL_ESTADO_ACTIVO, conceptosCons, tipoPer);
				
		obtenerPeriodo();
		
		for (Empleado empleadoAux: allEmpleados){

			List<Pago> pagosPorEmpleado =  new ArrayList<Pago>();
			
			for(Concepto conceptoAux: conceptos){

				pagosPorEmpleado.add(new Pago(empleadoAux, conceptoAux, periodo.getTime(),	0.0D, Constantes.PAGO_ACTIVO, null));
				
			}
			
			empleadoAux.setPagos(pagosPorEmpleado);
			allEmpleadosWithPagos.add(empleadoAux);

		}
			
	}
	
	public void guardarPagos(){
		
		for (Empleado empleadoaux : allEmpleadosWithPagos){
			
			for (Pago pagoaux : empleadoaux.getPagos()){
				
				if (pagoaux.getPagValorPago() != 0.0){
					pagoService.savePago(pagoaux);
				}
				
			}	
		}
		//Insertar registro en la tabla proceso Constantes.CONCEPTOS_LIQUIDACION y periodo.getTime()
		
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
        obtenerPeriodo();
        return "conceptosLiquidacion";
    }

	public void reset() {
        createDialogVisible = false;
    }

	public void handleDialogClose(CloseEvent event) {
        reset();
    }
	
	public List<Empleado> getAllEmpleados() {
		return allEmpleados;
	}


	public void setAllEmpleados(List<Empleado> allEmpleadoes) {
		this.allEmpleados = allEmpleadoes;
	}

	public List<Empleado> getAllEmpleadosWithPagos() {
		return allEmpleadosWithPagos;
	}


	public void setAllEmpleadosWithPagos(List<Empleado> allEmpleadosWithPagos) {
		this.allEmpleadosWithPagos = allEmpleadosWithPagos;
	}
	
	public Calendar obtenerPeriodo() {
		periodo = Utilidades.periodoLiquidacion();
		return periodo;
	}


	public String getPeriodoLiquidar() {
		periodoLiquidar = Utilidades.dateFormat(periodo.getTime());
		return periodoLiquidar;
	}

	public void setPeriodoLiquidar(String periodoLiquidar) {
		this.periodoLiquidar = periodoLiquidar;
	}

	private static final long serialVersionUID = 1L;
}
