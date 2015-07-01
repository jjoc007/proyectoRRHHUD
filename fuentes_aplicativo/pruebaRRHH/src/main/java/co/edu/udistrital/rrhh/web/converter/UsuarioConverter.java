package co.edu.udistrital.rrhh.web.converter;
import co.edu.udistrital.rrhh.domain.Usuario;
import co.edu.udistrital.rrhh.service.UsuarioService;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.converter.RooJsfConverter;

@FacesConverter("co.edu.udistrital.rrhh.web.converter.UsuarioConverter")
@Configurable
@RooJsfConverter(entity = Usuario.class)
public class UsuarioConverter implements Converter {

	@Autowired
    UsuarioService usuarioService;

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        Integer id = Integer.parseInt(value);
        return usuarioService.findUsuario(id);
    }

	public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value instanceof Usuario ? ((Usuario) value).getUsuUsuario().toString() : "";
    }
}
