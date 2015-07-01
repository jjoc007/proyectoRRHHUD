package co.edu.udistrital.rrhh.service;
import co.edu.udistrital.rrhh.domain.Afiliacion;
import co.edu.udistrital.rrhh.domain.AfiliacionPK;
import java.util.List;
import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { co.edu.udistrital.rrhh.domain.Afiliacion.class })
public interface AfiliacionService {

	public abstract long countAllAfiliacions();


	public abstract void deleteAfiliacion(Afiliacion afiliacion);


	public abstract Afiliacion findAfiliacion(AfiliacionPK id);


	public abstract List<Afiliacion> findAllAfiliacions();


	public abstract List<Afiliacion> findAfiliacionEntries(int firstResult, int maxResults);


	public abstract void saveAfiliacion(Afiliacion afiliacion);


	public abstract Afiliacion updateAfiliacion(Afiliacion afiliacion);

}
