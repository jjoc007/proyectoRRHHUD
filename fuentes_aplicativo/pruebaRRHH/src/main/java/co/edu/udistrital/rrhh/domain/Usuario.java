package co.edu.udistrital.rrhh.domain;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@Entity
@Table(name = "usuario")
@RooJavaBean
@RooToString
@RooJpaEntity(versionField = "", sequenceName = "", table = "usuario")
@RooDbManaged(automaticallyDelete = true)
public class Usuario implements Serializable{

	
	@ManyToOne
	@JoinColumn(name = "usu_rol", referencedColumnName = "rol_id", nullable = false)
    private Rol usuRol;

	@Column(name = "usu_nombre", length = 50)
    @NotNull
    private String usuNombre;

	@Column(name = "usu_clave", length = 50)
    @NotNull
    private String usuClave;

	@Column(name = "usu_estado", length = 1)
    @NotNull
    private String usuEstado;

	@Column(name = "usu_correo", length = 80)
    @NotNull
    private String usuCorreo;

	public Rol getUsuRol() {
		return usuRol;
	}

	public void setUsuRol(Rol usuRol) {
		this.usuRol = usuRol;
	}

	public String getUsuNombre() {
        return usuNombre;
    }

	public void setUsuNombre(String usuNombre) {
        this.usuNombre = usuNombre;
    }

	public String getUsuClave() {
        return usuClave;
    }

	public void setUsuClave(String usuClave) {
        this.usuClave = usuClave;
    }

	public String getUsuEstado() {
        return usuEstado;
    }

	public void setUsuEstado(String usuEstado) {
        this.usuEstado = usuEstado;
    }

	public String getUsuCorreo() {
        return usuCorreo;
    }

	public void setUsuCorreo(String usuCorreo) {
        this.usuCorreo = usuCorreo;
    }


	@Id
    @Column(name = "usu_usuario", length = 100)
    private String usuUsuario;

	public String getUsuUsuario() {
        return this.usuUsuario;
    }

	public void setUsuUsuario(String id) {
        this.usuUsuario = id;
    }
	
	public String getNombreRol() {
		
		if(this.usuRol!= null){
			
			return this.usuRol.getRolNombre();
		}
		
        return "";
    }
}
