package co.edu.udistrital.rrhh.service;
import co.edu.udistrital.rrhh.domain.Proceso;

import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { co.edu.udistrital.rrhh.domain.Proceso.class })
public interface ProcesoService {

	public abstract long countAllProcesos();

	public abstract void deleteProceso(Proceso proceso);

	public abstract Proceso findProceso(Integer id);

	public abstract List<Proceso> findAllProcesos();

	public abstract void saveProceso(Proceso proceso);

	public abstract Proceso updateProceso(Proceso proceso);

}
