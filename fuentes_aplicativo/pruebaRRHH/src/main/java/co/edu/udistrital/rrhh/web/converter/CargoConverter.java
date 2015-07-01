package co.edu.udistrital.rrhh.web.converter;
import co.edu.udistrital.rrhh.domain.Cargo;
import co.edu.udistrital.rrhh.service.CargoService;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.converter.RooJsfConverter;

@Configurable
@FacesConverter("co.edu.udistrital.rrhh.web.converter.CargoConverter")
@RooJsfConverter(entity = Cargo.class)
public class CargoConverter implements Converter {

	@Autowired
    CargoService cargoService;

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        Integer id = Integer.parseInt(value);
        return cargoService.findCargo(id);
    }

	public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value instanceof Cargo ? ((Cargo) value).getCarCogigo().toString() : "";
    }
}
