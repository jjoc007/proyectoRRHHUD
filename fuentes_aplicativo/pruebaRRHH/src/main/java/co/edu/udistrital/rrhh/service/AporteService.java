package co.edu.udistrital.rrhh.service;
import co.edu.udistrital.rrhh.domain.Aporte;
import co.edu.udistrital.rrhh.domain.Empleado;
import co.edu.udistrital.rrhh.web.util.AporteQueryOneTO;

import java.util.Date;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { co.edu.udistrital.rrhh.domain.Aporte.class })
public interface AporteService {

	public abstract long countAllAportes();


	public abstract void deleteAporte(Aporte aporte);


	public abstract Aporte findAporte(Integer id);


	public abstract List<Aporte> findAllAportes();


	public abstract List<Aporte> findAporteEntries(int firstResult, int maxResults);


	public abstract void saveAporte(Aporte aporte);


	public abstract Aporte updateAporte(Aporte aporte);
	
	//Lista de aportes-Valor
	public abstract List<AporteQueryOneTO> findValorAporte();


	Double calcularAporteSalud(Double sueldoEmpleado);


	Double calcularAportePension(Double sueldoEmpleado);


	Double calcularAporteARP(Double sueldoEmpleado);
	
	public abstract void realizarAporte(Integer entidad, String tipo, Date periodo, Double valor, Double valorEmpresa);

}
