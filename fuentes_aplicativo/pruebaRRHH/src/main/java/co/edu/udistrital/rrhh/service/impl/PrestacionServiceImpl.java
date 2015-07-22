package co.edu.udistrital.rrhh.service.impl;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udistrital.rrhh.domain.Aporte;
import co.edu.udistrital.rrhh.domain.Concepto;
import co.edu.udistrital.rrhh.domain.Empleado;
import co.edu.udistrital.rrhh.domain.Pago;
import co.edu.udistrital.rrhh.domain.Proceso;
import co.edu.udistrital.rrhh.domain.Provision;
import co.edu.udistrital.rrhh.repository.AporteRepository;
import co.edu.udistrital.rrhh.repository.ConceptoRepository;
import co.edu.udistrital.rrhh.repository.PagoRepository;
import co.edu.udistrital.rrhh.repository.ProvisionRepository;
import co.edu.udistrital.rrhh.service.ConceptoService;
import co.edu.udistrital.rrhh.service.PrestacionService;
import co.edu.udistrital.rrhh.service.ProcesoService;
import co.edu.udistrital.rrhh.web.util.Constantes;
import co.edu.udistrital.rrhh.web.util.NominaException;
import co.edu.udistrital.rrhh.web.util.Utilidades;

@Service
@Transactional
public class PrestacionServiceImpl implements PrestacionService {

	@Autowired
    ConceptoRepository conceptoRepository;
	
	@Autowired
	AporteRepository aporteRepository;
	
	@Autowired
	PagoRepository pagoRepository;
	
	@Autowired
	ProvisionRepository provisionRepository;
	
	@Autowired
    ProcesoService procesoService;
	
	@Autowired
	ConceptoService conceptoService;
	
	public static final int EMPLEADO = 0;
	public static final int EMPRESA = 1;
	
	public Pago pago;
	public Aporte aporte;
	public Provision provision;
	public Proceso proceso = new Proceso();
	
	@Override
	public StringBuffer liquidarPrestaciones(List<Empleado> allEmpleados, Calendar periodo) throws NominaException {

		//Verificar proceso de liquidacion de prestaciones
		proceso = procesoService.consultarProceso(Constantes.LIQUIDACION_PRESTACIONES, periodo.getTime());
		
		if (proceso != null){
			
			throw new NominaException("No se puede realizar el proceso de liquidación de prestaciones, YA existe liquidación de prestaciones para el período "+Utilidades.dateFormat(periodo.getTime()));
			
		}
		
		List<Concepto> listaAllConceptos = conceptoService.findAllConceptosByEstado(Constantes.GENERAL_ESTADO_ACTIVO);
		List<Concepto> listaConceptosProvisionables = getAllConceptosProvisionables(listaAllConceptos);
		List<Concepto> listaConceptosAporte = getAllConceptosAporte(listaAllConceptos);
		
		HashMap<Integer, List<Provision>> mapProvisionesPorEmpleado = new HashMap<Integer, List<Provision>>();
		List<Provision> provisionesEmpleado = null;
		List<Aporte> aportesEmpleado = null;
		List<Pago> pagosEmpleado = null;
		
		Provision provisionEmpleado = null;
		Aporte aporteEmpleado = null;
		Pago pagoEmpleado = null;
		
		int entidad = 0;
		String tipoAporte = null;
		Double[] valorConcepto = null;
		for (Empleado empleadoAux : allEmpleados) {
			
			valorConcepto = new Double[]{0D, 0D};
			provisionesEmpleado = new ArrayList<Provision>(); 
			
			for (Concepto concepto : listaConceptosProvisionables) {
				
				valorConcepto = calcularConcepto(empleadoAux.getCargo().getCarSalario(),concepto);
				provisionEmpleado = new  Provision(
							empleadoAux.getEmpCedula(),
							concepto.getConCodigo(),
							periodo.getTime(),
							valorConcepto[EMPLEADO],
							Constantes.PROV_ACTIVA);
				provisionesEmpleado.add(provisionEmpleado);
			}
			
			aportesEmpleado = new ArrayList<Aporte>();
			pagosEmpleado = new ArrayList<Pago>();
			
			for (Concepto concepto : listaConceptosAporte) {
				
				valorConcepto = calcularConcepto(empleadoAux.getCargo().getCarSalario(),concepto);
				switch (concepto.getConCodigo()) {
					case Constantes.CONCEPTO_ARL:{
						entidad = empleadoAux.getEntidadArp().getEntCodigo();
						tipoAporte = Constantes.APORTE_ARL;
					}break;
	
					case Constantes.CONCEPTO_SALUD:{
						entidad = empleadoAux.getEntidadSalud().getEntCodigo();
						tipoAporte = Constantes.APORTE_SALUD;
					}break;
	
					case Constantes.CONCEPTO_PENSION:{
						entidad = empleadoAux.getEntidadPension().getEntCodigo();
						tipoAporte = Constantes.APORTE_PENSION;
					}break;
	
					default:{
						entidad = 0;
						tipoAporte = "";
					}break;
				}
				
				if(concepto.getConCodigo() != Constantes.CONCEPTO_ARL){
					pagoEmpleado = new Pago(empleadoAux, 
							concepto, 
							periodo.getTime(), 
							valorConcepto[EMPLEADO],
							Constantes.PAGO_ACTIVO);
					pagosEmpleado.add(pagoEmpleado);
				}
				
				
				aporteEmpleado = new Aporte(entidad, 
						tipoAporte, 
						periodo.getTime(), 
						valorConcepto[EMPLEADO], 
						valorConcepto[EMPRESA]);
				aportesEmpleado.add(aporteEmpleado);
				
			}
			aporteRepository.save(aportesEmpleado);
			provisionRepository.save(provisionesEmpleado);
			pagoRepository.save(pagosEmpleado);
			mapProvisionesPorEmpleado.put(empleadoAux.getEmpCedula(), provisionesEmpleado);
		}
		//Insertar en proceso liquidacion
		
		procesoService.insertarProceso(Constantes.LIQUIDACION_PRESTACIONES, periodo.getTime());
				
		return new StringBuffer();
		
	}
	
