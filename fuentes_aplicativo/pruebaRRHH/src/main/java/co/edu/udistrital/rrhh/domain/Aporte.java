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
@Table(name = "aporte")
@Configurable
@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "aporte")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "apoEntidad" })
public class Aporte {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "apo_codigo")
    private Integer apoCodigo;

    public Integer getApoCodigo() {
        return this.apoCodigo;
    }

    public void setApoCodigo(Integer id) {
        this.apoCodigo = id;
    }

    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("apoEntidad").toString();
    }

    @ManyToOne
    @JoinColumn(name = "apo_entidad", referencedColumnName = "ent_codigo", nullable = false)
    private Entidad apoEntidad;

    @Column(name = "apo_tipo", length = 20)
    @NotNull
    private String apoTipo;

    @Column(name = "apo_periodo", length = 20)
    @NotNull
    private String apoPeriodo;

    @Column(name = "apo_valor", precision = 22)
    @NotNull
    private Double apoValor;

    public Entidad getApoEntidad() {
        return apoEntidad;
    }

    public void setApoEntidad(Entidad apoEntidad) {
        this.apoEntidad = apoEntidad;
    }

    public String getApoTipo() {
        return apoTipo;
    }

    public void setApoTipo(String apoTipo) {
        this.apoTipo = apoTipo;
    }

    public String getApoPeriodo() {
        return apoPeriodo;
    }

    public void setApoPeriodo(String apoPeriodo) {
        this.apoPeriodo = apoPeriodo;
    }

    public Double getApoValor() {
        return apoValor;
    }

    public void setApoValor(Double apoValor) {
        this.apoValor = apoValor;
    }

    @PersistenceContext
    transient EntityManager entityManager;

    public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("");

    public static final EntityManager entityManager() {
        EntityManager em = new Aporte().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

    public static long countAportes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Aporte o", Long.class).getSingleResult();
    }

    public static List<Aporte> findAllAportes() {
        return entityManager().createQuery("SELECT o FROM Aporte o", Aporte.class).getResultList();
    }

    public static List<Aporte> findAllAportes(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Aporte o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Aporte.class).getResultList();
    }

    public static Aporte findAporte(Integer apoCodigo) {
        if (apoCodigo == null) return null;
        return entityManager().find(Aporte.class, apoCodigo);
    }

    public static List<Aporte> findAporteEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Aporte o", Aporte.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static List<Aporte> findAporteEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Aporte o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Aporte.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Aporte attached = Aporte.findAporte(this.apoCodigo);
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
    public Aporte merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Aporte merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
}
