package co.edu.udistrital.rrhh.domain;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@Entity
@Table(name = "aporte")
@RooJavaBean
@RooToString
@RooJpaEntity(versionField = "", sequenceName = "", table = "aporte")
@RooDbManaged(automaticallyDelete = true)
public class Aporte {
	
	
	
	public Aporte() {
		super();
	}

	public Aporte(Integer apoEntidad, String apoTipo, Date apoPeriodo,
			Double apoValor, Double apoValorEmpresa) {
		super();
		this.apoEntidad = apoEntidad;
		this.apoTipo = apoTipo;
		this.apoPeriodo = apoPeriodo;
		this.apoValor = apoValor;
		this.apoValorEmpresa = apoValorEmpresa;
	}

	@Column(name = "apo_entidad")
    @NotNull
    private Integer apoEntidad;

	@Column(name = "apo_tipo", length = 20)
    @NotNull
    private String apoTipo;

	@Column(name = "apo_periodo")
    @NotNull
    private Date apoPeriodo;

	@Column(name = "apo_valor", precision = 22)
    @NotNull
    private Double apoValor;

	@Column(name = "apo_valorEmpresa")
    @NotNull
    private Double apoValorEmpresa;
	
	public Integer getApoEntidad() {
        return apoEntidad;
    }

	public void setApoEntidad(Integer apoEntidad) {
        this.apoEntidad = apoEntidad;
    }

	public String getApoTipo() {
        return apoTipo;
    }

	public void setApoTipo(String apoTipo) {
        this.apoTipo = apoTipo;
    }

	public Date getApoPeriodo() {
        return apoPeriodo;
    }

	public void setApoPeriodo(Date apoPeriodo) {
        this.apoPeriodo = apoPeriodo;
    }

	public Double getApoValor() {
        return apoValor;
    }

	public void setApoValor(Double apoValor) {
        this.apoValor = apoValor;
    }

	@Id
    @Column(name = "apo_codigo")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer apoCodigo;

	public Integer getApoCodigo() {
        return this.apoCodigo;
    }

	public void setApoCodigo(Integer id) {
        this.apoCodigo = id;
    }

	public Double getApoValorEmpresa() {
		return apoValorEmpresa;
	}

	public void setApoValorEmpresa(Double apoValorEmpresa) {
		this.apoValorEmpresa = apoValorEmpresa;
	}
	
	
}
