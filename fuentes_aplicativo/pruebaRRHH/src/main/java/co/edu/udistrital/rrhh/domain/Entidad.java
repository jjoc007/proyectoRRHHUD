package co.edu.udistrital.rrhh.domain;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

@Entity
@Table(name = "entidad")
@Configurable
@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "entidad")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "afiliacions", "aportes" })
public class Entidad {

    @PersistenceContext
    transient EntityManager entityManager;

    public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("");

    public static final EntityManager entityManager() {
        EntityManager em = new Entidad().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

    public static long countEntidads() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Entidad o", Long.class).getSingleResult();
    }

    public static List<Entidad> findAllEntidads() {
        return entityManager().createQuery("SELECT o FROM Entidad o", Entidad.class).getResultList();
    }

    public static List<Entidad> findAllEntidads(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Entidad o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Entidad.class).getResultList();
    }

    public static Entidad findEntidad(Integer entCodigo) {
        if (entCodigo == null) return null;
        return entityManager().find(Entidad.class, entCodigo);
    }

    public static List<Entidad> findEntidadEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Entidad o", Entidad.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static List<Entidad> findEntidadEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Entidad o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Entidad.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Entidad attached = Entidad.findEntidad(this.entCodigo);
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
    public Entidad merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Entidad merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("afiliacions", "aportes").toString();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ent_codigo")
    private Integer entCodigo;

    public Integer getEntCodigo() {
        return this.entCodigo;
    }

    public void setEntCodigo(Integer id) {
        this.entCodigo = id;
    }

    @OneToMany(mappedBy = "afiEntidad")
    private Set<Afiliacion> afiliacions;

    @OneToMany(mappedBy = "apoEntidad")
    private Set<Aporte> aportes;

    @Column(name = "ent_nombre", length = 50)
    @NotNull
    private String entNombre;

    @Column(name = "ent_cuenta")
    @NotNull
    private Integer entCuenta;

    @Column(name = "ent_aporteEmpleado", precision = 22)
    @NotNull
    private Double entAporteEmpleado;

    @Column(name = "ent_aporteEmpresa", precision = 22)
    @NotNull
    private Double entAporteEmpresa;

    public Set<Afiliacion> getAfiliacions() {
        return afiliacions;
    }

    public void setAfiliacions(Set<Afiliacion> afiliacions) {
        this.afiliacions = afiliacions;
    }

    public Set<Aporte> getAportes() {
        return aportes;
    }

    public void setAportes(Set<Aporte> aportes) {
        this.aportes = aportes;
    }

    public String getEntNombre() {
        return entNombre;
    }

    public void setEntNombre(String entNombre) {
        this.entNombre = entNombre;
    }

    public Integer getEntCuenta() {
        return entCuenta;
    }

    public void setEntCuenta(Integer entCuenta) {
        this.entCuenta = entCuenta;
    }

    public Double getEntAporteEmpleado() {
        return entAporteEmpleado;
    }

    public void setEntAporteEmpleado(Double entAporteEmpleado) {
        this.entAporteEmpleado = entAporteEmpleado;
    }

    public Double getEntAporteEmpresa() {
        return entAporteEmpresa;
    }

    public void setEntAporteEmpresa(Double entAporteEmpresa) {
        this.entAporteEmpresa = entAporteEmpresa;
    }
}
