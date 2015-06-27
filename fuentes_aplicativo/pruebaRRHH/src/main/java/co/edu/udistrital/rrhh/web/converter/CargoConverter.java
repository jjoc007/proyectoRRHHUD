package co.edu.udistrital.rrhh.web.converter;
import co.edu.udistrital.rrhh.domain.Cargo;
import javax.faces.convert.Converter;
import org.springframework.roo.addon.jsf.converter.RooJsfConverter;

@RooJsfConverter(entity = Cargo.class)
public class CargoConverter implements Converter {
}
