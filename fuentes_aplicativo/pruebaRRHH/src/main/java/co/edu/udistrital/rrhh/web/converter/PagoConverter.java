package co.edu.udistrital.rrhh.web.converter;
import co.edu.udistrital.rrhh.domain.Pago;
import javax.faces.convert.Converter;
import org.springframework.roo.addon.jsf.converter.RooJsfConverter;

@RooJsfConverter(entity = Pago.class)
public class PagoConverter implements Converter {
}
