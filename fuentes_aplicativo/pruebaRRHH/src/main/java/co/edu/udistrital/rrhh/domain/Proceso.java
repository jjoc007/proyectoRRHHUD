package co.edu.udistrital.rrhh.domain;
import java.util.Date;
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
@Table(name = "proceso")
@RooJavaBean
@RooToString
@RooJpaEntity(versionField = "", sequenceName = "", table = "proceso")
@RooDbManaged(automaticallyDelete = true)
public class Proceso {

	@Column(name = "pro_nombre")
    @NotNull
    private String proNombre;
	
	@Id
    @Column(name = "pro_codigo")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private String proCodigo;

	@Column(name = "pro_periodo", precision = 22)
    @NotNull
    private Date proPeriodo;
	
	public Proceso() {
		// TODO Auto-generated constructor stub
	}

	public String getProNombre() {
		return proNombre;
	}

	public void setProNombre(String proNombre) {
		this.proNombre = proNombre;
	}

	public String getProCodigo() {
		return proCodigo;
	}

	public void setProCodigo(String proCodigo) {
		this.proCodigo = proCodigo;
	}

	public Date getProPeriodo() {
		return proPeriodo;
	}

	public void setProPeriodo(Date proPeriodo) {
		this.proPeriodo = proPeriodo;
	}
	


}
