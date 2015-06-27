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

@Entity
@Table(name = "concepto")
@Configurable
@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "concepto")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "conPago" })
public class Concepto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "con_codigo")
    private Integer conCodigo;

    public Integer getConCodigo() {
        return this.conCodigo;
    }

    public void setConCodigo(Integer id) {
        this.conCodigo = id;
    }

    @PersistenceContext
    transient EntityManager entityManager;

    public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("");

    public static final EntityManager entityManager() {
        EntityManager em = new Concepto().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

    public static long countConceptoes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Concepto o", Long.class).getSingleResult();
    }

    public static List<Concepto> findAllConceptoes() {
        return entityManager().createQuery("SELECT o FROM Concepto o", Concepto.class).getResultList();
    }

    public static List<Concepto> findAllConceptoes(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Concepto o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Concepto.class).getResultList();
    }

    public static Concepto findConcepto(Integer conCodigo) {
        if (conCodigo == null) return null;
        return entityManager().find(Concepto.class, conCodigo);
    }

    public static List<Concepto> findConceptoEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Concepto o", Concepto.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static List<Concepto> findConceptoEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Concepto o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Concepto.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Concepto attached = Concepto.findConcepto(this.conCodigo);
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
    public Concepto merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Concepto merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("conPago").toString();
    }

    @ManyToOne
    @JoinColumn(name = "con_pago", referencedColumnName = "pag_codigo", nullable = false)
    private Pago conPago;

    @Column(name = "con_nombre", length = 50)
    @NotNull
    private String conNombre;

    @Column(name = "con_descripcion")
    private String conDescripcion;

    @Column(name = "con_tipo", length = 20)
    @NotNull
    private String conTipo;

    @Column(name = "con_valor", precision = 22)
    @NotNull
    private Double conValor;

    public Pago getConPago() {
        return conPago;
    }

    public void setConPago(Pago conPago) {
        this.conPago = conPago;
    }

    public String getConNombre() {
        return conNombre;
    }

    public void setConNombre(String conNombre) {
        this.conNombre = conNombre;
    }

    public String getConDescripcion() {
        return conDescripcion;
    }

    public void setConDescripcion(String conDescripcion) {
        this.conDescripcion = conDescripcion;
    }

    public String getConTipo() {
        return conTipo;
    }

    public void setConTipo(String conTipo) {
        this.conTipo = conTipo;
    }

    public Double getConValor() {
        return conValor;
    }

    public void setConValor(Double conValor) {
        this.conValor = conValor;
    }
}
