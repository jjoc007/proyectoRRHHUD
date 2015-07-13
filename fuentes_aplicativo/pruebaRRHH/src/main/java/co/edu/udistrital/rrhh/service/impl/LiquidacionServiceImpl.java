package co.edu.udistrital.rrhh.service.impl;

import co.edu.udistrital.rrhh.domain.Aporte;
import co.edu.udistrital.rrhh.domain.Concepto;
import co.edu.udistrital.rrhh.domain.Empleado;
import co.edu.udistrital.rrhh.domain.Pago;
import co.edu.udistrital.rrhh.domain.Provision;
import co.edu.udistrital.rrhh.service.AporteService;
import co.edu.udistrital.rrhh.service.ConceptoService;
import co.edu.udistrital.rrhh.service.EmpleadoService;
import co.edu.udistrital.rrhh.service.LiquidacionService;
import co.edu.udistrital.rrhh.service.PagoService;
import co.edu.udistrital.rrhh.service.ProvisionService;
import co.edu.udistrital.rrhh.web.util.Constantes;

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

	public Pago pago;
	public Aporte aporte;
	public Provision provision;

	public void Liquidar(List<Empleado> allEmpleados, Calendar periodo) {

		Integer numProviVacaciones;
		Integer mes;
		List<Provision> provisionesRep;
		System.out.println("aqui");
		provisionesRep = provisionService.findProvisionesPeriodo(periodo.getTime());
		System.out.println("aqui2 provisiones"+provisionesRep.size());
		if (provisionesRep.size() == 0){
			System.out
			.println("No se puede realizar el proceso de liquidación ya que no existe liquidación de prestaciones");
		}
		System.out.println("aqui3");
		provisionesRep = null;
		
		for (Empleado empleadoAux : allEmpleados) {
			System.out.println("aqui4");
			realizarAporteSeguridadSocial(empleadoAux.getEmpCedula(), periodo.getTime());
			System.out.println("aqui5");
					provisionesRep = provisionService.findProvisiones(
					empleadoAux.getEmpCedula(), Constantes.CONCEPTO_VACACIONES,
					Constantes.PROV_ACTIVA);

			if (provisionesRep != null)
				numProviVacaciones = provisionesRep.size();
			else
				numProviVacaciones = 0;

			if (numProviVacaciones > 24) {

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

					System.out
							.println("El empleado: "
									+ empleadoAux.getEmpCedula()
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

			mes = periodo.get(Calendar.MONTH);

			if (mes == Constantes.MES_CESANTIAS) {

				procesarCesantias(empleadoAux, periodo);

			}

			if (mes == Constantes.MES_PRIMA || mes == Constantes.MES_PRIMA2) {

				procesarPrima(empleadoAux, periodo);

			}
			
			if (empleadoAux.isEmp_liquida()) {

				liquidaEmpleado(empleadoAux, periodo.getTime());
				empleadoAux.setEmpFechaSalida(periodo.getTime());
				empleadoAux.setEmpEstado(Constantes.ESTADO_EMPL_INACTIVO);
				empleadoService.saveEmpleado(empleadoAux);

			}
			
		   try {
			generarArchivoPlano(empleadoAux, periodo.getTime());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
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
		Concepto concepto = conceptoService
				.findConcepto(Constantes.CONCEPTO_PRIMA);
		
		realizarPago(concepto, Constantes.PAGO_ACTIVO, empleado,
				periodo.getTime(), prima);

	}

	public void procesarCesantias(Empleado empleado, Calendar periodo) {

		Double cesantias = 0.0;
		Double interesesCesantias = 0.0;
		//Integer entidad;

		List<Provision> provisionesRep = provisionService.findProvisiones(
				empleado.getEmpCedula(), Constantes.CONCEPTO_CESANTIAS,
				Constantes.PROV_ACTIVA);

		for (Provision provisionAux : provisionesRep) {

			cesantias += provisionAux.getProValor();
			provisionAux.setProEstado(Constantes.PROV_PAG_ENTIDAD);
			provisionService.saveProvision(provisionAux);

		}

		// entidad = recuperarAfiliacion(cedulaEmpleado, "ENTIDAD_CESANTIAS");

		realizarAporte(1, Constantes.APORTE_CESANTIAS, periodo.getTime(),
				cesantias);

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

	public void realizarPago(Concepto concepto, String estado,
			Empleado empleado, Date periodo, Double valorPago) {
		
		pago = new Pago();
		
		pago.setPagConcepto(concepto);
		pago.setPagEstado(estado);
		pago.setPagoEmpleado(empleado);
		pago.setPagPeriodo(periodo);
		pago.setPagValorPago(valorPago);
		pagoService.savePago(pago);
	}

	public Integer recuperarAfiliacion(Integer cedulaEmpleado, String tipoEntidad) {

		Integer entidad = 0;
		// entidad = recuperar la entidad
		return entidad;
	}

	public void realizarAporte(Integer entidad, String tipo, Date periodo,
			Double valor) {
		
		aporte = new Aporte();
		aporte.setApoEntidad(entidad);
		aporte.setApoTipo(tipo);
		aporte.setApoPeriodo(periodo);
		aporte.setApoValor(valor);

		aporteService.saveAporte(aporte);
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
		
		realizarPago(concepto, Constantes.PAGO_ACTIVO, empleado,
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
		
		realizarPago(concepto, Constantes.PAGO_ACTIVO, empleado,
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
		
		realizarPago(concepto, Constantes.PAGO_ACTIVO, empleado,
				periodo, interesCesantias);
		
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
		
		realizarPago(concepto, Constantes.PAGO_ACTIVO, empleado,
				periodo, prima);

	}
	
	public void realizarAporteSeguridadSocial(Integer cedulaEmpleado, Date periodo){
		
		Double salud = 0.0;
		Double pension = 0.0;
		
		pago = pagoService.findPago(
				cedulaEmpleado, Constantes.CONCEPTO_SALUD,
				periodo);
		
		salud = pago.getPagValorPago();
		
		realizarAporte(1, Constantes.APORTE_SALUD, periodo, salud);
		
		pago = pagoService.findPago(
				cedulaEmpleado, Constantes.CONCEPTO_PENSION,
				periodo);
		
		pension = pago.getPagValorPago();
		
		realizarAporte(1, Constantes.APORTE_PENSION, periodo, pension);

	}
	
	public void generarArchivoPlano(Empleado empleado, Date periodo) throws IOException{
		
		Double devengos = 0.0;
		Double deducidos = 0.0;
		Double salario = 0.0;
		
		String ruta = Constantes.RUTA_ARCHIVO_PLANO;
		        File archivo = new File(ruta);
		        devengos = calcularTotalDevengados(empleado, periodo);
				deducidos = calcularTotalDeducciones(empleado.getEmpCedula(), periodo);
		        salario = calcularSalarioEmpleado(empleado, periodo);
		        
		        BufferedWriter bw;
		        if(archivo.exists()) {
		            bw = new BufferedWriter(new FileWriter(archivo));
		            bw.write(empleado.getEmpCedula());
		        } else {
		            bw = new BufferedWriter(new FileWriter(archivo));
		            bw.write(empleado.getEmpCedula());
		        }
		        bw.close();
		    }

}
