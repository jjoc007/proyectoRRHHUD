package co.edu.udistrital.rrhh.service;
import co.edu.udistrital.rrhh.domain.Empleado;
import java.util.List;
import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { co.edu.udistrital.rrhh.domain.Empleado.class })
public interface EmpleadoService {

	public abstract long countAllEmpleadoes();


	public abstract void deleteEmpleado(Empleado empleado);


	public abstract Empleado findEmpleado(Integer id);


	public abstract List<Empleado> findAllEmpleadoes();


	public abstract List<Empleado> findEmpleadoEntries(int firstResult, int maxResults);


	public abstract void saveEmpleado(Empleado empleado);


	public abstract Empleado updateEmpleado(Empleado empleado);
	
	
	public List<Empleado> findAllEmpleadosAct(String estado);

}
