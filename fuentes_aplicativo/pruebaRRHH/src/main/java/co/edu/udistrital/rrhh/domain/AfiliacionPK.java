package co.edu.udistrital.rrhh.domain;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jpa.identifier.RooIdentifier;

@Embeddable
@Configurable
@RooIdentifier(dbManaged = true)
public  class AfiliacionPK implements Serializable{

	public String toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }

	public String toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }

	public static AfiliacionPK fromJsonToAfiliacionPK(String json) {
        return new JSONDeserializer<AfiliacionPK>()
        .use(null, AfiliacionPK.class).deserialize(json);
    }

	public static String toJsonArray(Collection<AfiliacionPK> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }

	public static String toJsonArray(Collection<AfiliacionPK> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }

	public static Collection<AfiliacionPK> fromJsonArrayToAfiliacionPKs(String json) {
        return new JSONDeserializer<List<AfiliacionPK>>()
        .use("values", AfiliacionPK.class).deserialize(json);
    }

	@Column(name = "afi_empleado", nullable = false)
    private Integer afiEmpleado;

	@Column(name = "afi_entidad", nullable = false)
    private Integer afiEntidad;

	public AfiliacionPK(Integer afiEmpleado, Integer afiEntidad) {
        super();
        this.afiEmpleado = afiEmpleado;
        this.afiEntidad = afiEntidad;
    }

	public AfiliacionPK() {
        super();
    }

	public Integer getAfiEmpleado() {
        return afiEmpleado;
    }

	public Integer getAfiEntidad() {
        return afiEntidad;
    }

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((afiEmpleado == null) ? 0 : afiEmpleado.hashCode());
		result = prime * result
				+ ((afiEntidad == null) ? 0 : afiEntidad.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AfiliacionPK other = (AfiliacionPK) obj;
		if (afiEmpleado == null) {
			if (other.afiEmpleado != null)
				return false;
		} else if (!afiEmpleado.equals(other.afiEmpleado))
			return false;
		if (afiEntidad == null) {
			if (other.afiEntidad != null)
				return false;
		} else if (!afiEntidad.equals(other.afiEntidad))
			return false;
		return true;
	}
	
	

	@Override
	public String toString() {
		return  afiEmpleado + ";"+ afiEntidad;
	}

	private static final long serialVersionUID = 1L;
}
