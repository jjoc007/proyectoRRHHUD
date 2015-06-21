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
@Table(name = "tbl_parametro")
@Configurable
@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "tbl_parametro")
@RooDbManaged(automaticallyDelete = true)
public class TblParametro {

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("");

	public static final EntityManager entityManager() {
        EntityManager em = new TblParametro().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countTblParametroes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM TblParametro o", Long.class).getSingleResult();
    }

	public static List<TblParametro> findAllTblParametroes() {
        return entityManager().createQuery("SELECT o FROM TblParametro o", TblParametro.class).getResultList();
    }

	public static List<TblParametro> findAllTblParametroes(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM TblParametro o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, TblParametro.class).getResultList();
    }

	public static TblParametro findTblParametro(Long parCodigo) {
        if (parCodigo == null) return null;
        return entityManager().find(TblParametro.class, parCodigo);
    }

	public static List<TblParametro> findTblParametroEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM TblParametro o", TblParametro.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<TblParametro> findTblParametroEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM TblParametro o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, TblParametro.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            TblParametro attached = TblParametro.findTblParametro(this.parCodigo);
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
    public TblParametro merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        TblParametro merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	@Column(name = "par_nombre", length = 255)
    @NotNull
    private String parNombre;

	@Column(name = "par_descripcion")
    @NotNull
    private String parDescripcion;

	@Column(name = "valor", length = 200)
    @NotNull
    private String valor;

	@Column(name = "par_tipo")
    @NotNull
    private Integer parTipo;

	public String getParNombre() {
        return parNombre;
    }

	public void setParNombre(String parNombre) {
        this.parNombre = parNombre;
    }

	public String getParDescripcion() {
        return parDescripcion;
    }

	public void setParDescripcion(String parDescripcion) {
        this.parDescripcion = parDescripcion;
    }

	public String getValor() {
        return valor;
    }

	public void setValor(String valor) {
        this.valor = valor;
    }

	public Integer getParTipo() {
        return parTipo;
    }

	public void setParTipo(Integer parTipo) {
        this.parTipo = parTipo;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "par_codigo")
    private Long parCodigo;

	public Long getParCodigo() {
        return this.parCodigo;
    }

	public void setParCodigo(Long id) {
        this.parCodigo = id;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
