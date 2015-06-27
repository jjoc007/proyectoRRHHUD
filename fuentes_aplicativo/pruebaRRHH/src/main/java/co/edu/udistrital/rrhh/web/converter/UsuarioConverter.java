package co.edu.udistrital.rrhh.web.converter;
import co.edu.udistrital.rrhh.domain.Usuario;
import javax.faces.convert.Converter;
import org.springframework.roo.addon.jsf.converter.RooJsfConverter;

@RooJsfConverter(entity = Usuario.class)
public class UsuarioConverter implements Converter {
}
