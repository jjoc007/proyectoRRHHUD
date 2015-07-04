package co.edu.udistrital.rrhh.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
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

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

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
    private Integer rolId;

	public Integer getRolId() {
        return this.rolId;
    }

	public void setRolId(Integer id) {
        this.rolId = id;
    }
}