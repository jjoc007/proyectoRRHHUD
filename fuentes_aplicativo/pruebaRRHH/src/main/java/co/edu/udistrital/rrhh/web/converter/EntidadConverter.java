package co.edu.udistrital.rrhh.web.converter;
import co.edu.udistrital.rrhh.domain.Entidad;
import javax.faces.convert.Converter;
import org.springframework.roo.addon.jsf.converter.RooJsfConverter;

@RooJsfConverter(entity = Entidad.class)
public class EntidadConverter implements Converter {
}
