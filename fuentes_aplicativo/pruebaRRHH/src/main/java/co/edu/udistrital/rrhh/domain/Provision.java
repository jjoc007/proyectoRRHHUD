package co.edu.udistrital.rrhh.domain;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "provision")
@RooJavaBean
@RooToString
@RooJpaEntity(versionField = "", sequenceName = "", table = "provision")
@RooDbManaged(automaticallyDelete = true)
public class Provision {

	@Column(name = "pro_empleado")
    @NotNull
    private Integer proEmpleado;

	@Column(name = "pro_concepto")
    @NotNull
    private Integer proConcepto;

	@Column(name = "pro_periodo")
    @NotNull
    private Date proPeriodo;

	@Column(name = "pro_valor", precision = 22)
    @NotNull
    private Double proValor;

	@Column(name = "pro_estado", length = 1)
    @NotNull
    private String proEstado;

	public Integer getProEmpleado() {
        return proEmpleado;
    }

	public void setProEmpleado(Integer proEmpleado) {
        this.proEmpleado = proEmpleado;
    }

	public Integer getProConcepto() {
        return proConcepto;
    }

	public void setProConcepto(Integer proConcepto) {
        this.proConcepto = proConcepto;
    }

	public Date getProPeriodo() {
		return proPeriodo;
	}

	public void setProPeriodo(Date proPeriodo) {
		this.proPeriodo = proPeriodo;
	}

	public Double getProValor() {
        return proValor;
    }

	public void setProValor(Double proValor) {
        this.proValor = proValor;
    }

	public String getProEstado() {
        return proEstado;
    }

	public void setProEstado(String proEstado) {
        this.proEstado = proEstado;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@Id
    @Column(name = "pro_codigo")
    private Integer proCodigo;

	public Integer getProCodigo() {
        return this.proCodigo;
    }

	public void setProCodigo(Integer id) {
        this.proCodigo = id;
    }
}
