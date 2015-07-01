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
@Table(name = "pago")
@RooJavaBean
@RooToString
@RooJpaEntity(versionField = "", sequenceName = "", table = "pago")
@RooDbManaged(automaticallyDelete = true)
public class Pago {

	@Column(name = "pag_empleado")
    @NotNull
    private Integer pagEmpleado;

	@Column(name = "pag_tipo", length = 20)
    @NotNull
    private String pagTipo;

	@Column(name = "pag_valorPago", precision = 22)
    @NotNull
    private Double pagValorPago;

	@Column(name = "pag_descripcion")
    private String pagDescripcion;

	@Column(name = "pag_estado")
    @NotNull
    private boolean pagEstado;

	@Column(name = "con_concepto")
    @NotNull
    private Integer conConcepto;

	public Integer getPagEmpleado() {
        return pagEmpleado;
    }

	public void setPagEmpleado(Integer pagEmpleado) {
        this.pagEmpleado = pagEmpleado;
    }

	public String getPagTipo() {
        return pagTipo;
    }

	public void setPagTipo(String pagTipo) {
        this.pagTipo = pagTipo;
    }

	public Double getPagValorPago() {
        return pagValorPago;
    }

	public void setPagValorPago(Double pagValorPago) {
        this.pagValorPago = pagValorPago;
    }

	public String getPagDescripcion() {
        return pagDescripcion;
    }

	public void setPagDescripcion(String pagDescripcion) {
        this.pagDescripcion = pagDescripcion;
    }

	public boolean isPagEstado() {
        return pagEstado;
    }

	public void setPagEstado(boolean pagEstado) {
        this.pagEstado = pagEstado;
    }

	public Integer getConConcepto() {
        return conConcepto;
    }

	public void setConConcepto(Integer conConcepto) {
        this.conConcepto = conConcepto;
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

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
