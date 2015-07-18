package co.edu.udistrital.rrhh.domain;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@Entity
@Table(name = "rol")
@RooJavaBean
@RooToString
@RooJpaEntity(versionField = "", sequenceName = "", table = "rol")
@RooDbManaged(automaticallyDelete = true)
public class Rol {

	@Column(name = "rol_nombre", length = 50)
    @NotNull
    private String rolNombre;

	@Column(name = "rol_descripcion")
    private String rolDescripcion;

	@Column(name = "rol_estado", length = 1)
    @NotNull
    private String rolEstado;

	public String getRolNombre() {
        return rolNombre;
    }

	public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }

	public String getRolDescripcion() {
        return rolDescripcion;
    }

	public void setRolDescripcion(String rolDescripcion) {
        this.rolDescripcion = rolDescripcion;
    }

	public String getRolEstado() {
        return rolEstado;
    }

	public void setRolEstado(String rolEstado) {
        this.rolEstado = rolEstado;
    }

	@Id
    @Column(name = "rol_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer rolId;

	public Integer getRolId() {
        return this.rolId;
    }
	
	
	@OneToMany(mappedBy = "usuRol", fetch= FetchType.EAGER)
    private List<Usuario> usuarios;

	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public void setRolId(Integer id) {
        this.rolId = id;
    }
}
