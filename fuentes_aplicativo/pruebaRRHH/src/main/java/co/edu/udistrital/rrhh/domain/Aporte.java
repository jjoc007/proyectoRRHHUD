package co.edu.udistrital.rrhh.domain;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@Entity
@Table(name = "aporte")
@RooJavaBean
@RooToString
@RooJpaEntity(versionField = "", sequenceName = "", table = "aporte")
@RooDbManaged(automaticallyDelete = true)
public class Aporte {

	@Column(name = "apo_entidad")
    @NotNull
    private Integer apoEntidad;

	@Column(name = "apo_tipo", length = 20)
    @NotNull
    private String apoTipo;

	@Column(name = "apo_periodo")
    @NotNull
    private Date apoPeriodo;

	@Column(name = "apo_valor", precision = 22)
    @NotNull
    private Double apoValor;

	public Integer getApoEntidad() {
        return apoEntidad;
    }

	public void setApoEntidad(Integer apoEntidad) {
        this.apoEntidad = apoEntidad;
    }

	public String getApoTipo() {
        return apoTipo;
    }

	public void setApoTipo(String apoTipo) {
        this.apoTipo = apoTipo;
    }

	public Date getApoPeriodo() {
        return apoPeriodo;
    }

	public void setApoPeriodo(Date apoPeriodo) {
        this.apoPeriodo = apoPeriodo;
    }

	public Double getApoValor() {
        return apoValor;
    }

	public void setApoValor(Double apoValor) {
        this.apoValor = apoValor;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@Id
    @Column(name = "apo_codigo")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer apoCodigo;

	public Integer getApoCodigo() {
        return this.apoCodigo;
    }

	public void setApoCodigo(Integer id) {
        this.apoCodigo = id;
    }
}
