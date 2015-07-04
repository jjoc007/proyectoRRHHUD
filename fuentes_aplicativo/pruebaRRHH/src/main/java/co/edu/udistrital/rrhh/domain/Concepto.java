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
@Table(name = "concepto")
@RooJavaBean
@RooToString
@RooJpaEntity(versionField = "", sequenceName = "", table = "concepto")
@RooDbManaged(automaticallyDelete = true)
public class Concepto {

	@Id
    @Column(name = "con_codigo")
    private Integer conCodigo;

	public Integer getConCodigo() {
        return this.conCodigo;
    }

	public void setConCodigo(Integer id) {
        this.conCodigo = id;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@Column(name = "con_nombre", length = 50)
    @NotNull
    private String conNombre;

	@Column(name = "con_descripcion")
    private String conDescripcion;

	@Column(name = "con_tipo", length = 20)
    @NotNull
    private String conTipo;
	
	@Column(name = "con_tipoPercepcion", length = 20)
    @NotNull
    private String conTipoPercepcion;
	
	@Column(name = "con_valor", precision = 22)
    @NotNull
    private Double conValor;

	@Column(name = "con_estado", length = 1)
    @NotNull
    private String conEstado;

	public String getConNombre() {
        return conNombre;
    }

	public void setConNombre(String conNombre) {
        this.conNombre = conNombre;
    }

	public String getConDescripcion() {
        return conDescripcion;
    }

	public void setConDescripcion(String conDescripcion) {
        this.conDescripcion = conDescripcion;
    }

	public String getConTipo() {
        return conTipo;
    }

	public void setConTipo(String conTipo) {
        this.conTipo = conTipo;
    }
	
	public String getConTipoPercepcion() {
		return conTipoPercepcion;
	}

	public void setConTipoPercepcion(String conTipoPercepcion) {
		this.conTipoPercepcion = conTipoPercepcion;
	}

	public Double getConValor() {
        return conValor;
    }

	public void setConValor(Double conValor) {
        this.conValor = conValor;
    }

	public String getConEstado() {
        return conEstado;
    }

	public void setConEstado(String conEstado) {
        this.conEstado = conEstado;
    }
}
