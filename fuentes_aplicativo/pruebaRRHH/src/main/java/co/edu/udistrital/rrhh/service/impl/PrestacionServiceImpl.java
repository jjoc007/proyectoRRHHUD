package co.edu.udistrital.rrhh.service.impl;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import co.edu.udistrital.rrhh.domain.Aporte;
import co.edu.udistrital.rrhh.domain.Concepto;
import co.edu.udistrital.rrhh.domain.Empleado;
import co.edu.udistrital.rrhh.domain.Pago;
import co.edu.udistrital.rrhh.domain.Proceso;
import co.edu.udistrital.rrhh.domain.Provision;
import co.edu.udistrital.rrhh.repository.ConceptoRepository;
import co.edu.udistrital.rrhh.service.ConceptoService;
import co.edu.udistrital.rrhh.service.PrestacionService;
import co.edu.udistrital.rrhh.service.ProcesoService;
import co.edu.udistrital.rrhh.web.util.Constantes;
import co.edu.udistrital.rrhh.web.util.NominaException;
import co.edu.udistrital.rrhh.web.util.Utilidades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jca.cci.CciOperationNotSupportedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PrestacionServiceImpl implements PrestacionService {

	@Autowired
    ConceptoRepository conceptoRepository;
	
	@Autowired
    ProcesoService procesoService;
	
	@Autowired
	ConceptoService conceptoService;
	
	public Pago pago;
	public Aporte aporte;
	public Provision provision;
	public Proceso proceso = new Proceso();

	@Override
	public StringBuffer liquidarPrestaciones(List<Empleado> allEmpleados, Calendar periodo) throws NominaException {

		StringBuffer alertasVacaciones = new StringBuffer();

		//Verificar proceso de liquidacion de prestaciones
		proceso = procesoService.consultarProceso(Constantes.LIQUIDACION_PRESTACIONES, periodo.getTime());
		
		if (proceso == null){
			throw new NominaException("No se puede realizar el proceso de liquidación, NO existe liquidación de prestaciones para el período "+Utilidades.dateFormat(periodo.getTime()));
		}

		//Verificar proceso de liquidacion de prestaciones
		proceso = procesoService.consultarProceso(Constantes.LIQUIDACION_PRESTACIONES, periodo.getTime());
		
		if (proceso != null){
			
			throw new NominaException("No se puede realizar el proceso de liquidación de prestaciones, YA existe liquidación de prestaciones para el período "+Utilidades.dateFormat(periodo.getTime()));
			
		}
		
		List<Concepto> listaAllConceptos = conceptoService.findAllConceptosByEstado(Constantes.GENERAL_ESTADO_ACTIVO);
		List<Concepto> listaConceptosProvisionables = getAllConceptosProvisionables(listaAllConceptos);
		List<Concepto> listaConceptosAporte = getAllConceptosAporte(listaAllConceptos);
		
		HashMap<Integer, List<Provision>> mapProvisionesPorEmpleado = new HashMap<Integer, List<Provision>>();
		Provision provisionEmpleado = null;
		Aporte aporteEmpleado = null;
		List<Provision> provisionesEmpleado = null;
		Double valorConcepto = 0D; 
		for (Empleado empleadoAux : allEmpleados) {
			provisionesEmpleado = new ArrayList<Provision>(); 
			
			for (Concepto concepto : listaConceptosProvisionables) {
				
				switch (concepto.getConCodigo()) {
					case Constantes.CONCEPTO_PRIMA:{
						valorConcepto = calcularPrima(empleadoAux.getCargo().getCarSalario());
					}break;
					
					case Constantes.CONCEPTO_VACACIONES:{
						valorConcepto = calcularVacaciones(empleadoAux.getCargo().getCarSalario());
					}break;
					
					case Constantes.CONCEPTO_CESANTIAS:{
						valorConcepto = calcularCesantias(empleadoAux.getCargo().getCarSalario());
					}break;
					
					case Constantes.CONCEPTO_INTERESES_CESANTIAS:{
						valorConcepto = calcularInteresesCesantias(empleadoAux.getCargo().getCarSalario());
					}break;
	
					default:{
						valorConcepto = 0D;
					}break;
				}
				
				provisionEmpleado = new  Provision(
							empleadoAux.getEmpCedula(),
							concepto.getConCodigo(),
							periodo.getTime(),
							valorConcepto,//Valor
							Constantes.PROV_ACTIVA,
							null);//Código (Autogenerado)
				provisionesEmpleado.add(provisionEmpleado);
			}
			
			for (Concepto concepto : listaConceptosAporte) {
				/*aporteEmpleado = new Aporte(
						empleadoAux.get
						);*/
				provisionEmpleado = new  Provision(
							empleadoAux.getEmpCedula(),
							concepto.getConCodigo(),
							periodo.getTime(),
							null,//Valor
							Constantes.PROV_ACTIVA,
							null);//Código
				provisionesEmpleado.add(provisionEmpleado);
			}
			mapProvisionesPorEmpleado.put(empleadoAux.getEmpCedula(), provisionesEmpleado);
		}
		return alertasVacaciones;
		
	}
	
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
	
	public List<Concepto> getAllConceptosProvisionables(List<Concepto> listaConceptosProvisionables){
		Iterator<Concepto> iteratorConcepto = listaConceptosProvisionables.iterator();
		Concepto concepto = null;
		while (iteratorConcepto.hasNext()) {
			concepto = iteratorConcepto.next();
			if(concepto.getConCodigo() != Constantes.CONCEPTO_PRIMA &&
				concepto.getConCodigo() != Constantes.CONCEPTO_VACACIONES &&
				concepto.getConCodigo() != Constantes.CONCEPTO_CESANTIAS &&
				concepto.getConCodigo() != Constantes.CONCEPTO_INTERESES_CESANTIAS){
				iteratorConcepto.remove();
			}
		}
		return listaConceptosProvisionables;
	}
	
	public List<Concepto> getAllConceptosAporte(List<Concepto> listaConceptosAporte){
		Iterator<Concepto> iteratorConcepto = listaConceptosAporte.iterator();
		Concepto concepto = null;
		while (iteratorConcepto.hasNext()) {
			concepto = iteratorConcepto.next();
			if(concepto.getConCodigo() != Constantes.CONCEPTO_SALUD &&
				concepto.getConCodigo() != Constantes.CONCEPTO_PENSION &&
				concepto.getConCodigo() != Constantes.CONCEPTO_ARL){
				iteratorConcepto.remove();
			}
		}
		return listaConceptosAporte;
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
