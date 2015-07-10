package co.edu.udistrital.rrhh.service.impl;

import co.edu.udistrital.rrhh.domain.Aporte;
import co.edu.udistrital.rrhh.domain.Concepto;
import co.edu.udistrital.rrhh.domain.Empleado;
import co.edu.udistrital.rrhh.domain.Pago;
import co.edu.udistrital.rrhh.domain.Provision;

import co.edu.udistrital.rrhh.service.AporteService;
import co.edu.udistrital.rrhh.service.ConceptoService;
import co.edu.udistrital.rrhh.service.LiquidacionService;
import co.edu.udistrital.rrhh.service.PagoService;
import co.edu.udistrital.rrhh.service.ProvisionService;
import co.edu.udistrital.rrhh.web.util.Constantes;

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

	public Pago pago = new Pago();
	public Aporte aporte = new Aporte();

	public void Liquidar(List<Empleado> allEmpleados, Calendar periodo) {

		Integer numProviVacaciones;
		Integer mes;

		for (Empleado empleadoAux : allEmpleados) {

			List<Provision> provisionesRep = provisionService.findProvisiones(
					empleadoAux.getEmpCedula(), Constantes.CONCEPTO_VACACIONES,
					Constantes.PROV_ACTIVA);

			if (provisionesRep != null)
				numProviVacaciones = provisionesRep.size();
			else
				numProviVacaciones = 0;

			System.out.println("numero de provisiones:" + numProviVacaciones);

			if (numProviVacaciones > 24) {

				int cont = 0;

				for (Provision provisionAux : provisionesRep) {
					cont += 1;

					if (cont < 12) {

						provisionAux.setProEstado(Constantes.PROV_NO_APLICA);
						provisionService.saveProvision(provisionAux);

					}
				}

			}

			if (empleadoAux.isEmp_vacaciones()) {

				if (numProviVacaciones <= 12) {

					System.out
							.println("El empleado: "
									+ empleadoAux.getEmpCedula()
									+ " NO puede tomar vacaciones no cumple con los periodos necesarios");
				} else {

					int cont = 0;

					for (Provision provisionAux : provisionesRep) {

						cont += 1;

						if (cont < 12) {

							provisionAux.setProEstado(Constantes.PROV_PAGADA);
							provisionService.saveProvision(provisionAux);

						}
					}

				}

			}

			mes = periodo.get(Calendar.MONTH);

			System.out.println("mes: " + mes + " Constantes.MES_CESANTIAS "
					+ Constantes.MES_CESANTIAS);

			if (mes == Constantes.MES_CESANTIAS) {
				System.out.println("entrp cesa: ");
				procesarCesantias(empleadoAux, periodo);
				System.out.println("salio cesa: " + mes);
			}

			if (mes == Constantes.MES_PRIMA || mes == Constantes.MES_PRIMA2) {
				System.out.println("entro prima: " + mes);
				procesarPrima(empleadoAux, periodo);
				System.out.println("salio prima: " + mes);
			}

			if (empleadoAux.isEmp_liquida()) {

				liquidaEmpleado(empleadoAux.getEmpCedula());
				// empleadoRepository.actualizaFechaSalida(empleadoAux.getEmpCedula(),
				// periodo);
				// calcularSalarioEmpleado(empleadoAux.getEmpCedula(), periodo);

			}
		}
	};

	public void procesarPrima(Empleado empleado, Calendar periodo) {

		Double prima = 0.0;

		/*
		 * obtener la suma de la tabla provisiones donde el concepto sea
		 * Constantes.CONCEPTO_PRIMA, insertar esa suma en la tabla pago y
		 * actualizar esos registros de la tabla provision con estado P
		 */

		List<Provision> provisionesRep = provisionService.findProvisiones(
				empleado.getEmpCedula(), Constantes.CONCEPTO_PRIMA,
				Constantes.PROV_ACTIVA);

		for (Provision provisionAux : provisionesRep) {

			provisionAux.setProEstado(Constantes.PROV_PAGADA);
			provisionService.saveProvision(provisionAux);

		}
		Concepto concepto = conceptoService
				.findConcepto(Constantes.CONCEPTO_PRIMA);
		realizarPago(concepto, Constantes.PAGO_ACTIVO, empleado,
				periodo.getTime(), prima);

	}

	public void procesarCesantias(Empleado empleado, Calendar periodo) {

		Double cesantias = 0.0;
		Double interesesCesantias = 0.0;
		//Integer entidad;
		System.out.println("ant prov:");
		List<Provision> provisionesRep = provisionService.findProvisiones(
				empleado.getEmpCedula(), Constantes.CONCEPTO_CESANTIAS,
				Constantes.PROV_ACTIVA);
		System.out.println("desp prov:");
		for (Provision provisionAux : provisionesRep) {
			System.out.println("provision: " + provisionAux.getProValor());
			cesantias += provisionAux.getProValor();
			provisionAux.setProEstado(Constantes.PROV_PAG_ENTIDAD);
			provisionService.saveProvision(provisionAux);

		}

		// entidad = recuperarAfiliacion(cedulaEmpleado, "ENTIDAD_CESANTIAS");
		System.out.println("ant aporte:" + cesantias);
		realizarAporte(1, Constantes.APORTE_CESANTIAS, periodo.getTime(),
				cesantias);

		System.out.println("desp aporte:");
		/*
		 * obtener la suma de la tabla provisiones donde el concepto sea
		 * Constantes.CONCEPTO_CESANTIAS, insertar esa suma en la aporte y
		 * actualizar esos registros de la tabla provision con estado P
		 */

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
		realizarPago(concepto, Constantes.PAGO_ACTIVO, empleado,
				periodo.getTime(), interesesCesantias);

		/*
		 * obtener la suma de la tabla provisiones donde el concepto sea
		 * Constantes.CONCEPTO_INTERESES_CESANTIAS, insertar esa suma en la
		 * tabla pago y actualizar esos registros de la tabla provision con
		 * estado P
		 */

	}

	public Double calcularTotalDeducciones(Integer cedulaEmpleado,
			Calendar periodo) {
		Double totalDeducciones = 0.0;

		/*
		 * List<Pago> pagoRep = pagoReposirory.findPagos(cedulaEmpleado,
		 * periodo, Constantes.TIPO_CONCEPTO_DEDUCIDO);
		 * 
		 * for(Pago pagoAux: pagoRep){
		 * 
		 * totalDeducciones += pagoAux.getPagValorPago();
		 * 
		 * }
		 */
		return totalDeducciones;

	}

	public Double calcularTotalDevengados(Empleado empleado, Calendar periodo) {
		Double totalDevengados = 0.0;
		/*
		 * List<Pago> pagoRep =
		 * pagoReposirory.findPagos(empleado.getEmpCedula(), periodo,
		 * Constantes.TIPO_CONCEPTO_DEVENGO);
		 * 
		 * for(Pago pagoAux: pagoRep){
		 * 
		 * totalDevengados += pagoAux.getPagValorPago();
		 * 
		 * }
		 */
		totalDevengados += empleado.getCargo().getCarSalario();

		return totalDevengados;
	}

	public Double calcularSalarioEmpleado(Empleado empleado, Calendar periodo) {
		Double salario = 0.0;
		salario = calcularTotalDevengados(empleado, periodo)
				- calcularTotalDeducciones(empleado.getEmpCedula(), periodo);
		return salario;
	}

	public void realizarPago(Concepto concepto, String estado,
			Empleado empleado, Date periodo, Double valorPago) {

		pago.setPagConcepto(concepto);
		pago.setPagEstado(estado);
		pago.setPagoEmpleado(empleado);
		pago.setPagPeriodo(periodo);
		pago.setPagValorPago(valorPago);

		pagoService.savePago(pago);
	}

	public Integer recuperarAfiliacion(Integer empleado, String tipoEntidad) {

		Integer entidad = 0;
		// entidad = recuperar la entidad
		return entidad;
	}

	public void realizarAporte(Integer entidad, String tipo, Date periodo,
			Double valor) {

		System.out.println("en apentidad: " + entidad);
		aporte.setApoEntidad(entidad);
		System.out.println("en aptipo: " + tipo);
		aporte.setApoTipo(tipo);
		System.out.println("en apperiodo: " + periodo);
		aporte.setApoPeriodo(periodo);
		System.out.println("en apvalor: " + valor);
		aporte.setApoValor(valor);

		System.out.println("desp valor");
		aporteService.saveAporte(aporte);
		System.out.println("save aporte apentidad:");
	}

	public void liquidaEmpleado(Integer cedulaEmpleado) {

		List<Provision> provisiones = provisionService.findProvisiones(
				cedulaEmpleado, Constantes.CONCEPTO_VACACIONES,
				Constantes.PROV_ACTIVA);

		for (Provision provisionAux : provisiones) {

			provisionAux.setProEstado(Constantes.PROV_PAGADA);
			provisionService.saveProvision(provisionAux);
		}

		provisiones = null;

		provisiones = provisionService.findProvisiones(cedulaEmpleado,
				Constantes.CONCEPTO_CESANTIAS, Constantes.PROV_ACTIVA);

		for (Provision provisionAux : provisiones) {

			provisionAux.setProEstado(Constantes.PROV_PAGADA);
			provisionService.saveProvision(provisionAux);
		}
		provisiones = null;

		provisiones = provisionService
				.findProvisiones(cedulaEmpleado,
						Constantes.CONCEPTO_INTERESES_CESANTIAS,
						Constantes.PROV_ACTIVA);

		for (Provision provisionAux : provisiones) {

			provisionAux.setProEstado(Constantes.PROV_PAGADA);
			provisionService.saveProvision(provisionAux);
		}
		provisiones = null;

		provisiones = provisionService.findProvisiones(cedulaEmpleado,
				Constantes.CONCEPTO_PRIMA, Constantes.PROV_ACTIVA);

		for (Provision provisionAux : provisiones) {

			provisionAux.setProEstado(Constantes.PROV_PAGADA);
			provisionService.saveProvision(provisionAux);
		}

	}

}
