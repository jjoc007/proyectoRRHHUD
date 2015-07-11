package co.edu.udistrital.rrhh.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udistrital.rrhh.domain.Aporte;
import co.edu.udistrital.rrhh.domain.Concepto;
import co.edu.udistrital.rrhh.repository.AporteRepository;
import co.edu.udistrital.rrhh.repository.ConceptoRepository;
import co.edu.udistrital.rrhh.service.AporteService;
import co.edu.udistrital.rrhh.web.util.Constantes;

@Service
@Transactional
public class AporteServiceImpl implements AporteService {

	@Autowired
    AporteRepository aporteReprository;
	
	@Autowired
	ConceptoRepository conceptoRepository;
	
	@Override
	public Double calcularAporteSalud(Double sueldoEmpleado){
		
		Concepto aporteSalud = conceptoRepository.findOne(Constantes.CONCEPTO_SALUD);
		
		Double valorAporteSalud = new Double(0);
		
		if(aporteSalud.getConTipo().equalsIgnoreCase(Constantes.TIPO_CONCEPTO_PORCENTAJE)){
			valorAporteSalud = sueldoEmpleado * (aporteSalud.getConValor()/100);
		}else if(aporteSalud.getConTipo().equalsIgnoreCase(Constantes.TIPO_CONCEPTO_VALOR)){
			valorAporteSalud = aporteSalud.getConValor();
		}
		return valorAporteSalud;
	}
	
	@Override
	public Double calcularAportePension(Double sueldoEmpleado){
		
		Concepto aportePension = conceptoRepository.findOne(Constantes.CONCEPTO_PENSION);
		
		Double valorAportePension = new Double(0);
		
		if(aportePension.getConTipo().equalsIgnoreCase(Constantes.TIPO_CONCEPTO_PORCENTAJE)){
			valorAportePension = sueldoEmpleado * (aportePension.getConValor()/100);
		}else if(aportePension.getConTipo().equalsIgnoreCase(Constantes.TIPO_CONCEPTO_VALOR)){
			valorAportePension = aportePension.getConValor();
		}
		return valorAportePension;
	}
	
	@Override
	public Double calcularAporteARP(Double sueldoEmpleado){
		
		Concepto aporteARP = conceptoRepository.findOne(Constantes.CONCEPTO_ARP);
		
		Double valorAporteARP = new Double(0);
		
		if(aporteARP.getConTipo().equalsIgnoreCase(Constantes.TIPO_CONCEPTO_PORCENTAJE)){
			valorAporteARP = sueldoEmpleado * (aporteARP.getConValor()/100);
		}else if(aporteARP.getConTipo().equalsIgnoreCase(Constantes.TIPO_CONCEPTO_VALOR)){
			valorAporteARP = aporteARP.getConValor();
		}
		return valorAporteARP;
	}
	
	public long countAllAportes() {
        return aporteReprository.count();
    }

	public void deleteAporte(Aporte aporte) {
        aporteReprository.delete(aporte);
    }

	public Aporte findAporte(Integer id) {
        return aporteReprository.findOne(id);
    }

	public List<Aporte> findAllAportes() {
        return aporteReprository.findAll();
    }

	public List<Aporte> findAporteEntries(int firstResult, int maxResults) {
        return aporteReprository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveAporte(Aporte aporte) {
        aporteReprository.save(aporte);
    }

	public Aporte updateAporte(Aporte aporte) {
        return aporteReprository.save(aporte);
    }
}
