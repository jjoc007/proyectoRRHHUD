package co.edu.udistrital.rrhh.service;
import co.edu.udistrital.rrhh.domain.Entidad;
import java.util.List;
import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { co.edu.udistrital.rrhh.domain.Entidad.class })
public interface EntidadService {

	public abstract long countAllEntidads();


	public abstract void deleteEntidad(Entidad entidad);


	public abstract Entidad findEntidad(Integer id);


	public abstract List<Entidad> findAllEntidads();


	public abstract List<Entidad> findEntidadEntries(int firstResult, int maxResults);


	public abstract void saveEntidad(Entidad entidad);


	public abstract Entidad updateEntidad(Entidad entidad);
	
	public abstract List<Entidad> findAllEntidadesByTipo(String tipo);

}
