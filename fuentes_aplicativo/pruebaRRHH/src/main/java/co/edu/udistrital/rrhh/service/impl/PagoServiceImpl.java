package co.edu.udistrital.rrhh.service.impl;
import co.edu.udistrital.rrhh.domain.Concepto;
import co.edu.udistrital.rrhh.domain.Empleado;
import co.edu.udistrital.rrhh.domain.Pago;
import co.edu.udistrital.rrhh.repository.PagoRepository;
import co.edu.udistrital.rrhh.service.PagoService;


import java.util.Date;
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
        return pagoReprository.findAllPagosOrder();
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
	
	public Pago findPago(Integer cedulaEmpleado, Integer concepto, Date	periodo){
		return pagoReprository.findPago(cedulaEmpleado, concepto, periodo);
	}
	
	public void realizarPago(Concepto concepto, String estado, Empleado empleado, Date periodo, Double valorPago) {
				
		Pago pago = new Pago();
		
		pago.setPagConcepto(concepto);
		pago.setPagEstado(estado);
		pago.setPagoEmpleado(empleado);
		pago.setPagPeriodo(periodo);
		pago.setPagValorPago(valorPago);
		pagoReprository.save(pago);
	}
}
