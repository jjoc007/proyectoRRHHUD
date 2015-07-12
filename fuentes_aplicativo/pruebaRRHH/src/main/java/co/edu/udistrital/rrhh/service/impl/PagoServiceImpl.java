package co.edu.udistrital.rrhh.service.impl;
import co.edu.udistrital.rrhh.domain.Pago;
import co.edu.udistrital.rrhh.repository.PagoRepository;
import co.edu.udistrital.rrhh.service.PagoService;
import co.edu.udistrital.rrhh.web.util.Constantes;

import java.util.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PagoServiceImpl implements PagoService {

	@Autowired
    PagoRepository pagoReprository;
	
	
	public List<Pago> findPagos(Integer cedulaEmpleado, Date periodo,
			 String tipoPer){
		
		return pagoReprository.findPagos(cedulaEmpleado, periodo, tipoPer);
	}
	
	@Override
	public Calendar traerPeriodoActualPago(){
		
		Calendar periodoActual = Calendar.getInstance();
		Date periodoEncontrado = pagoReprository.findPeriodoPago();
		if(periodoEncontrado != null){
			periodoActual.setTime(periodoEncontrado);
		}else {
			periodoActual.set(Calendar.DAY_OF_MONTH, Constantes.PERIODO_DEFECTO[0]);
			periodoActual.set(Calendar.MONTH, Constantes.PERIODO_DEFECTO[1]);
			periodoActual.set(Calendar.YEAR, Constantes.PERIODO_DEFECTO[2]);
		}
		
		return periodoActual;
	}
	
	

	public long countAllPagoes() {
        return pagoReprository.count();
    }

	public void deletePago(Pago pago) {
        pagoReprository.delete(pago);
    }

	public Pago findPago(Integer id) {
        return pagoReprository.findOne(id);
    }

	public List<Pago> findAllPagoes() {
        return pagoReprository.findAll();
    }

	public List<Pago> findPagoEntries(int firstResult, int maxResults) {
        return pagoReprository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void savePago(Pago pago) {
        pagoReprository.save(pago);
    }

	public Pago updatePago(Pago pago) {
        return pagoReprository.save(pago);
    }
}
