package co.edu.udistrital.rrhh.service.impl;
import co.edu.udistrital.rrhh.domain.Concepto;
import co.edu.udistrital.rrhh.repository.ConceptoRepository;
import co.edu.udistrital.rrhh.service.ConceptoService;
import co.edu.udistrital.rrhh.web.util.Constantes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ConceptoServiceImpl implements ConceptoService {

	@Autowired
    ConceptoRepository conceptoReprository;

	public long countAllConceptoes() {
        return conceptoReprository.count();
    }

	public void deleteConcepto(Concepto concepto) {
        conceptoReprository.delete(concepto);
    }
	
	// Cambio de Estado de Cargp
	public void  ActEstadoConcepto(Concepto concepto) {
		concepto.setConEstado(Constantes.GENERAL_ESTADO_INACTIVO);
		saveConcepto(concepto);
    }
	
	// Buscar cargos Activos
	public List<Concepto> findAllConceptoAct(String estado) {    
        return conceptoReprository.findAllConceptoAct(Constantes.GENERAL_ESTADO_ACTIVO);
    }

	public Concepto findConcepto(Integer id) {
        return conceptoReprository.findOne(id);
    }

	public List<Concepto> findAllConceptoes() {
        return conceptoReprository.findAll();
    }

	public List<Concepto> findConceptoEntries(int firstResult, int maxResults) {
        return conceptoReprository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveConcepto(Concepto concepto) {
        conceptoReprository.save(concepto);
    }

	public Concepto updateConcepto(Concepto concepto) {
        return conceptoReprository.save(concepto);
    }

	public List<Concepto> findAllConceptoLiq(String estado, List<Integer> conceptos, List<String> tipoPer){
		return conceptoReprository.findAllConceptoLiq(estado, conceptos, tipoPer);
	}
}
