package co.edu.udistrital.rrhh.domain;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

@Entity
@Table(name = "tbl_cargo")
@Configurable
@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "tbl_cargo")
@RooDbManaged(automaticallyDelete = true)
public class TblCargo {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "car_codigo", length = 15)
    private String carCodigo;

	public String getCarCodigo() {
        return this.carCodigo;
    }

	public void setCarCodigo(String id) {
        this.carCodigo = id;
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("");

	public static final EntityManager entityManager() {
        EntityManager em = new TblCargo().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countTblCargoes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM TblCargo o", Long.class).getSingleResult();
    }

	public static List<TblCargo> findAllTblCargoes() {
        return entityManager().createQuery("SELECT o FROM TblCargo o", TblCargo.class).getResultList();
    }

	public static List<TblCargo> findAllTblCargoes(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM TblCargo o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, TblCargo.class).getResultList();
    }

	public static TblCargo findTblCargo(String carCodigo) {
        if (carCodigo == null || carCodigo.length() == 0) return null;
        return entityManager().find(TblCargo.class, carCodigo);
    }

	public static List<TblCargo> findTblCargoEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM TblCargo o", TblCargo.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<TblCargo> findTblCargoEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM TblCargo o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, TblCargo.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            TblCargo attached = TblCargo.findTblCargo(this.carCodigo);
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
    public TblCargo merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        TblCargo merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@Column(name = "car_nombre", length = 100)
    private String carNombre;

	@Column(name = "car_salario", precision = 22)
    private Double carSalario;

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
