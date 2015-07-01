package co.edu.udistrital.rrhh.service;
import co.edu.udistrital.rrhh.domain.Concepto;
import java.util.List;
import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { co.edu.udistrital.rrhh.domain.Concepto.class })
public interface ConceptoService {

	public abstract long countAllConceptoes();


	public abstract void deleteConcepto(Concepto concepto);


	public abstract Concepto findConcepto(Integer id);


	public abstract List<Concepto> findAllConceptoes();


	public abstract List<Concepto> findConceptoEntries(int firstResult, int maxResults);


	public abstract void saveConcepto(Concepto concepto);


	public abstract Concepto updateConcepto(Concepto concepto);

}
