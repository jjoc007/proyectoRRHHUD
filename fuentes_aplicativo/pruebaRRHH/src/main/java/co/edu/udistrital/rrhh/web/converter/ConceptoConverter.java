package co.edu.udistrital.rrhh.web.converter;
import co.edu.udistrital.rrhh.domain.Concepto;
import co.edu.udistrital.rrhh.service.ConceptoService;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.converter.RooJsfConverter;

@Configurable
@FacesConverter("co.edu.udistrital.rrhh.web.converter.ConceptoConverter")
@RooJsfConverter(entity = Concepto.class)
public class ConceptoConverter implements Converter {

	@Autowired
    ConceptoService conceptoService;

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        Integer id = Integer.parseInt(value);
        return conceptoService.findConcepto(id);
    }

	public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value instanceof Concepto ? ((Concepto) value).getConCodigo().toString() : "";
    }
}
