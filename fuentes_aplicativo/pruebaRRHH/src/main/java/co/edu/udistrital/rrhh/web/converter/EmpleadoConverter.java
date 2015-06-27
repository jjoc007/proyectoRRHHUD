package co.edu.udistrital.rrhh.web.converter;
import co.edu.udistrital.rrhh.domain.Empleado;
import javax.faces.convert.Converter;
import org.springframework.roo.addon.jsf.converter.RooJsfConverter;

@RooJsfConverter(entity = Empleado.class)
public class EmpleadoConverter implements Converter {
}
