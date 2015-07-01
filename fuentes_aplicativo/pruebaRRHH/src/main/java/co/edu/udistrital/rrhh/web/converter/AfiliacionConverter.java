package co.edu.udistrital.rrhh.web.converter;
import co.edu.udistrital.rrhh.domain.Afiliacion;
import co.edu.udistrital.rrhh.domain.AfiliacionPK;
import co.edu.udistrital.rrhh.service.AfiliacionService;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.converter.RooJsfConverter;

@FacesConverter("co.edu.udistrital.rrhh.web.converter.AfiliacionConverter")
@Configurable
@RooJsfConverter(entity = Afiliacion.class)
public class AfiliacionConverter implements Converter {

	@Autowired
    AfiliacionService afiliacionService;

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        
        String cadena[] =   value.toString().split(";");
        AfiliacionPK id = new AfiliacionPK(Integer.parseInt(cadena[0]), Integer.parseInt(cadena[1]));
        
        return afiliacionService.findAfiliacion(id);
    }

	public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value instanceof Afiliacion ? ((Afiliacion) value).getId().toString() : "";
    }
}
