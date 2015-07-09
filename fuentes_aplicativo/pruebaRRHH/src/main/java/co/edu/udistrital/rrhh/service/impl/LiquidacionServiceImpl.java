package co.edu.udistrital.rrhh.service.impl;
import co.edu.udistrital.rrhh.domain.Aporte;
import co.edu.udistrital.rrhh.domain.Empleado;
import co.edu.udistrital.rrhh.domain.Pago;
import co.edu.udistrital.rrhh.domain.Provision;
import co.edu.udistrital.rrhh.repository.EmpleadoRepository;
import co.edu.udistrital.rrhh.repository.ProvisionRepository;
import co.edu.udistrital.rrhh.service.AporteService;
import co.edu.udistrital.rrhh.service.LiquidacionService;
import co.edu.udistrital.rrhh.service.PagoService;
import co.edu.udistrital.rrhh.web.util.Constantes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LiquidacionServiceImpl implements LiquidacionService {

	@Autowired
    EmpleadoRepository empleadoReprository;
	
	@Autowired
	ProvisionRepository provisionRepository;
	
	
	public PagoService pagoService;
	public AporteService aporteService;
	public Pago pago;
	public Aporte aporte;
	

	public List<Empleado> findAllEmpleados() {
        return empleadoReprository.findAll();
    }
	
	
	public void Liquidar(List<Empleado> allEmpleados, String periodo){
		
		Integer numProviVacaciones;	
		String mes;
			
		for (Empleado empleadoAux: allEmpleados) {
			
			if (empleadoAux.isEmp_vacaciones()) {
			   
			   List<Provision> provisionesRep = provisionRepository.findProvisiones(empleadoAux.getEmpCedula(), Constantes.CONCEPTO_VACACIONES, Constantes.PROV_ACTIVA);
			   
			   if(provisionesRep != null) numProviVacaciones  =  provisionesRep.size();
			   else numProviVacaciones = 0;
			   
			   System.out.println("numero de provisiones:"+numProviVacaciones);
			   
			   if (numProviVacaciones > 24 ){
				   
				   for(Provision provisionAux: provisionesRep){
					   
					   provisionAux.setProEstado(Constantes.PROV_NO_APLICA);
					   provisionRepository.save(provisionAux);
					   
				   }
				   
			   }
			   else if(numProviVacaciones >= 12 && numProviVacaciones <= 24 ){
				   
				   for(Provision provisionAux: provisionesRep){
					   
					   provisionAux.setProEstado(Constantes.PROV_PAGADA);
					   provisionRepository.save(provisionAux);
				   }
			   }
			};

			mes = periodo.substring(4,5);
			
			System.out.println("mes: "+mes);
			
			if (mes.equals(Constantes.MES_CESANTIAS) ){
				
				procesarCesantias(empleadoAux.getEmpCedula(), periodo);
			}
			
			if (mes.equals(Constantes.MES_PRIMA) || mes.equals(Constantes.MES_PRIMA2)) {
				
				procesarPrima(empleadoAux.getEmpCedula(), periodo);
			}
			
			if(empleadoAux.isEmp_liquida()){
				
				Integer concepVacaciones = Constantes.CONCEPTO_VACACIONES;
				Integer concepCesantias = Constantes.CONCEPTO_CESANTIAS;
				Integer concepIntereses = Constantes.CONCEPTO_INTERESES_CESANTIAS;
				Integer concepPrima = Constantes.CONCEPTO_PRIMA;
				
				/*List<Provision> provisionesAct = provisionRepository.findProvisionesAct(empleadoAux.getEmpCedula(), concepVacaciones, concepCesantias, concepIntereses, concepPrima);
				
				for(Provision provisionAux: provisionesAct){
					   
					   provisionAux.setProEstado(Constantes.PROV_PAGADA);
					   provisionRepository.save(provisionAux);
				   }*/
				
				System.out.println(empleadoAux.getEmpCedula()+"liq: "+empleadoAux.isEmp_liquida()+"vacas "+empleadoAux.isEmp_vacaciones());
			}
			
			//calcularSalarioEmpleado(empleadoAux.getEmpCedula(), periodo);
			
		}
	}; 
	
	public void procesarPrima(Integer cedulaEmpleado, String periodo){
		
		Double prima = 0.0;
		
		/*obtener la suma de la tabla provisiones donde el concepto sea Constantes.CONCEPTO_PRIMA, insertar esa suma en la tabla pago y actualizar esos registros de la tabla provision con estado P*/
		
		List<Provision> provisionesRep = provisionRepository.findProvisiones(cedulaEmpleado, Constantes.CONCEPTO_PRIMA, Constantes.PROV_ACTIVA);
		
		for(Provision provisionAux: provisionesRep){
			   
			   provisionAux.setProEstado(Constantes.PROV_PAGADA);
			   provisionRepository.save(provisionAux);
			   
		   }
		
	realizarPago(Constantes.CONCEPTO_PRIMA, Constantes.PAGO_ACTIVO, cedulaEmpleado, periodo, prima);
		
	}
	
	public void procesarCesantias(Integer cedulaEmpleado, String periodo){
		
		Double cesantias = 0.0;
		Double interesesCesantias = 0.0;
		Integer entidad;
		
		List<Provision> provisionesRep = provisionRepository.findProvisiones(cedulaEmpleado, Constantes.CONCEPTO_CESANTIAS, Constantes.PROV_ACTIVA);
		
		for(Provision provisionAux: provisionesRep){
			   
			   provisionAux.setProEstado(Constantes.PROV_PAG_ENTIDAD);
			   provisionRepository.save(provisionAux);
			   
		   }
		
		entidad = recuperarAfiliacion(cedulaEmpleado, "ENTIDAD_CESANTIAS");
		
		realizarAporte(entidad, Constantes.APORTE_CESANTIAS, periodo,cesantias);
		
		/*obtener la suma de la tabla provisiones donde el concepto sea Constantes.CONCEPTO_CESANTIAS, insertar esa suma en la aporte y actualizar esos registros de la tabla provision con estado P*/
				
		List<Provision> provisioneInte = provisionRepository.findProvisiones(cedulaEmpleado, Constantes.CONCEPTO_INTERESES_CESANTIAS, Constantes.PROV_ACTIVA);
		
		for(Provision provisionAux: provisioneInte){
			   
			   provisionAux.setProEstado(Constantes.PROV_PAGADA);
			   provisionRepository.save(provisionAux);
			   
		   }
		
		realizarPago(Constantes.CONCEPTO_INTERESES_CESANTIAS, Constantes.PAGO_ACTIVO, cedulaEmpleado, periodo, interesesCesantias);
		/*obtener la suma de la tabla provisiones donde el concepto sea Constantes.CONCEPTO_INTERESES_CESANTIAS, insertar esa suma en la tabla pago y actualizar esos registros de la tabla provision con estado P*/
		
	}
	
	public Double calcularTotalDeducciones(Integer cedulaEmpleado, String periodo)
	{
		Double totalDeducciones = 0.0;
		
		return totalDeducciones;
		/*
	realizar suma de los valores de la tabla Pago por empleado y por periodo 
	que sean de tipo deducido(esto se obtiene realizando el cruce con la tabla de conceptos por el id del concepto)
	*/
	}

	public Double calcularTotalDevengados(Integer cedulaEmpleado, String periodo)
	{
		Double totalDevengados = 0.0;
		
		return totalDevengados;
		/*
	realizar suma de los valores de la tabla Pago por empleado y por periodo 
	que sean de tipo devengo(esto se obtiene realizando el cruce con la tabla de conceptos por el id del concepto)
	más el valor del sueldo obtenido de la funcion obtenerSueldoEmpleado(id_Empledo int) mas la prima
	*/
	}

	public Double calcularSalarioEmpleado(Integer cedulaEmpleado, String periodo)
	{
		Double salario = 0.0;
	salario = calcularTotalDevengados(cedulaEmpleado, periodo) - calcularTotalDeducciones(cedulaEmpleado, periodo);
	return salario;
	}
	
	public void realizarPago(Integer concepto, String estado, Integer empleado, String periodo, Double valorPago){
		
		//pago.setPagConcepto(concepto);
		pago.setPagEstado(estado);
	//	pago.setPagoEmpleado(empleado);
	//	pago.setPagPeriodo(periodo);
		pago.setPagValorPago(valorPago);
		
		pagoService.savePago(pago);
	}
		
	public Integer recuperarAfiliacion(Integer empleado, String tipoEntidad){
		
		Integer entidad = 0;
		//entidad = recuperar la entidad 
		return entidad;
	}

	public void realizarAporte(Integer entidad, String tipo, String periodo, Double valor){
		
		aporte.setApoEntidad(entidad);
		aporte.setApoTipo(tipo);
		//aporte.setApoPeriodo(periodo);
		aporte.setApoValor(valor);
		
		aporteService.saveAporte(aporte);
	}
}
