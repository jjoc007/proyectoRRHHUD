package co.edu.udistrital.rrhh.web.converter;
import co.edu.udistrital.rrhh.domain.Concepto;
import javax.faces.convert.Converter;
import org.springframework.roo.addon.jsf.converter.RooJsfConverter;

@RooJsfConverter(entity = Concepto.class)
public class ConceptoConverter implements Converter {
}
