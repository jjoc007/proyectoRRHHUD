package co.edu.udistrital.rrhh.service.impl;

import co.edu.udistrital.rrhh.domain.Aporte;
import co.edu.udistrital.rrhh.domain.Concepto;
import co.edu.udistrital.rrhh.domain.Empleado;
import co.edu.udistrital.rrhh.domain.Historicocargo;
import co.edu.udistrital.rrhh.domain.Pago;
import co.edu.udistrital.rrhh.domain.Provision;
import co.edu.udistrital.rrhh.service.AporteService;
import co.edu.udistrital.rrhh.service.ConceptoService;
import co.edu.udistrital.rrhh.service.EmpleadoService;
import co.edu.udistrital.rrhh.service.HistoricocargoService;
import co.edu.udistrital.rrhh.service.LiquidacionService;
import co.edu.udistrital.rrhh.service.PagoService;
import co.edu.udistrital.rrhh.service.ProvisionService;
import co.edu.udistrital.rrhh.web.util.Constantes;
import co.edu.udistrital.rrhh.web.util.NominaException;
import co.edu.udistrital.rrhh.web.util.Utilidades;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

	public Pago pago;
	public Aporte aporte;
	public Provision provision;

	public void Liquidar(List<Empleado> allEmpleados, Calendar periodo) throws NominaException {

		Integer numProviVacaciones;
		Integer mes;
		List<Provision> provisionesRep;
		StringBuffer contenidoArchivo = new StringBuffer("CEDULA;NOMBRE;CUENTA;TOTAL DEVENGOS; TOTAL DEDUCIDOS;SALARIO \n");

		//Verificar en la tabla Proceso

		//	throw new NominaException("No se puede realizar el proceso de liquidación ya que no existe liquidación de prestaciones");


		/*	System.out
			.println("No se puede realizar el proceso de liquidación ya que no existe liquidación de prestaciones");*/


		for (Empleado empleadoAux : allEmpleados) {

			provisionesRep = provisionService.findProvisiones(
					empleadoAux.getEmpCedula(), Constantes.CONCEPTO_VACACIONES,
					Constantes.PROV_ACTIVA);

			if (provisionesRep != null)
				numProviVacaciones = provisionesRep.size();
			else
				numProviVacaciones = 0;

			if  (numProviVacaciones >= 12) {

				throw new NominaException("Alerta el empleado "+empleadoAux.getEmpCedula()+" ya tiene "+numProviVacaciones+" meses acumulados sin tomar vacaciones");
				//System.out.println("Alerta el empleado "+empleadoAux.getEmpCedula()+" ya tiene "+numProviVacaciones+" meses acumulados sin tomar vacaciones");

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

				contenidoArchivo.append(empleadoAux.getEmpCedula()+";"+empleadoAux.getEmpNombre()+";"+empleadoAux.getEmpCuentaPago()+";"+calcularTotalDevengados(empleadoAux, periodo.getTime())+";"+calcularTotalDeducciones(empleadoAux.getEmpCedula(), periodo.getTime())+";"+calcularSalarioEmpleado(empleadoAux, periodo.getTime())+"\n");
		}
		
		generarArchivoPlano(contenidoArchivo, periodo.getTime());

		//Insertar en la tabla proceso con Constantes.LIQUIDACION_NOMINA y el periodo.getTime()
	};

	public void procesarPrima(Empleado empleado, Calendar periodo) {

		Double prima = 0.0;

		List<Provision> provisionesRep = provisionService.findProvisiones(
				empleado.getEmpCedula(), Constantes.CONCEPTO_PRIMA,
				Constantes.PROV_ACTIVA);

		for (Provision provisionAux : provisionesRep) {

			prima += provisionAux.getProValor();
			provisionAux.setProEstado(Constantes.PROV_PAGADA);
			provisionService.saveProvision(provisionAux);

		}

		Concepto concepto = conceptoService.findConcepto(Constantes.CONCEPTO_PRIMA);

		pagoService.realizarPago(concepto, Constantes.PAGO_ACTIVO, empleado, periodo.getTime(), prima);

	}

	public void procesarCesantias(Empleado empleado, Calendar periodo) {

		Double cesantias = 0.0;
		Double interesesCesantias = 0.0;

		List<Provision> provisionesRep = provisionService.findProvisiones(
				empleado.getEmpCedula(), Constantes.CONCEPTO_CESANTIAS,
				Constantes.PROV_ACTIVA);

		for (Provision provisionAux : provisionesRep) {

			cesantias += provisionAux.getProValor();
			provisionAux.setProEstado(Constantes.PROV_PAG_ENTIDAD);
			provisionService.saveProvision(provisionAux);

		}

		aporteService.realizarAporte(empleado.getEntidadCesantias().getEntCodigo(), Constantes.APORTE_CESANTIAS, periodo.getTime(),
				cesantias, Constantes.APO_EMPRESA);

		List<Provision> provisioneInte = provisionService
				.findProvisiones(empleado.getEmpCedula(),
						Constantes.CONCEPTO_INTERESES_CESANTIAS,
						Constantes.PROV_ACTIVA);

		for (Provision provisionAux : provisioneInte) {

			interesesCesantias += provisionAux.getProValor();
			provisionAux.setProEstado(Constantes.PROV_PAGADA);
			provisionService.saveProvision(provisionAux);

		}
		Concepto concepto = conceptoService
				.findConcepto(Constantes.CONCEPTO_INTERESES_CESANTIAS);

		pagoService.realizarPago(concepto, Constantes.PAGO_ACTIVO, empleado,
				periodo.getTime(), interesesCesantias);

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


	public Integer recuperarAfiliacion(Integer cedulaEmpleado, String tipoEntidad) {

		Integer entidad = 0;
		// entidad = recuperar la entidad
		return entidad;
	}

	public void liquidaEmpleado(Empleado empleado, Date periodo) {

		Concepto concepto = new Concepto();
		Double prima = 0.0;		
		Double interesCesantias = 0.0;	
		Double cesantias = 0.0;
		Double vacaciones  = 0.0;

		List<Provision> provisiones = provisionService.findProvisiones(
				empleado.getEmpCedula(), Constantes.CONCEPTO_VACACIONES,
				Constantes.PROV_ACTIVA);

		for (Provision provisionAux : provisiones) {
			vacaciones += provisionAux.getProValor();
			provisionAux.setProEstado(Constantes.PROV_PAGADA);
			provisionService.saveProvision(provisionAux);
		}

		concepto = conceptoService
				.findConcepto(Constantes.CONCEPTO_VACACIONES);

		pagoService.realizarPago(concepto, Constantes.PAGO_ACTIVO, empleado,
				periodo, vacaciones);

		provisiones = null;

		provisiones = provisionService.findProvisiones(empleado.getEmpCedula(),
				Constantes.CONCEPTO_CESANTIAS, Constantes.PROV_ACTIVA);

		for (Provision provisionAux : provisiones) {
			cesantias +=  provisionAux.getProValor();
			provisionAux.setProEstado(Constantes.PROV_PAGADA);
			provisionService.saveProvision(provisionAux);
		}

		concepto = conceptoService
				.findConcepto(Constantes.CONCEPTO_CESANTIAS);

		pagoService.realizarPago(concepto, Constantes.PAGO_ACTIVO, empleado,
				periodo, cesantias);

		provisiones = null;

		provisiones = provisionService
				.findProvisiones(empleado.getEmpCedula(),
						Constantes.CONCEPTO_INTERESES_CESANTIAS,
						Constantes.PROV_ACTIVA);

		for (Provision provisionAux : provisiones) {
			interesCesantias +=  provisionAux.getProValor();
			provisionAux.setProEstado(Constantes.PROV_PAGADA);
			provisionService.saveProvision(provisionAux);
		}

		concepto = conceptoService
				.findConcepto(Constantes.CONCEPTO_INTERESES_CESANTIAS);

		pagoService.realizarPago(concepto, Constantes.PAGO_ACTIVO, empleado, periodo, interesCesantias);

		provisiones = null;

		provisiones = provisionService.findProvisiones(empleado.getEmpCedula(),
				Constantes.CONCEPTO_PRIMA, Constantes.PROV_ACTIVA);

		for (Provision provisionAux : provisiones) {
			prima += provisionAux.getProValor();
			provisionAux.setProEstado(Constantes.PROV_PAGADA);
			provisionService.saveProvision(provisionAux);
		}

		concepto = conceptoService
				.findConcepto(Constantes.CONCEPTO_PRIMA);

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

}
