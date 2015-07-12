package co.edu.udistrital.rrhh.service.impl;
import co.edu.udistrital.rrhh.domain.Concepto;
import co.edu.udistrital.rrhh.repository.ConceptoRepository;
import co.edu.udistrital.rrhh.service.PrestacionService;
import co.edu.udistrital.rrhh.web.util.Constantes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PrestacionServiceImpl implements PrestacionService {

	@Autowired
    ConceptoRepository conceptoRepository;
	
	@Override
	public Double calcularCesantias(Double sueldoEmpleado){
		
		Concepto cesantias = conceptoRepository.findOne(Constantes.CONCEPTO_CESANTIAS);
		
		Double valorCesantias = new Double(0);
		
		if(cesantias.getConTipo().equalsIgnoreCase(Constantes.TIPO_CONCEPTO_PORCENTAJE)){
			valorCesantias = sueldoEmpleado * (cesantias.getConValor()/100);
		}else if(cesantias.getConTipo().equalsIgnoreCase(Constantes.TIPO_CONCEPTO_VALOR)){
			valorCesantias = cesantias.getConValor();
		}
		return valorCesantias;
	}
	
	@Override
	public Double calcularInteresesCesantias(Double valorCesantias){
		
		Concepto interesesCesantias = conceptoRepository.findOne(Constantes.CONCEPTO_INTERESES_CESANTIAS);
		
		Double valorInteresesCesantias = new Double(0);
		
		if(interesesCesantias.getConTipo().equalsIgnoreCase(Constantes.TIPO_CONCEPTO_PORCENTAJE)){
			valorInteresesCesantias = valorCesantias * (interesesCesantias.getConValor()/100);
		}else if(interesesCesantias.getConTipo().equalsIgnoreCase(Constantes.TIPO_CONCEPTO_VALOR)){
			valorInteresesCesantias = interesesCesantias.getConValor();
		}
		return valorInteresesCesantias;
	}
	
	@Override
	public Double calcularPrima(Double sueldoEmpleado){
		
		Concepto prima = conceptoRepository.findOne(Constantes.CONCEPTO_INTERESES_CESANTIAS);
		
		Double valorPrima = new Double(0);
		
		if(prima.getConTipo().equalsIgnoreCase(Constantes.TIPO_CONCEPTO_PORCENTAJE)){
			valorPrima = sueldoEmpleado * (prima.getConValor()/100);
		}else if(prima.getConTipo().equalsIgnoreCase(Constantes.TIPO_CONCEPTO_VALOR)){
			valorPrima = prima.getConValor();
		}
		return valorPrima;
	}
	
	@Override
	public Double calcularVacaciones(Double sueldoEmpleado){
		
		Concepto prima = conceptoRepository.findOne(Constantes.CONCEPTO_INTERESES_CESANTIAS);
		
		Double valorPrima = new Double(0);
		
		if(prima.getConTipo().equalsIgnoreCase(Constantes.TIPO_CONCEPTO_PORCENTAJE)){
			valorPrima = sueldoEmpleado * (prima.getConValor()/100);
		}else if(prima.getConTipo().equalsIgnoreCase(Constantes.TIPO_CONCEPTO_VALOR)){
			valorPrima = prima.getConValor();
		}
		return valorPrima;
	}

/*calcularVacaciones
{
obtenerParametro(id_VACACIONES) retorna objeto de tipo concepto
Si el tipo de concepto es porcentaje haga
{
multiplicar p_Sueldo por el valor del parametro/100
} Sino haga
{
retornar el valor del parametro
}
}*/

	
}
