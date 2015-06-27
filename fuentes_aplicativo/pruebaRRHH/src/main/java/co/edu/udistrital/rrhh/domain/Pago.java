package co.edu.udistrital.rrhh.domain;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@RooToString(excludeFields = { "conceptoes", "pagEmpleado" })
public class Pago {

    @OneToMany(mappedBy = "conPago")
    private Set<Concepto> conceptoes;

    @ManyToOne
    @JoinColumn(name = "pag_empleado", referencedColumnName = "emp_cedula", nullable = false)
    private Empleado pagEmpleado;

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

    public Set<Concepto> getConceptoes() {
        return conceptoes;
    }

    public void setConceptoes(Set<Concepto> conceptoes) {
        this.conceptoes = conceptoes;
    }

    public Empleado getPagEmpleado() {
        return pagEmpleado;
    }

    public void setPagEmpleado(Empleado pagEmpleado) {
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

    @PersistenceContext
    transient EntityManager entityManager;

    public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("");

    public static final EntityManager entityManager() {
        EntityManager em = new Pago().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

    public static long countPagoes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Pago o", Long.class).getSingleResult();
    }

    public static List<Pago> findAllPagoes() {
        return entityManager().createQuery("SELECT o FROM Pago o", Pago.class).getResultList();
    }

    public static List<Pago> findAllPagoes(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Pago o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Pago.class).getResultList();
    }

    public static Pago findPago(Integer pagCodigo) {
        if (pagCodigo == null) return null;
        return entityManager().find(Pago.class, pagCodigo);
    }

    public static List<Pago> findPagoEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Pago o", Pago.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static List<Pago> findPagoEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Pago o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Pago.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Transactional
    public void persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }

    @Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Pago attached = Pago.findPago(this.pagCodigo);
            this.entityManager.remove(attached);
        }
    }

    @Transactional
    public void flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }

    @Transactional
    public void clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }

    @Transactional
    public Pago merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Pago merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("conceptoes", "pagEmpleado").toString();
    }

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
}
