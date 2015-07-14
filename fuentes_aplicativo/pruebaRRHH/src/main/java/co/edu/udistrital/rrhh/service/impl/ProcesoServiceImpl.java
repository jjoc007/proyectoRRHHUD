package co.edu.udistrital.rrhh.service.impl;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udistrital.rrhh.domain.Proceso;
import co.edu.udistrital.rrhh.repository.ProcesoRepository;
import co.edu.udistrital.rrhh.service.ProcesoService;
import co.edu.udistrital.rrhh.web.util.Constantes;

@Service
@Transactional
public class ProcesoServiceImpl implements ProcesoService {

	@Autowired
    ProcesoRepository procesoReprository;
	
	public long countAllProcesos() {
        return procesoReprository.count();
    }

	public void deleteProceso(Proceso proceso) {
        procesoReprository.delete(proceso);
    }

	public List<Proceso> findAllProcesos() {
        return procesoReprository.findAll();
    }

	public void saveProceso(Proceso proceso) {
        procesoReprository.save(proceso);
    }

	public Proceso updateProceso(Proceso proceso) {
        return procesoReprository.save(proceso);
    }

	@Override
	public Proceso findProceso(Integer id) {
		 return procesoReprository.findOne(id);
	}
}
