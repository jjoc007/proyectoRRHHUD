package co.edu.udistrital.rrhh.domain;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

	public void setEmpCedula(Integer id) {
        this.empCedula = id;
    }

	@Column(name = "emp_nombre", length = 50)
    @NotNull
    private String empNombre;

	@Column(name = "emp_fechaIngreso")
    private Date empFechaIngreso;

	@Column(name = "emp_fechaSalida")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Date empFechaSalida;

	@Column(name = "emp_cuentaPago")
    @NotNull
    private Integer empCuentaPago;

	@Column(name = "emp_estado", length = 1)
    @NotNull
    private String empEstado;

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

	public List<Pago> getPagos() {
		return pagos;
	}

	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}
	
}
