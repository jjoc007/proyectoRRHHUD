package co.edu.udistrital.rrhh.service;
import co.edu.udistrital.rrhh.domain.Concepto;

import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { co.edu.udistrital.rrhh.domain.Concepto.class })
public interface ConceptoService {

	public abstract long countAllConceptoes();


	public abstract void deleteConcepto(Concepto concepto);


	public abstract Concepto findConcepto(Integer id);
	
	public abstract void  ActEstadoConcepto(Concepto concepto); // Cambio de Estado de Concepto
	
	public List<Concepto> findAllConceptoAct (String estado);// Buscar Concepto Activos


	public abstract List<Concepto> findAllConceptoes();


	public abstract List<Concepto> findConceptoEntries(int firstResult, int maxResults);


	public abstract void saveConcepto(Concepto concepto);


	public abstract Concepto updateConcepto(Concepto concepto);
	
	
	public abstract List<Concepto> findByTipoPer(String paramConTipoPer);
	

}
