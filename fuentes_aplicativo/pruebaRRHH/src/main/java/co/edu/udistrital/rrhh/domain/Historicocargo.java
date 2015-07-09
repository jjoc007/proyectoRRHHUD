package co.edu.udistrital.rrhh.domain;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "historicocargo")
@RooJavaBean
@RooToString
@RooJpaEntity(versionField = "", sequenceName = "", table = "historicocargo")
@RooDbManaged(automaticallyDelete = true)
public class Historicocargo implements Serializable{

	@Id
    @Column(name = "his_codigo")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer hisCodigo;
	
	@ManyToOne
	@JoinColumn(name = "his_empleado", referencedColumnName = "emp_cedula", nullable = false)
	private Empleado hisEmpleado;

	public Integer getHisCodigo() {
        return this.hisCodigo;
    }

	public void setHisCodigo(Integer id) {
        this.hisCodigo = id;
    }


	@ManyToOne
	@JoinColumn(name = "his_cargo", referencedColumnName = "car_cogigo", nullable = false)
    @NotNull
    private Cargo hisCargo;

	@Column(name = "his_fechaInicio")
    private Date hisFechaInicio;

	@Column(name = "his_fechaFin")
    private Date hisFechaFin;

	@Column(name = "his_estado", length = 1)
    @NotNull
    private String hisEstado;

	

	public Empleado getHisEmpleado() {
		return hisEmpleado;
	}

	public void setHisEmpleado(Empleado hisEmpleado) {
		this.hisEmpleado = hisEmpleado;
	}

	public Cargo getHisCargo() {
		return hisCargo;
	}

	public void setHisCargo(Cargo hisCargo) {
		this.hisCargo = hisCargo;
	}

	public Date getHisFechaInicio() {
		return hisFechaInicio;
	}

	public void setHisFechaInicio(Date hisFechaInicio) {
		this.hisFechaInicio = hisFechaInicio;
	}

	public Date getHisFechaFin() {
		return hisFechaFin;
	}

	public void setHisFechaFin(Date hisFechaFin) {
		this.hisFechaFin = hisFechaFin;
	}

	public String getHisEstado() {
        return hisEstado;
    }

	public void setHisEstado(String hisEstado) {
        this.hisEstado = hisEstado;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
	
}
