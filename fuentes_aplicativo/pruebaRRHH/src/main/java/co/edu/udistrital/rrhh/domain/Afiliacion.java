package co.edu.udistrital.rrhh.domain;
import java.util.List;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
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
@Table(name = "afiliacion")
@RooJavaBean
@RooJpaActiveRecord(identifierType = AfiliacionPK.class, versionField = "", table = "afiliacion")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "afiEmpleado", "afiEntidad" })
public class Afiliacion {

    @ManyToOne
    @JoinColumn(name = "afi_empleado", referencedColumnName = "emp_cedula", nullable = false, insertable = false, updatable = false)
    private Empleado afiEmpleado;

    @ManyToOne
    @JoinColumn(name = "afi_entidad", referencedColumnName = "ent_codigo", nullable = false, insertable = false, updatable = false)
    private Entidad afiEntidad;

    public Empleado getAfiEmpleado() {
        return afiEmpleado;
    }

    public void setAfiEmpleado(Empleado afiEmpleado) {
        this.afiEmpleado = afiEmpleado;
    }

    public Entidad getAfiEntidad() {
        return afiEntidad;
    }

    public void setAfiEntidad(Entidad afiEntidad) {
        this.afiEntidad = afiEntidad;
    }

    @PersistenceContext
    transient EntityManager entityManager;

    public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("");

    public static final EntityManager entityManager() {
        EntityManager em = new Afiliacion().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

    public static long countAfiliacions() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Afiliacion o", Long.class).getSingleResult();
    }

    public static List<Afiliacion> findAllAfiliacions() {
        return entityManager().createQuery("SELECT o FROM Afiliacion o", Afiliacion.class).getResultList();
    }

    public static List<Afiliacion> findAllAfiliacions(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Afiliacion o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Afiliacion.class).getResultList();
    }

    public static Afiliacion findAfiliacion(AfiliacionPK id) {
        if (id == null) return null;
        return entityManager().find(Afiliacion.class, id);
    }

    public static List<Afiliacion> findAfiliacionEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Afiliacion o", Afiliacion.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static List<Afiliacion> findAfiliacionEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Afiliacion o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Afiliacion.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Afiliacion attached = Afiliacion.findAfiliacion(this.id);
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
    public Afiliacion merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Afiliacion merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("afiEmpleado", "afiEntidad").toString();
    }

    @EmbeddedId
    private AfiliacionPK id;

    public AfiliacionPK getId() {
        return this.id;
    }

    public void setId(AfiliacionPK id) {
        this.id = id;
    }
}
