package co.edu.udistrital.rrhh.domain;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@Entity
@Table(name = "historicocargo")
@RooJavaBean
@RooToString
@RooJpaEntity(versionField = "", sequenceName = "", table = "historicocargo")
@RooDbManaged(automaticallyDelete = true)
public class Historicocargo {

	@Column(name = "his_empleado")
    @NotNull
    private Integer hisEmpleado;

	@Column(name = "his_cargo")
    @NotNull
    private Integer hisCargo;

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

	public Integer getHisEmpleado() {
        return hisEmpleado;
    }

	public void setHisEmpleado(Integer hisEmpleado) {
        this.hisEmpleado = hisEmpleado;
    }

	public Integer getHisCargo() {
        return hisCargo;
    }

	public void setHisCargo(Integer hisCargo) {
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
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@Id
    @Column(name = "his_codigo")
    private Integer hisCodigo;

	public Integer getHisCodigo() {
        return this.hisCodigo;
    }

	public void setHisCodigo(Integer id) {
        this.hisCodigo = id;
    }
}
