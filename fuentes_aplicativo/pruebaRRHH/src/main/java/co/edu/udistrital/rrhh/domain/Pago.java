package co.edu.udistrital.rrhh.domain;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
@Table(name = "pago")
@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "pago")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "conceptoes", "pagEmpleado", "conConcepto" })
public class Pago {

    @ManyToOne
    @JoinColumn(name = "pag_empleado", referencedColumnName = "emp_cedula", nullable = false)
    private Empleado pagEmpleado;

    @ManyToOne
    @JoinColumn(name = "con_concepto", referencedColumnName = "con_codigo", nullable = false)
    private Concepto conConcepto;

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

    public Empleado getPagEmpleado() {
        return pagEmpleado;
    }

    public void setPagEmpleado(Empleado pagEmpleado) {
        this.pagEmpleado = pagEmpleado;
    }

    public Concepto getConConcepto() {
        return conConcepto;
    }

    public void setConConcepto(Concepto conConcepto) {
        this.conConcepto = conConcepto;
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

    @PersistenceContext
    transient EntityManager entityManager;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pag_codigo")
    private Integer pagCodigo;

    public Integer getPagCodigo() {
        return this.pagCodigo;
    }

    public void setPagCodigo(Integer id) {
        this.pagCodigo = id;
    }

    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("conceptoes", "pagEmpleado", "conConcepto").toString();
    }
}
