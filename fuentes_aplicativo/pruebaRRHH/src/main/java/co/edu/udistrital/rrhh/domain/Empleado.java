package co.edu.udistrital.rrhh.domain;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@Entity
@Table(name = "empleado")
@RooJavaBean
@RooToString
@RooJpaEntity(versionField = "", sequenceName = "", table = "empleado")
@RooDbManaged(automaticallyDelete = true)
public class Empleado {

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@Id
    @Column(name = "emp_cedula")
    private Integer empCedula;

	public Integer getEmpCedula() {
        return this.empCedula;
    }
	
	@ManyToOne
	@JoinColumn(name = "cargo", referencedColumnName = "car_cogigo", nullable = false)
    @NotNull
    private Cargo cargo;

	public void setEmpCedula(Integer id) {
        this.empCedula = id;
    }

	@Column(name = "emp_nombre", length = 50)
    @NotNull
    private String empNombre;

	@Column(name = "emp_fechaIngreso")
    private Date empFechaIngreso;

	@Column(name = "emp_fechaSalida")
    private Date empFechaSalida;

	@Column(name = "emp_cuentaPago")
    @NotNull
    private Integer empCuentaPago;

	@Column(name = "emp_estado", length = 1)
    @NotNull
    private String empEstado;
	
	@Transient
	private boolean emp_vacaciones;

	@Transient
	private boolean emp_liquida;

	public String getEmpNombre() {
        return empNombre;
    }

	public void setEmpNombre(String empNombre) {
        this.empNombre = empNombre;
    }

	public Date getEmpFechaIngreso() {
        return empFechaIngreso;
    }

	public void setEmpFechaIngreso(Date empFechaIngreso) {
        this.empFechaIngreso = empFechaIngreso;
    }

	public Date getEmpFechaSalida() {
        return empFechaSalida;
    }

	public void setEmpFechaSalida(Date empFechaSalida) {
        this.empFechaSalida = empFechaSalida;
    }

	public Integer getEmpCuentaPago() {
        return empCuentaPago;
    }

	public void setEmpCuentaPago(Integer empCuentaPago) {
        this.empCuentaPago = empCuentaPago;
    }

	public String getEmpEstado() {
        return empEstado;
    }

	public void setEmpEstado(String empEstado) {
        this.empEstado = empEstado;
    }
	
	@OneToMany(mappedBy = "pagoEmpleado")
    private List<Pago> pagos;
	
	@OneToMany(mappedBy = "hisEmpleado", fetch= FetchType.EAGER)
    private List<Historicocargo> historicoCargos;

	public List<Pago> getPagos() {
		return pagos;
	}
	
	

	public List<Historicocargo> getHistoricoCargos() {
		return historicoCargos;
	}

	public void setHistoricoCargos(List<Historicocargo> historicoCargos) {
		this.historicoCargos = historicoCargos;
	}

	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}

	public boolean isEmp_vacaciones() {
		return emp_vacaciones;
	}

	public void setEmp_vacaciones(boolean emp_vacaciones) {
		this.emp_vacaciones = emp_vacaciones;
	}

	public boolean isEmp_liquida() {
		return emp_liquida;
	}

	public void setEmp_liquida(boolean emp_liquida) {
		this.emp_liquida = emp_liquida;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	
}
