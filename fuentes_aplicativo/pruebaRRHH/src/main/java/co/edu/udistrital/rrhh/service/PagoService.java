package co.edu.udistrital.rrhh.service;
import co.edu.udistrital.rrhh.domain.Pago;
import co.edu.udistrital.rrhh.web.util.Constantes;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { co.edu.udistrital.rrhh.domain.Pago.class })
public interface PagoService {

	public abstract long countAllPagoes();


	public abstract void deletePago(Pago pago);


	public abstract Pago findPago(Integer id);


	public abstract List<Pago> findAllPagoes();


	public abstract List<Pago> findPagoEntries(int firstResult, int maxResults);


	public abstract void savePago(Pago pago);


	public abstract Pago updatePago(Pago pago);

	public abstract List<Pago> findPagos(Integer cedulaEmpleado, Date periodo,
			 String tipoPer);

	Calendar traerPeriodoActualPago();

}
