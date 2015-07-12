package co.edu.udistrital.rrhh.domain;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@Entity
@Table(name = "pago")
@RooJavaBean
@RooToString
@RooJpaEntity(versionField = "", sequenceName = "", table = "pago")
@RooDbManaged(automaticallyDelete = true)
public class Pago {

	@ManyToOne
	@JoinColumn(name = "pag_empleado", referencedColumnName = "emp_cedula", nullable = false)
	private Empleado pagoEmpleado;

	@ManyToOne
    @JoinColumn(name = "pag_concepto", referencedColumnName = "con_codigo", nullable = false)
    private Concepto pagConcepto;

	@Column(name = "pag_periodo")
    private Date pagPeriodo;
	
	@Column(name = "pag_valorPago", precision = 22)
    @NotNull
    private Double pagValorPago;

	@Column(name = "pag_estado", length = 1)
    @NotNull
    private String pagEstado;

	public Concepto getPagConcepto() {
		return pagConcepto;
	}

	public void setPagConcepto(Concepto pagConcepto) {
		this.pagConcepto = pagConcepto;
	}

	public Pago() {
		// TODO Auto-generated constructor stub
	}

	public Pago(Empleado pagoEmpleado, Concepto pagConcepto, Date pagPeriodo,
			Double pagValorPago, String pagEstado, Integer pagCodigo) {
		super();
		this.pagoEmpleado = pagoEmpleado;
		this.pagConcepto = pagConcepto;
		this.pagPeriodo = pagPeriodo;
		this.pagValorPago = pagValorPago;
		this.pagEstado = pagEstado;
		this.pagCodigo = pagCodigo;

	}

	public Date getPagPeriodo() {
		return pagPeriodo;
	}

	public void setPagPeriodo(Date pagPeriodo) {
		this.pagPeriodo = pagPeriodo;
	}

	public Double getPagValorPago() {
        return pagValorPago;
    }

	public void setPagValorPago(Double pagValorPago) {
        this.pagValorPago = pagValorPago;
    }

	public String getPagEstado() {
        return pagEstado;
    }

	public void setPagEstado(String pagEstado) {
        this.pagEstado = pagEstado;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@Id
    @Column(name = "pag_codigo")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer pagCodigo;

	public Integer getPagCodigo() {
        return this.pagCodigo;
    }

	public void setPagCodigo(Integer id) {
        this.pagCodigo = id;
    }

	public Empleado getPagoEmpleado() {
		return pagoEmpleado;
	}

	public void setPagoEmpleado(Empleado pagoEmpleado) {
		this.pagoEmpleado = pagoEmpleado;
	}
	

	
}
