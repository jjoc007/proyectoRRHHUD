package co.edu.udistrital.rrhh.domain;
import java.util.Calendar;
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

@Entity
@Table(name = "empleado")
@Configurable
@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "empleado")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "afiliacions", "historicocargoes", "pagoes" })
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "emp_cedula")
    private Integer empCedula;

    public Integer getEmpCedula() {
        return this.empCedula;
    }

    public void setEmpCedula(Integer id) {
        this.empCedula = id;
    }

    @PersistenceContext
    transient EntityManager entityManager;

    public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("");

    public static final EntityManager entityManager() {
        EntityManager em = new Empleado().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

    public static long countEmpleadoes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Empleado o", Long.class).getSingleResult();
    }

    public static List<Empleado> findAllEmpleadoes() {
        return entityManager().createQuery("SELECT o FROM Empleado o", Empleado.class).getResultList();
    }

    public static List<Empleado> findAllEmpleadoes(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Empleado o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Empleado.class).getResultList();
    }

    public static Empleado findEmpleado(Integer empCedula) {
        if (empCedula == null) return null;
        return entityManager().find(Empleado.class, empCedula);
    }

    public static List<Empleado> findEmpleadoEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Empleado o", Empleado.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static List<Empleado> findEmpleadoEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Empleado o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Empleado.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Empleado attached = Empleado.findEmpleado(this.empCedula);
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
    public Empleado merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Empleado merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

    @OneToMany(mappedBy = "afiEmpleado")
    private Set<Afiliacion> afiliacions;

    @OneToMany(mappedBy = "hisEmpleado")
    private Set<Historicocargo> historicocargoes;

    @OneToMany(mappedBy = "pagEmpleado")
    private Set<Pago> pagoes;

    @Column(name = "emp_nombre", length = 50)
    @NotNull
    private String empNombre;

    @Column(name = "emp_fechaIngreso")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar empFechaIngreso;

    @Column(name = "emp_fechaSalida")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar empFechaSalida;

    @Column(name = "emp_cuentaPago")
    @NotNull
    private Integer empCuentaPago;

    @Column(name = "emp_estado", length = 1)
    @NotNull
    private String empEstado;

    public Set<Afiliacion> getAfiliacions() {
        return afiliacions;
    }

    public void setAfiliacions(Set<Afiliacion> afiliacions) {
        this.afiliacions = afiliacions;
    }

    public Set<Historicocargo> getHistoricocargoes() {
        return historicocargoes;
    }

    public void setHistoricocargoes(Set<Historicocargo> historicocargoes) {
        this.historicocargoes = historicocargoes;
    }

    public Set<Pago> getPagoes() {
        return pagoes;
    }

    public void setPagoes(Set<Pago> pagoes) {
        this.pagoes = pagoes;
    }

    public String getEmpNombre() {
        return empNombre;
    }

    public void setEmpNombre(String empNombre) {
        this.empNombre = empNombre;
    }

    public Calendar getEmpFechaIngreso() {
        return empFechaIngreso;
    }

    public void setEmpFechaIngreso(Calendar empFechaIngreso) {
        this.empFechaIngreso = empFechaIngreso;
    }

    public Calendar getEmpFechaSalida() {
        return empFechaSalida;
    }

    public void setEmpFechaSalida(Calendar empFechaSalida) {
        this.empFechaSalida = empFechaSalida;
    }

    public Integer getEmpCuentaPago() {
        return empCuentaPago;
    }

    public void setEmpCuentaPago(Integer empCuentaPago) {
        this.empCuentaPago = empCuentaPago;
    }

    public String getEmpEstado() {
        return empEstado;
    }

    public void setEmpEstado(String empEstado) {
        this.empEstado = empEstado;
    }

    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("afiliacions", "historicocargoes", "pagoes").toString();
    }
}
