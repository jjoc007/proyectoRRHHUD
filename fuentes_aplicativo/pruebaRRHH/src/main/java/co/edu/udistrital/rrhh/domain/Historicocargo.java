package co.edu.udistrital.rrhh.domain;
import java.util.Calendar;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
@Table(name = "historicocargo")
@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "historicocargo")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "hisEmpleado", "hisCargo" })
public class Historicocargo {

    @ManyToOne
    @JoinColumn(name = "his_empleado", referencedColumnName = "emp_cedula", nullable = false)
    private Empleado hisEmpleado;

    @ManyToOne
    @JoinColumn(name = "his_cargo", referencedColumnName = "car_cogigo", nullable = false)
    private Cargo hisCargo;

    @Column(name = "his_fechaInicio")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar hisFechaInicio;

    @Column(name = "his_fechaFinal")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar hisFechaFinal;

    @Column(name = "his_salario", precision = 22)
    @NotNull
    private Double hisSalario;

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

    public Calendar getHisFechaInicio() {
        return hisFechaInicio;
    }

    public void setHisFechaInicio(Calendar hisFechaInicio) {
        this.hisFechaInicio = hisFechaInicio;
    }

    public Calendar getHisFechaFinal() {
        return hisFechaFinal;
    }

    public void setHisFechaFinal(Calendar hisFechaFinal) {
        this.hisFechaFinal = hisFechaFinal;
    }

    public Double getHisSalario() {
        return hisSalario;
    }

    public void setHisSalario(Double hisSalario) {
        this.hisSalario = hisSalario;
    }

    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("hisEmpleado", "hisCargo").toString();
    }

    @PersistenceContext
    transient EntityManager entityManager;

    public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("");

    public static final EntityManager entityManager() {
        EntityManager em = new Historicocargo().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

    public static long countHistoricocargoes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Historicocargo o", Long.class).getSingleResult();
    }

    public static List<Historicocargo> findAllHistoricocargoes() {
        return entityManager().createQuery("SELECT o FROM Historicocargo o", Historicocargo.class).getResultList();
    }

    public static List<Historicocargo> findAllHistoricocargoes(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Historicocargo o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Historicocargo.class).getResultList();
    }

    public static Historicocargo findHistoricocargo(Integer hisCodigo) {
        if (hisCodigo == null) return null;
        return entityManager().find(Historicocargo.class, hisCodigo);
    }

    public static List<Historicocargo> findHistoricocargoEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Historicocargo o", Historicocargo.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static List<Historicocargo> findHistoricocargoEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Historicocargo o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Historicocargo.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Historicocargo attached = Historicocargo.findHistoricocargo(this.hisCodigo);
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
    public Historicocargo merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Historicocargo merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "his_codigo")
    private Integer hisCodigo;

    public Integer getHisCodigo() {
        return this.hisCodigo;
    }

    public void setHisCodigo(Integer id) {
        this.hisCodigo = id;
    }
}
