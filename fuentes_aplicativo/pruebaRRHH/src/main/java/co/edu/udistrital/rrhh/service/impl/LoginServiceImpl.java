package co.edu.udistrital.rrhh.service.impl;
import co.edu.udistrital.rrhh.domain.Aporte;
import co.edu.udistrital.rrhh.domain.Usuario;
import co.edu.udistrital.rrhh.repository.UsuarioRepository;
import co.edu.udistrital.rrhh.service.AporteService;
import co.edu.udistrital.rrhh.service.LoginService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public boolean login(String nombre, String pass) {
		List<Object> listaUsers = usuarioRepository.findUsuarioByUserAndPassword(nombre, pass);
		return false;
	}

	@Override
	public Usuario logout(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
