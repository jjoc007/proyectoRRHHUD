package co.edu.udistrital.rrhh.service;
import co.edu.udistrital.rrhh.domain.Provision;

import java.util.Date;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { co.edu.udistrital.rrhh.domain.Provision.class })
public interface ProvisionService {

	public abstract long countAllProvisions();


	public abstract void deleteProvision(Provision provision);


	public abstract Provision findProvision(Integer id);


	public abstract List<Provision> findAllProvisions();


	public abstract List<Provision> findProvisionEntries(int firstResult, int maxResults);


	public abstract void saveProvision(Provision provision);


	public abstract Provision updateProvision(Provision provision);

	
	public abstract List<Provision> findProvisiones(Integer cedulaEmpleado, Integer concepto, String estado);
	
	
	public abstract List<Provision> findProvisionesPeriodo(Date periodo);
	
}
