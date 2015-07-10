package co.edu.udistrital.rrhh.service;
import co.edu.udistrital.rrhh.domain.Usuario;


import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { co.edu.udistrital.rrhh.domain.Aporte.class })
public interface LoginService {

	public boolean login(String nombre, String pass);
	public Usuario logout(String nombre);
	
}
