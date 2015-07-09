package co.edu.udistrital.rrhh.service.impl;
import co.edu.udistrital.rrhh.domain.Empleado;
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
	
	private int concepto; 
	
	private int numProviVacaciones;
/*
	public long countAllEmpleadoes() {
        return empleadoReprository.count();
    }

	public void deleteEmpleado(Empleado empleado) {
        empleadoReprository.delete(empleado);
    }

	public Empleado findEmpleado(Integer id) {
        return empleadoReprository.findOne(id);
    }
*/
	public List<Empleado> findAllEmpleados() {
        return empleadoReprository.findAll();
    }
	
	
	public void Liquidar(List<Empleado> allEmpleados){
		
		for (Empleado empleadoAux: allEmpleados) {
			if (empleadoAux.isEmp_vacaciones()) {
			   concepto = Constantes.CONCEPTO_VACACIONES;
			   numProviVacaciones = provisionRepository.findProviVacaciones(empleadoAux.getEmpCedula(), concepto);
			   
			   if (numProviVacaciones > 24 ){
				   provisionRepository.updateProviVacaciones(empleadoAux.getEmpCedula(), concepto, "N");
			   }
			};
			if(empleadoAux.isEmp_liquida()){
				System.out.println(empleadoAux.getEmpCedula()+"liq: "+empleadoAux.isEmp_liquida()+"vacas "+empleadoAux.isEmp_vacaciones());
			}
			
			
		}
	}; 
/*
	public List<Empleado> findEmpleadoEntries(int firstResult, int maxResults) {
        return empleadoReprository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveEmpleado(Empleado empleado) {
        empleadoReprository.save(empleado);
    }

	public Empleado updateEmpleado(Empleado empleado) {
        return empleadoReprository.save(empleado);
    }*/
}
