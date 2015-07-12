package co.edu.udistrital.rrhh.service;
import co.edu.udistrital.rrhh.domain.Cargo;
import co.edu.udistrital.rrhh.domain.Empleado;

import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { co.edu.udistrital.rrhh.domain.Cargo.class })
public interface CargoService {

	public abstract long countAllCargoes();


	public abstract void deleteCargo(Cargo cargo);

	public abstract void  ActEstadoCargo(Cargo cargo); // Cambio de Estado de Cargos
	
	public List<Cargo> findAllCargoAct (String estado);// Buscar cargos Activos

	public abstract Cargo findCargo(Integer id);


	public abstract List<Cargo> findAllCargoes();


	public abstract List<Cargo> findCargoEntries(int firstResult, int maxResults);


	public abstract void saveCargo(Cargo cargo);


	public abstract Cargo updateCargo(Cargo cargo);

}
