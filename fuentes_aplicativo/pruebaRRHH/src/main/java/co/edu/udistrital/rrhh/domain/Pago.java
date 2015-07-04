package co.edu.udistrital.rrhh.domain;
import java.util.Calendar;
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
@Table(name = "pago")
@RooJavaBean
@RooToString
@RooJpaEntity(versionField = "", sequenceName = "", table = "pago")
@RooDbManaged(automaticallyDelete = true)
public class Pago {

	@Column(name = "pag_empleado")
    @NotNull
    private Integer pagEmpleado;

	@Column(name = "pag_concepto")
    @NotNull
    private Integer pagConcepto;

	@Column(name = "pag_periodo")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar pagPeriodo;
	
	@Column(name = "pag_valorPago", precision = 22)
    @NotNull
    private Double pagValorPago;

	@Column(name = "pag_estado", length = 1)
    @NotNull
    private String pagEstado;

	public Integer getPagEmpleado() {
        return pagEmpleado;
    }

	public void setPagEmpleado(Integer pagEmpleado) {
        this.pagEmpleado = pagEmpleado;
    }

	public Integer getPagConcepto() {
        return pagConcepto;
    }

	public void setPagConcepto(Integer pagConcepto) {
        this.pagConcepto = pagConcepto;
    }

	public Calendar getPagPeriodo() {
        return pagPeriodo;
    }

	public void setPagPeriodo(Calendar pagPeriodo) {
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
    private Integer pagCodigo;

	public Integer getPagCodigo() {
        return this.pagCodigo;
    }

	public void setPagCodigo(Integer id) {
        this.pagCodigo = id;
    }

	@Column(name = "con_concepto")
    @NotNull
    private Integer conConcepto;

	public Integer getConConcepto() {
        return conConcepto;
    }

	public void setConConcepto(Integer conConcepto) {
        this.conConcepto = conConcepto;
    }
}
