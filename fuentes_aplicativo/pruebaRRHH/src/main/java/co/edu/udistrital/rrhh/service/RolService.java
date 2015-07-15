package co.edu.udistrital.rrhh.service;
import co.edu.udistrital.rrhh.domain.Rol;
import java.util.List;
import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { co.edu.udistrital.rrhh.domain.Rol.class })
public interface RolService {

	public abstract long countAllRols();


	public abstract void deleteRol(Rol rol);


	public abstract Rol findRol(Integer id);


	public abstract List<Rol> findAllRols();


	public abstract List<Rol> findRolEntries(int firstResult, int maxResults);


	public abstract void saveRol(Rol rol);

	public abstract Rol updateRol(Rol rol);
	
	public abstract List<Rol> findAllRolesActivos();

}
