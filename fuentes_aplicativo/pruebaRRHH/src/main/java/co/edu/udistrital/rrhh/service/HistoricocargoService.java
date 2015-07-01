package co.edu.udistrital.rrhh.service;
import co.edu.udistrital.rrhh.domain.Historicocargo;
import java.util.List;
import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { co.edu.udistrital.rrhh.domain.Historicocargo.class })
public interface HistoricocargoService {

	public abstract long countAllHistoricocargoes();


	public abstract void deleteHistoricocargo(Historicocargo historicocargo);


	public abstract Historicocargo findHistoricocargo(Integer id);


	public abstract List<Historicocargo> findAllHistoricocargoes();


	public abstract List<Historicocargo> findHistoricocargoEntries(int firstResult, int maxResults);


	public abstract void saveHistoricocargo(Historicocargo historicocargo);


	public abstract Historicocargo updateHistoricocargo(Historicocargo historicocargo);

}
