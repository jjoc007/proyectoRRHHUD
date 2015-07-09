package co.edu.udistrital.rrhh.service.impl;
import co.edu.udistrital.rrhh.domain.Empleado;
import co.edu.udistrital.rrhh.domain.Provision;
import co.edu.udistrital.rrhh.repository.EmpleadoRepository;
import co.edu.udistrital.rrhh.repository.ProvisionRepository;
import co.edu.udistrital.rrhh.service.LiquidacionService;
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
	
	
	private int numProviVacaciones;

	public List<Empleado> findAllEmpleados() {
        return empleadoReprository.findAll();
    }
	
	
	public void Liquidar(List<Empleado> allEmpleados){
		
		for (Empleado empleadoAux: allEmpleados) {
			
			if (empleadoAux.isEmp_vacaciones()) {
			   
			   List<Provision> provisionesRep = provisionRepository.findProviVacaciones(empleadoAux.getEmpCedula(), Constantes.CONCEPTO_VACACIONES);
			   
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
		String mes;
		Double prima = 0.0;
		
		mes = periodo.substring(4,5);
		System.out.println("mes: "+mes);
		
		if (mes.equals("06") || mes.equals("12")) {
			
			procesarPrima(cedulaEmpleado, periodo);
		}
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

}
