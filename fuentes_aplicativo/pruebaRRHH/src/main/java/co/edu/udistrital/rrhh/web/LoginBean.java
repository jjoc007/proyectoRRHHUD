package co.edu.udistrital.rrhh.web;
import java.io.Serializable;
import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
public class LoginBean extends BaseBean implements Serializable {

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
	
	public String login(){
		
		this.user = loginService.login(usuario, password);
		if(this.user != null){
			redirect("/pages/main.jsf");
		}else{
			redirect("/public/index.jsf");
		}
		return null;
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

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
	//cerrar session
	public void cerrarSession(){
		this.user = null;
		redirect("/public/index.jsf");
	}
	
	
}
