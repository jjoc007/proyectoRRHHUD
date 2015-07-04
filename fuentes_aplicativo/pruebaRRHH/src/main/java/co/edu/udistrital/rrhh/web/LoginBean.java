package co.edu.udistrital.rrhh.web;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.serializable.RooSerializable;

import co.edu.udistrital.rrhh.domain.Usuario;
import co.edu.udistrital.rrhh.service.LoginService;

@Configurable
@ManagedBean(name = "loginBean")
@SessionScoped
@RooSerializable
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario user;

	@Autowired
    LoginService loginService;

	private String usuario= "";
	
	private String password= "";
	
	public void reset() {
        usuario = "";
        password = "";
    }
	
	public void login(){
		loginService.login(usuario, password);
	}
	
	public void handleDialogClose(CloseEvent event) {
        reset();
    }

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
