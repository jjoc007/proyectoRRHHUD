package co.edu.udistrital.rrhh.domain;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

import co.edu.udistrital.rrhh.web.util.Utilidades;

@Entity
@Table(name = "cargo")
@RooJavaBean
@RooToString
@RooJpaEntity(versionField = "", sequenceName = "", table = "cargo")
@RooDbManaged(automaticallyDelete = true)
public class Cargo {

	@Column(name = "car_nombre", length = 50)
    @NotNull
    private String carNombre;
	
	@Column(name = "car_descripcion", length = 50)
    @NotNull
    private String carDescripcion;

	@Column(name = "car_salario", precision = 22)
    @NotNull
    private Double carSalario;

	@Column(name = "car_estado", length = 1)
    @NotNull
    private String carEstado;

	public String getCarNombre() {
        return carNombre;
    }
	
	@OneToMany(mappedBy = "cargo")
    private List<Empleado> empleados;
	
	@OneToMany(mappedBy = "hisCargo")
    private List<Historicocargo> hisCargos;

	public void setCarNombre(String carNombre) {
        this.carNombre = carNombre;
    }

	public Double getCarSalario() {
        return carSalario;
    }

	public void setCarSalario(Double carSalario) {
        this.carSalario = carSalario;
    }

	public String getCarEstado() {
        return carEstado;
    }

	public void setCarEstado(String carEstado) {
        this.carEstado = carEstado;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@Id
    @Column(name = "car_cogigo")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer carCogigo;

	public Integer getCarCogigo() {
        return this.carCogigo;
    }

	public void setCarCogigo(Integer id) {
        this.carCogigo = id;
    }

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public List<Historicocargo> getHisCargos() {
		return hisCargos;
	}

	public void setHisCargos(List<Historicocargo> hisCargos) {
		this.hisCargos = hisCargos;
	}
	

	public String getFormatedSalario(){
		
		return Utilidades.doubleFormated(carSalario);
	}

	public String getCarDescripcion() {
		return carDescripcion;
	}

	public void setCarDescripcion(String carDescripcion) {
		this.carDescripcion = carDescripcion;
	}
	
	public String getStringrmatedSalary(){
		
		if(this.carSalario!=null){
			return Utilidades.doubleFormated(carSalario);
		}
		return "";
	}
	}
