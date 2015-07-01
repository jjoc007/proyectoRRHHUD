package co.edu.udistrital.rrhh.web.converter;
import co.edu.udistrital.rrhh.domain.Historicocargo;
import co.edu.udistrital.rrhh.service.HistoricocargoService;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.converter.RooJsfConverter;

@Configurable
@FacesConverter("co.edu.udistrital.rrhh.web.converter.HistoricocargoConverter")
@RooJsfConverter(entity = Historicocargo.class)
public class HistoricocargoConverter implements Converter {

	@Autowired
    HistoricocargoService historicocargoService;

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        Integer id = Integer.parseInt(value);
        return historicocargoService.findHistoricocargo(id);
    }

	public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value instanceof Historicocargo ? ((Historicocargo) value).getHisCodigo().toString() : "";
    }
}
