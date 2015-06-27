package co.edu.udistrital.rrhh.web.converter;
import co.edu.udistrital.rrhh.domain.Aporte;
import javax.faces.convert.Converter;
import org.springframework.roo.addon.jsf.converter.RooJsfConverter;

@RooJsfConverter(entity = Aporte.class)
public class AporteConverter implements Converter {
}
