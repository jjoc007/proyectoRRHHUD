package co.edu.udistrital.rrhh.service.impl;

import co.edu.udistrital.rrhh.domain.Aporte;
import co.edu.udistrital.rrhh.domain.Concepto;
import co.edu.udistrital.rrhh.domain.Empleado;
import co.edu.udistrital.rrhh.domain.Historicocargo;
import co.edu.udistrital.rrhh.domain.Pago;
import co.edu.udistrital.rrhh.domain.Proceso;
import co.edu.udistrital.rrhh.domain.Provision;
import co.edu.udistrital.rrhh.service.AporteService;
import co.edu.udistrital.rrhh.service.ConceptoService;
import co.edu.udistrital.rrhh.service.EmpleadoService;
import co.edu.udistrital.rrhh.service.HistoricocargoService;
import co.edu.udistrital.rrhh.service.LiquidacionService;
import co.edu.udistrital.rrhh.service.PagoService;
import co.edu.udistrital.rrhh.service.ProcesoService;
import co.edu.udistrital.rrhh.service.ProvisionService;
import co.edu.udistrital.rrhh.web.util.Constantes;
import co.edu.udistrital.rrhh.web.util.NominaException;
import co.edu.udistrital.rrhh.web.util.Utilidades;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LiquidacionServiceImpl implements LiquidacionService {

	@Autowired
	PagoService pagoService;
	@Autowired
	AporteService aporteService;
	@Autowired
	ProvisionService provisionService;
	@Autowired
	ConceptoService conceptoService;
	@Autowired
	EmpleadoService empleadoService;
	@Autowired
	HistoricocargoService historicocargoService;
	@Autowired
	ProcesoService procesoService;

	public Pago pago;
	public Aporte aporte;
	public Provision provision;
	public Proceso proceso = new Proceso();

	public StringBuffer Liquidar(List<Empleado> allEmpleados, Calendar periodo) throws NominaException {

		Integer numProviVacaciones;
		Integer mes;
		List<Provision> provisionesRep;
		
		StringBuffer contenidoArchivo = new StringBuffer("CEDULA;NOMBRE;CUENTA;TOTAL DEVENGOS; TOTAL DEDUCIDOS;SALARIO \r\n");
		StringBuffer alertasVacaciones = new StringBuffer();

		//Verificar proceso de liquidacion de prestaciones
		proceso = procesoService.consultarProceso(Constantes.LIQUIDACION_PRESTACIONES, periodo.getTime());
		
		if (proceso == null){
			
			throw new NominaException("No se puede realizar el proceso de liquidación, NO existe liquidación de prestaciones para el período "+Utilidades.dateFormat(periodo.getTime()));
			
		}

		for (Empleado empleadoAux : allEmpleados) {

			provisionesRep = provisionService.findProvisiones(
					empleadoAux.getEmpCedula(), Constantes.CONCEPTO_VACACIONES,
					Constantes.PROV_ACTIVA);

			if (provisionesRep != null)
				numProviVacaciones = provisionesRep.size();
			else
				numProviVacaciones = 0;

			if  (numProviVacaciones >= 12) {

				alertasVacaciones.append("Alerta el empleado "+empleadoAux.getEmpCedula()+" ya tiene "+numProviVacaciones+" meses acumulados sin tomar vacaciones");

			}else if (numProviVacaciones > 24) {

				int cont = 0;

				for (Provision provisionAux : provisionesRep) {

					if (cont < 12) {

						cont += 1;
						provisionAux.setProEstado(Constantes.PROV_NO_APLICA);
						provisionService.saveProvision(provisionAux);

					}
				}

			} 

			if (empleadoAux.isEmp_vacaciones()) {

				if (numProviVacaciones <= 12) {

					throw new NominaException("El empleado: "+ empleadoAux.getEmpCedula()
							+ " NO puede tomar vacaciones no cumple con los periodos necesarios");
				} else {

					int cont = 0;

					for (Provision provisionAux : provisionesRep) {

						if (cont < 12) {

							cont += 1;
							provisionAux.setProEstado(Constantes.PROV_PAGADA);
							provisionService.saveProvision(provisionAux);

						}
					}

				}

			}

			if (empleadoAux.isEmp_liquida()) {

				liquidaEmpleado(empleadoAux, periodo.getTime());
				empleadoAux.setEmpFechaSalida(periodo.getTime());
				empleadoAux.setEmpEstado(Constantes.ESTADO_EMPL_INACTIVO);

				for (Historicocargo histcargoaux : empleadoAux.getHistoricoCargos()){

					if (histcargoaux.getHisEstado().equals(Constantes.GENERAL_ESTADO_ACTIVO)){

						histcargoaux.setHisEstado(Constantes.GENERAL_ESTADO_INACTIVO);
						histcargoaux.setHisFechaFin(periodo.getTime());
						historicocargoService.saveHistoricocargo(histcargoaux);
					}

				}
				empleadoService.saveEmpleado(empleadoAux);

			}

			mes = periodo.get(Calendar.MONTH);

			if (mes == Constantes.MES_CESANTIAS) {

				procesarCesantias(empleadoAux, periodo);

			}

			if (mes == Constantes.MES_PRIMA || mes == Constantes.MES_PRIMA2) {

				procesarPrima(empleadoAux, periodo);

			}

				contenidoArchivo.append(empleadoAux.getEmpCedula()+";"+empleadoAux.getEmpNombre()+";"+empleadoAux.getEmpCuentaPago()+";"+calcularTotalDevengados(empleadoAux, periodo.getTime())+";"+calcularTotalDeducciones(empleadoAux.getEmpCedula(), periodo.getTime())+";"+calcularSalarioEmpleado(empleadoAux, periodo.getTime())+"\r\n");
		}
		
		generarArchivoPlano(contenidoArchivo, periodo.getTime());

		//Insertar en proceso liquidacion
		
		procesoService.insertarProceso(Constantes.LIQUIDACION_NOMINA, periodo.getTime());
		
		//Actualizar periodo liquidacion
		
		proceso = procesoService.consultarProceso(Constantes.PERIODO_LIQUIDACION, periodo.getTime());
		
		periodo.add(Calendar.MONTH, 1);
		proceso.setProPeriodo(periodo.getTime());
		procesoService.saveProceso(proceso);
		
		return alertasVacaciones;
		
	};

	public void procesarPrima(Empleado empleado, Calendar periodo) {

		Double prima = 0.0;
		
		prima = procesarProvisiones(empleado.getEmpCedula(), Constantes.CONCEPTO_PRIMA, Constantes.PROV_PAGADA);

		Concepto concepto = conceptoService.findConcepto(Constantes.CONCEPTO_PRIMA);

		if (prima != 0.0){
			
			pagoService.realizarPago(concepto, Constantes.PAGO_ACTIVO, empleado, periodo.getTime(), prima);
			
		}

	}

	public void procesarCesantias(Empleado empleado, Calendar periodo) {

		Double cesantias = 0.0;
		Double interesesCesantias = 0.0;

		//Provisiones de cesantías
		cesantias = procesarProvisiones(empleado.getEmpCedula(), Constantes.CONCEPTO_CESANTIAS, Constantes.PROV_PAG_ENTIDAD);
		
		if (cesantias != 0.0){
			
			aporteService.realizarAporte(empleado.getEntidadCesantias().getEntCodigo(), Constantes.APORTE_CESANTIAS, periodo.getTime(),
					cesantias, Constantes.APO_EMPRESA);
		}
		
		
		//Provisiones de intereses de cesantías
		interesesCesantias = procesarProvisiones(empleado.getEmpCedula(), Constantes.CONCEPTO_INTERESES_CESANTIAS, Constantes.PROV_PAGADA);

		Concepto concepto = conceptoService.findConcepto(Constantes.CONCEPTO_INTERESES_CESANTIAS);

		if (interesesCesantias != 0.0){
			
			pagoService.realizarPago(concepto, Constantes.PAGO_ACTIVO, empleado, periodo.getTime(), interesesCesantias);
		
		}

	}

	public Double calcularTotalDeducciones(Integer cedulaEmpleado,
			Date periodo) {

		Double totalDeducciones = 0.0;


		List<Pago> pagoRep = pagoService.findPagos(cedulaEmpleado,
				periodo, Constantes.TIPO_CONCEPTO_DEDUCIDO);

		for(Pago pagoAux: pagoRep){

			totalDeducciones += pagoAux.getPagValorPago();

		}

		return totalDeducciones;

	}

	public Double calcularTotalDevengados(Empleado empleado, Date periodo) {

		Double totalDevengados = 0.0;

		List<Pago> pagoRep = pagoService.findPagos(empleado.getEmpCedula(), periodo,
				Constantes.TIPO_CONCEPTO_DEVENGO);

		for(Pago pagoAux: pagoRep){

			totalDevengados += pagoAux.getPagValorPago();

		}

		totalDevengados += empleado.getCargo().getCarSalario();

		return totalDevengados;
	}

	public Double calcularSalarioEmpleado(Empleado empleado, Date periodo) {

		Double salario = 0.0;
		salario = calcularTotalDevengados(empleado, periodo)
				- calcularTotalDeducciones(empleado.getEmpCedula(), periodo);

		System.out.println("DEVENGADOS: "+calcularTotalDevengados(empleado, periodo)+" decucidos: "+calcularTotalDeducciones(empleado.getEmpCedula(), periodo)+" salario "+salario);
		return salario;
	}

	public void liquidaEmpleado(Empleado empleado, Date periodo) {

		Concepto concepto = new Concepto();
		Double prima = 0.0;		
		Double interesCesantias = 0.0;	
		Double cesantias = 0.0;
		Double vacaciones  = 0.0;

		
		vacaciones = procesarProvisiones(empleado.getEmpCedula(), Constantes.CONCEPTO_VACACIONES, Constantes.PROV_PAGADA);

		concepto = conceptoService.findConcepto(Constantes.CONCEPTO_VACACIONES);

		pagoService.realizarPago(concepto, Constantes.PAGO_ACTIVO, empleado, periodo, vacaciones);
		

		cesantias = procesarProvisiones(empleado.getEmpCedula(), Constantes.CONCEPTO_CESANTIAS, Constantes.PROV_PAGADA);

		concepto = conceptoService.findConcepto(Constantes.CONCEPTO_CESANTIAS);

		pagoService.realizarPago(concepto, Constantes.PAGO_ACTIVO, empleado, periodo, cesantias);
		

		interesCesantias = procesarProvisiones(empleado.getEmpCedula(), Constantes.CONCEPTO_INTERESES_CESANTIAS, Constantes.PROV_PAGADA);

		concepto = conceptoService.findConcepto(Constantes.CONCEPTO_INTERESES_CESANTIAS);

		pagoService.realizarPago(concepto, Constantes.PAGO_ACTIVO, empleado, periodo, interesCesantias);
		

		prima = procesarProvisiones(empleado.getEmpCedula(), Constantes.CONCEPTO_PRIMA, Constantes.PROV_PAGADA);

		concepto = conceptoService.findConcepto(Constantes.CONCEPTO_PRIMA);

		pagoService.realizarPago(concepto, Constantes.PAGO_ACTIVO, empleado, periodo, prima);

	}


	public void generarArchivoPlano(StringBuffer contenidoArchivo, Date periodo){


		BufferedWriter out = null;   
		try {   
			out = new BufferedWriter(new FileWriter(Constantes.RUTA_ARCHIVO_PLANO+Utilidades.dateFormatedToFile(periodo)+".txt", true));   
			out.write(contenidoArchivo.toString()+"\r"); 

		} catch (IOException e) {   
			// error processing code   
		} finally {   
			if (out != null) {   
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}   
			}   
		}

	}
	
	public void saveConceptosLiq(List<Empleado> allEmpleadosWithPagos, Date periodo) throws NominaException{
		
		//Verificar proceso de conceptos liquidacion
		Proceso proceso = new Proceso();
		
		proceso = procesoService.consultarProceso(Constantes.CONCEPTOS_LIQUIDACION, periodo);
		
		if (proceso != null){
			
			throw new NominaException("Ya existe proceso de conceptos liquidación para el período "+ Utilidades.dateFormat(periodo));
			
		}
		
		for (Empleado empleadoaux : allEmpleadosWithPagos){
			
			for (Pago pagoaux : empleadoaux.getPagos()){
				
				if (pagoaux.getPagValorPago() != 0.0){
					pagoService.savePago(pagoaux);
				}
				
			}	
		}
		
		//Insertar registro en la tabla proceso 
		
		procesoService.insertarProceso(Constantes.CONCEPTOS_LIQUIDACION, periodo);		
		
	}
	
	public List<Empleado> fillPagosEmpleado(List<Empleado> allEmpleados, Date periodo){
		
		List<Concepto> conceptos =  new ArrayList<Concepto>();
		List<Empleado> allEmpleadosWithPagos = new ArrayList<Empleado>();
		
		List<String> tipoPer =  new ArrayList<String>();
				
		tipoPer.add(Constantes.TIPO_CONCEPTO_DEVENGO);
		tipoPer.add(Constantes.TIPO_CONCEPTO_DEDUCIDO);
		
		//Busca todos los conceptos que sean de tipo devengo y deducido		
		conceptos = conceptoService.findAllConceptoLiq(Constantes.GENERAL_ESTADO_ACTIVO, tipoPer);
		
		for (Empleado empleadoAux: allEmpleados){

			List<Pago> pagosPorEmpleado =  new ArrayList<Pago>();
			
			for(Concepto conceptoAux: conceptos){

				pagosPorEmpleado.add(new Pago(empleadoAux, conceptoAux, periodo,	0.0D, Constantes.PAGO_ACTIVO, null));
				
			}
			
			empleadoAux.setPagos(pagosPorEmpleado);
			allEmpleadosWithPagos.add(empleadoAux);

		}
		
		return allEmpleadosWithPagos;
	}
	
	public Calendar periodoLiquidacion(){
		
		//Recuperar de la tabla proceso el periodo actual
		Proceso proceso = new Proceso();
		proceso = procesoService.consultarProcesobyName(Constantes.PERIODO_LIQUIDACION);
		
		Calendar periodoLiq =  Calendar.getInstance();
		periodoLiq.setTime(proceso.getProPeriodo());
		
		return periodoLiq;
	}

	
	public Double procesarProvisiones(Integer cedulaEmpleado, Integer concepto, String newEstado){
		
		Double valor = 0.0;
		List<Provision> provisionesRep = null;
		
		provisionesRep = provisionService.findProvisiones(cedulaEmpleado, concepto, Constantes.PROV_ACTIVA);

		for (Provision provisionAux : provisionesRep) {

			valor += provisionAux.getProValor();
			provisionAux.setProEstado(newEstado);
			provisionService.saveProvision(provisionAux);

		}
		
		return valor;
	}
	

}
