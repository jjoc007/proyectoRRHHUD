package co.edu.udistrital.rrhh.service;
import co.edu.udistrital.rrhh.domain.Aporte;
import co.edu.udistrital.rrhh.domain.Usuario;

import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { co.edu.udistrital.rrhh.domain.Aporte.class })
public interface LoginService {

	public boolean login(String nombre, String pass);
	public Usuario logout(String nombre);
	
}
