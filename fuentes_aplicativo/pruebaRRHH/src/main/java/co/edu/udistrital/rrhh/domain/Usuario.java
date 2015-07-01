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
@Table(name = "usuario")
@RooJavaBean
@RooToString
@RooJpaEntity(versionField = "", sequenceName = "", table = "usuario")
@RooDbManaged(automaticallyDelete = true)
public class Usuario {

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@Id
    @Column(name = "usu_usuario")
    private Integer usuUsuario;

	public Integer getUsuUsuario() {
        return this.usuUsuario;
    }

	public void setUsuUsuario(Integer id) {
        this.usuUsuario = id;
    }

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
}
