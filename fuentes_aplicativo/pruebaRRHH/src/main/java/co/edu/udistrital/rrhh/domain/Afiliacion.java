package co.edu.udistrital.rrhh.domain;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@Entity
@Table(name = "afiliacion")
@RooJavaBean
@RooToString
@RooJpaEntity(identifierType = AfiliacionPK.class, versionField = "", sequenceName = "", table = "afiliacion")
@RooDbManaged(automaticallyDelete = true)
public class Afiliacion {

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@EmbeddedId
    private AfiliacionPK id;

	public AfiliacionPK getId() {
        return this.id;
    }

	public void setId(AfiliacionPK id) {
        this.id = id;
    }
}
