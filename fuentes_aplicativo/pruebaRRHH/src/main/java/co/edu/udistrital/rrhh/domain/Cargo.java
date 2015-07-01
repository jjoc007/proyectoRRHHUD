package co.edu.udistrital.rrhh.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@Entity
@Table(name = "cargo")
@RooJavaBean
@RooToString
@RooJpaEntity(versionField = "", sequenceName = "", table = "cargo")
@RooDbManaged(automaticallyDelete = true)
public class Cargo {

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@Column(name = "car_nombre", length = 50)
    @NotNull
    private String carNombre;

	@Column(name = "car_salario", precision = 22)
    @NotNull
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

	@Id
    @Column(name = "car_cogigo")
    private Integer carCogigo;

	public Integer getCarCogigo() {
        return this.carCogigo;
    }

	public void setCarCogigo(Integer id) {
        this.carCogigo = id;
    }
}