	@Override
	public Double[] calcularConcepto(Double sueldoEmpleado, Concepto concepto){
		
		if(concepto == null){
			concepto = conceptoRepository.findOne(Constantes.CONCEPTO_SALUD);
		}
		
		Double[] valorConcepto = new Double[2];
		
		if(concepto.getConTipo().equalsIgnoreCase(Constantes.TIPO_CONCEPTO_PORCENTAJE)){
			valorConcepto[EMPLEADO] = sueldoEmpleado * (concepto.getConValor()/100);
			valorConcepto[EMPRESA] = sueldoEmpleado * (concepto.getConValorEmpresa()/100);
		}else if(concepto.getConTipo().equalsIgnoreCase(Constantes.TIPO_CONCEPTO_VALOR)){
			valorConcepto[EMPLEADO] = concepto.getConValor();
			valorConcepto[EMPRESA] = concepto.getConValorEmpresa();
		}else {
			valorConcepto = new Double[]{0D, 0D};
		}
		return valorConcepto;
	}
	
	@Override
	public List<Concepto> getAllConceptosProvisionables(List<Concepto> listaConceptosProvisionables){
		Iterator<Concepto> iteratorConcepto = listaConceptosProvisionables.iterator();
		List<Concepto> listaConceptosProvisionablesAux = new ArrayList<Concepto>();
		Concepto concepto = null;
		while (iteratorConcepto.hasNext()) {
			concepto = iteratorConcepto.next();
			if(concepto.getConCodigo() == Constantes.CONCEPTO_PRIMA ||
				concepto.getConCodigo() == Constantes.CONCEPTO_VACACIONES ||
				concepto.getConCodigo() == Constantes.CONCEPTO_CESANTIAS ||
				concepto.getConCodigo() == Constantes.CONCEPTO_INTERESES_CESANTIAS){
				listaConceptosProvisionablesAux.add(concepto);
			}
		}
		return listaConceptosProvisionablesAux;
	}
	
	@Override
	public List<Concepto> getAllConceptosAporte(List<Concepto> listaConceptosAporte){
		Iterator<Concepto> iteratorConcepto = listaConceptosAporte.iterator();
		List<Concepto> listaConceptosAporteAux = new ArrayList<Concepto>();
		Concepto concepto = null;
		while (iteratorConcepto.hasNext()) {
			concepto = iteratorConcepto.next();
			if(concepto.getConCodigo() == Constantes.CONCEPTO_SALUD ||
				concepto.getConCodigo() == Constantes.CONCEPTO_PENSION ||
				concepto.getConCodigo() == Constantes.CONCEPTO_ARL){
				listaConceptosAporteAux.add(concepto);
			}
		}
		return listaConceptosAporteAux;
	}
	
}
