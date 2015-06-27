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

@Configurable
@Entity
@Table(name = "cargo")
@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "cargo")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "historicocargoes" })
public class Cargo {

    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("historicocargoes").toString();
    }

    @PersistenceContext
    transient EntityManager entityManager;

    public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("");

    public static final EntityManager entityManager() {
        EntityManager em = new Cargo().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

    public static long countCargoes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Cargo o", Long.class).getSingleResult();
    }

    public static List<Cargo> findAllCargoes() {
        return entityManager().createQuery("SELECT o FROM Cargo o", Cargo.class).getResultList();
    }

    public static List<Cargo> findAllCargoes(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Cargo o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Cargo.class).getResultList();
    }

    public static Cargo findCargo(Integer carCogigo) {
        if (carCogigo == null) return null;
        return entityManager().find(Cargo.class, carCogigo);
    }

    public static List<Cargo> findCargoEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Cargo o", Cargo.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static List<Cargo> findCargoEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Cargo o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Cargo.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Cargo attached = Cargo.findCargo(this.carCogigo);
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
    public Cargo merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Cargo merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "car_cogigo")
    private Integer carCogigo;

    public Integer getCarCogigo() {
        return this.carCogigo;
    }

    public void setCarCogigo(Integer id) {
        this.carCogigo = id;
    }

    @OneToMany(mappedBy = "hisCargo")
    private Set<Historicocargo> historicocargoes;

    @Column(name = "car_nombre", length = 50)
    @NotNull
    private String carNombre;

    @Column(name = "car_salario", precision = 22)
    @NotNull
    private Double carSalario;

    public Set<Historicocargo> getHistoricocargoes() {
        return historicocargoes;
    }

    public void setHistoricocargoes(Set<Historicocargo> historicocargoes) {
        this.historicocargoes = historicocargoes;
    }

    public String getCarNombre() {
        return carNombre;
    }

    public void setCarNombre(String carNombre) {
        this.carNombre = carNombre;
    }

    public Double getCarSalario() {
        return carSalario;
    }

    public void setCarSalario(Double carSalario) {
        this.carSalario = carSalario;
    }
}
