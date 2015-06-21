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
@Table(name = "tbl_usuario")
@Configurable
@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "tbl_usuario")
@RooDbManaged(automaticallyDelete = true)
public class TblUsuario {

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("");

	public static final EntityManager entityManager() {
        EntityManager em = new TblUsuario().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countTblUsuarios() {
        return entityManager().createQuery("SELECT COUNT(o) FROM TblUsuario o", Long.class).getSingleResult();
    }

	public static List<TblUsuario> findAllTblUsuarios() {
        return entityManager().createQuery("SELECT o FROM TblUsuario o", TblUsuario.class).getResultList();
    }

	public static List<TblUsuario> findAllTblUsuarios(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM TblUsuario o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, TblUsuario.class).getResultList();
    }

	public static TblUsuario findTblUsuario(String usuUsuario) {
        if (usuUsuario == null || usuUsuario.length() == 0) return null;
        return entityManager().find(TblUsuario.class, usuUsuario);
    }

	public static List<TblUsuario> findTblUsuarioEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM TblUsuario o", TblUsuario.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<TblUsuario> findTblUsuarioEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM TblUsuario o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, TblUsuario.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            TblUsuario attached = TblUsuario.findTblUsuario(this.usuUsuario);
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
    public TblUsuario merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        TblUsuario merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "usu_usuario", length = 100)
    private String usuUsuario;

	public String getUsuUsuario() {
        return this.usuUsuario;
    }

	public void setUsuUsuario(String id) {
        this.usuUsuario = id;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@Column(name = "usu_nombre", length = 200)
    private String usuNombre;

	@Column(name = "usu_clave", length = 200)
    private String usuClave;

	@Column(name = "usu_foto")
    private byte[] usuFoto;

	@Column(name = "usu_estado", length = 2)
    private String usuEstado;

	@Column(name = "usu_correo", length = 200)
    private String usuCorreo;

	public String getUsuNombre() {
        return usuNombre;
    }

	public void setUsuNombre(String usuNombre) {
        this.usuNombre = usuNombre;
    }

	public String getUsuClave() {
        return usuClave;
    }

	public void setUsuClave(String usuClave) {
        this.usuClave = usuClave;
    }

	public byte[] getUsuFoto() {
        return usuFoto;
    }

	public void setUsuFoto(byte[] usuFoto) {
        this.usuFoto = usuFoto;
    }

	public String getUsuEstado() {
        return usuEstado;
    }

	public void setUsuEstado(String usuEstado) {
        this.usuEstado = usuEstado;
    }

	public String getUsuCorreo() {
        return usuCorreo;
    }

	public void setUsuCorreo(String usuCorreo) {
        this.usuCorreo = usuCorreo;
    }
}
