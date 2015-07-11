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
@Table(name = "entidad")
@RooJavaBean
@RooToString
@RooJpaEntity(versionField = "", sequenceName = "", table = "entidad")
@RooDbManaged(automaticallyDelete = true)
public class Entidad {

	@Id
    @Column(name = "ent_codigo")
    private Integer entCodigo;

	public Integer getEntCodigo() {
        return this.entCodigo;
    }

	public void setEntCodigo(Integer id) {
        this.entCodigo = id;
    }

	@Column(name = "ent_nombre", length = 50)
    @NotNull
    private String entNombre;

	@Column(name = "ent_cuenta")
    @NotNull
    private Integer entCuenta;

	@Column(name = "ent_aporteEmpleado", precision = 22)
    @NotNull
    private Double entAporteEmpleado;

	@Column(name = "ent_aporteEmpresa", precision = 22)
    @NotNull
    private Double entAporteEmpresa;

	@Column(name = "ent_estado", length = 1)
    @NotNull
    private String entEstado;
	
	@Column(name = "ent_tipo", length = 2)
    @NotNull
    private String entTipo;

	public String getEntNombre() {
        return entNombre;
    }

	public void setEntNombre(String entNombre) {
        this.entNombre = entNombre;
    }

	public Integer getEntCuenta() {
        return entCuenta;
    }

	public void setEntCuenta(Integer entCuenta) {
        this.entCuenta = entCuenta;
    }

	public Double getEntAporteEmpleado() {
        return entAporteEmpleado;
    }

	public void setEntAporteEmpleado(Double entAporteEmpleado) {
        this.entAporteEmpleado = entAporteEmpleado;
    }

	public Double getEntAporteEmpresa() {
        return entAporteEmpresa;
    }

	public void setEntAporteEmpresa(Double entAporteEmpresa) {
        this.entAporteEmpresa = entAporteEmpresa;
    }

	public String getEntEstado() {
        return entEstado;
    }

	public void setEntEstado(String entEstado) {
        this.entEstado = entEstado;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public String getEntTipo() {
		return entTipo;
	}

	public void setEntTipo(String entTipo) {
		this.entTipo = entTipo;
	}
	
	
}
