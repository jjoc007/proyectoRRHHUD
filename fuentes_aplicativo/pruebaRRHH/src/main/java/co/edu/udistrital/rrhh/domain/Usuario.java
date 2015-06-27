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
@Table(name = "usuario")
@Configurable
@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "usuario")
@RooDbManaged(automaticallyDelete = true)
public class Usuario {

    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "usu_usuario")
    private Integer usuUsuario;

    public Integer getUsuUsuario() {
        return this.usuUsuario;
    }

    public void setUsuUsuario(Integer id) {
        this.usuUsuario = id;
    }

    @PersistenceContext
    transient EntityManager entityManager;

    public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("");

    public static final EntityManager entityManager() {
        EntityManager em = new Usuario().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

    public static long countUsuarios() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Usuario o", Long.class).getSingleResult();
    }

    public static List<Usuario> findAllUsuarios() {
        return entityManager().createQuery("SELECT o FROM Usuario o", Usuario.class).getResultList();
    }

    public static List<Usuario> findAllUsuarios(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Usuario o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Usuario.class).getResultList();
    }

    public static Usuario findUsuario(Integer usuUsuario) {
        if (usuUsuario == null) return null;
        return entityManager().find(Usuario.class, usuUsuario);
    }

    public static List<Usuario> findUsuarioEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Usuario o", Usuario.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static List<Usuario> findUsuarioEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Usuario o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Usuario.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Usuario attached = Usuario.findUsuario(this.usuUsuario);
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
    public Usuario merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Usuario merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

    @Column(name = "usu_nombre", length = 50)
    @NotNull
    private String usuNombre;

    @Column(name = "usu_clave", length = 50)
    @NotNull
    private String usuClave;

    @Column(name = "usu_estado", length = 1)
    @NotNull
    private String usuEstado;

    @Column(name = "usu_correo", length = 80)
    @NotNull
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
