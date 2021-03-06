package co.edu.udistrital.rrhh.domain;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

import co.edu.udistrital.rrhh.web.util.Constantes;

@Entity
@Table(name = "entidad")
@RooJavaBean
@RooToString
@RooJpaEntity(versionField = "", sequenceName = "", table = "entidad")
@RooDbManaged(automaticallyDelete = true)
public class Entidad {

	@Id
    @Column(name = "ent_codigo")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer entCodigo;

	public Integer getEntCodigo() {
        return this.entCodigo;
    }

	public void setEntCodigo(Integer id) {
        this.entCodigo = id;
    }

	@Column(name = "ent_nombre", length = 50)
    @NotNull
    private String entNombre;

	@Column(name = "ent_cuenta")
    @NotNull
    private Integer entCuenta;

	@Column(name = "ent_estado", length = 1)
    @NotNull
    private String entEstado;
	
	@Column(name = "ent_tipo", length = 2)
    @NotNull
    private String entTipo;

	public String getEntNombre() {
        return entNombre;
    }

	public void setEntNombre(String entNombre) {
        this.entNombre = entNombre;
    }

	public Integer getEntCuenta() {
        return entCuenta;
    }

	public void setEntCuenta(Integer entCuenta) {
        this.entCuenta = entCuenta;
    }

	public String getEntEstado() {
        return entEstado;
    }

	public void setEntEstado(String entEstado) {
        this.entEstado = entEstado;
    }


	public String getEntTipo() {
		return entTipo;
	}

	public void setEntTipo(String entTipo) {
		this.entTipo = entTipo;
	}
	
	@OneToMany(mappedBy = "entidadSalud", fetch=FetchType.EAGER)
    private List<Empleado> empleadosSalud;
	
	@OneToMany(mappedBy = "entidadPension", fetch=FetchType.EAGER)
    private List<Empleado> empleadosPension;
	
	@OneToMany(mappedBy = "entidadCesantias", fetch=FetchType.EAGER)
    private List<Empleado> empleadosCesantias;
	
	@OneToMany(mappedBy = "entidadArp", fetch=FetchType.EAGER)
    private List<Empleado> empleadosArp;
	
	@OneToMany(mappedBy = "entidadCajaCompensacion", fetch=FetchType.EAGER)
    private List<Empleado> empleadosCajaComp;

	public List<Empleado> getEmpleadosSalud() {
		return empleadosSalud;
	}

	public void setEmpleadosSalud(List<Empleado> empleadosSalud) {
		this.empleadosSalud = empleadosSalud;
	}

	public List<Empleado> getEmpleadosPension() {
		return empleadosPension;
	}

	public void setEmpleadosPension(List<Empleado> empleadosPension) {
		this.empleadosPension = empleadosPension;
	}

	public List<Empleado> getEmpleadosCesantias() {
		return empleadosCesantias;
	}

	public void setEmpleadosCesantias(List<Empleado> empleadosCesantias) {
		this.empleadosCesantias = empleadosCesantias;
	}

	public List<Empleado> getEmpleadosArp() {
		return empleadosArp;
	}

	public void setEmpleadosArp(List<Empleado> empleadosArp) {
		this.empleadosArp = empleadosArp;
	}

	public List<Empleado> getEmpleadosCajaComp() {
		return empleadosCajaComp;
	}

	public void setEmpleadosCajaComp(List<Empleado> empleadosCajaComp) {
		this.empleadosCajaComp = empleadosCajaComp;
	}
	
	
	public String getNombreTipoEntidad(){
		
		if(this.entTipo != null){
			if(this.entTipo.equals(Constantes.TIPO_ENTIDAD_ARL)){
				
				return "ARL";
				
			}else if (this.entTipo.equals(Constantes.TIPO_ENTIDAD_CAJA_COMPENSACION)){
				return "CAJA DE COMPESACION";
				
			}else if (this.entTipo.equals(Constantes.TIPO_ENTIDAD_CESANTIAS)){
				return "CESANTIAS";
				
			}else if (this.entTipo.equals(Constantes.TIPO_ENTIDAD_PENSION)){
				return "PENSION";
				
			}else if (this.entTipo.equals(Constantes.TIPO_ENTIDAD_SALUD)){
				return "SALUD";
				
			}
				return "";
			
		}
		
		return "";
		
	}
	
	
	
	
}
