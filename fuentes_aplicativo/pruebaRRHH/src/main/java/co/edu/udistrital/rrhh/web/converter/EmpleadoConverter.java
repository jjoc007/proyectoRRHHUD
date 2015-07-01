package co.edu.udistrital.rrhh.web.converter;
import co.edu.udistrital.rrhh.domain.Empleado;
import co.edu.udistrital.rrhh.service.EmpleadoService;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.converter.RooJsfConverter;

@Configurable
@FacesConverter("co.edu.udistrital.rrhh.web.converter.EmpleadoConverter")
@RooJsfConverter(entity = Empleado.class)
public class EmpleadoConverter implements Converter {

	@Autowired
    EmpleadoService empleadoService;

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        Integer id = Integer.parseInt(value);
        return empleadoService.findEmpleado(id);
    }

	public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value instanceof Empleado ? ((Empleado) value).getEmpCedula().toString() : "";
    }
}
