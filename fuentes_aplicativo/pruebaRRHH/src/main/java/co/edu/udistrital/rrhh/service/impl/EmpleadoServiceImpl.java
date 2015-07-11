package co.edu.udistrital.rrhh.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udistrital.rrhh.domain.Concepto;
import co.edu.udistrital.rrhh.domain.Empleado;
import co.edu.udistrital.rrhh.repository.ConceptoRepository;
import co.edu.udistrital.rrhh.repository.EmpleadoRepository;
import co.edu.udistrital.rrhh.service.EmpleadoService;
import co.edu.udistrital.rrhh.web.util.Constantes;

@Service
@Transactional
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
    EmpleadoRepository empleadoReprository;
	
	@Autowired
	ConceptoRepository conceptoRepository;
	
	@Override
	public Double calcularSubsidoTransporte(Double sueldoEmpleado){
		
		Concepto smlv = conceptoRepository.findOne(Constantes.CONCEPTO_SMLV);
		Concepto subisidioTransporte = conceptoRepository.findOne(Constantes.CONCEPTO_TRANSPORTE);
		
		Double valorSubsidioTranporte = new Double(0);
		if((Constantes.CANTIDAD_SMLV_TRANSPORTE * smlv.getConValor()) < sueldoEmpleado.doubleValue()){
			
			if(subisidioTransporte.getConTipo().equalsIgnoreCase(Constantes.TIPO_CONCEPTO_PORCENTAJE)){
				valorSubsidioTranporte = sueldoEmpleado * (subisidioTransporte.getConValor()/100);
			}else if(subisidioTransporte.getConTipo().equalsIgnoreCase(Constantes.TIPO_CONCEPTO_VALOR)){
				valorSubsidioTranporte = subisidioTransporte.getConValor();
			}
		}
		return valorSubsidioTranporte;
	}
	
	public long countAllEmpleadoes() {
        return empleadoReprository.count();
    }

	public void deleteEmpleado(Empleado empleado) {
        empleadoReprository.delete(empleado);
    }

	public Empleado findEmpleado(Integer id) {
        return empleadoReprository.findOne(id);
    }

	public List<Empleado> findAllEmpleadoes() {
        return empleadoReprository.findAll();
    }

	public List<Empleado> findEmpleadoEntries(int firstResult, int maxResults) {
        return empleadoReprository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveEmpleado(Empleado empleado) {
        empleadoReprository.save(empleado);
    }

	public Empleado updateEmpleado(Empleado empleado) {
        return empleadoReprository.save(empleado);
    }
	
	public List<Empleado> findAllEmpleadosAct(String estado) {
        return empleadoReprository.findAllEmpleadosAct(estado);
    }
	
}
