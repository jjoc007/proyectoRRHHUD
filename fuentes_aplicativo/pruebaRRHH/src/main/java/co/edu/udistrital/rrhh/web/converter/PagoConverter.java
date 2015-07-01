package co.edu.udistrital.rrhh.web.converter;
import co.edu.udistrital.rrhh.domain.Pago;
import co.edu.udistrital.rrhh.service.PagoService;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.converter.RooJsfConverter;

@Configurable
@FacesConverter("co.edu.udistrital.rrhh.web.converter.PagoConverter")
@RooJsfConverter(entity = Pago.class)
public class PagoConverter implements Converter {

	@Autowired
    PagoService pagoService;

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        Integer id = Integer.parseInt(value);
        return pagoService.findPago(id);
    }

	public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value instanceof Pago ? ((Pago) value).getPagCodigo().toString() : "";
    }
}
