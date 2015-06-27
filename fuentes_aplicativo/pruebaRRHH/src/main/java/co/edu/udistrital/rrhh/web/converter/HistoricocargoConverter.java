package co.edu.udistrital.rrhh.web.converter;
import co.edu.udistrital.rrhh.domain.Historicocargo;
import javax.faces.convert.Converter;
import org.springframework.roo.addon.jsf.converter.RooJsfConverter;

@RooJsfConverter(entity = Historicocargo.class)
public class HistoricocargoConverter implements Converter {
}
