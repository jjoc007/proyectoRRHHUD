package co.edu.udistrital.rrhh.service.impl;
import co.edu.udistrital.rrhh.domain.Concepto;
import co.edu.udistrital.rrhh.repository.ConceptoReprository;
import co.edu.udistrital.rrhh.service.ConceptoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ConceptoServiceImpl implements ConceptoService {

	@Autowired
    ConceptoReprository conceptoReprository;

	public long countAllConceptoes() {
        return conceptoReprository.count();
    }

	public void deleteConcepto(Concepto concepto) {
        conceptoReprository.delete(concepto);
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
}
