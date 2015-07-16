package co.edu.udistrital.rrhh.service.impl;
import co.edu.udistrital.rrhh.domain.Usuario;
import co.edu.udistrital.rrhh.repository.UsuarioRepository;
import co.edu.udistrital.rrhh.service.LoginService;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginServiceImpl implements LoginService, Serializable {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public Usuario login(String nombre, String pass) {
		Usuario userLogued = usuarioRepository.findUsuarioByUserAndPassword(nombre, pass);
		return userLogued;
	}
	
}
