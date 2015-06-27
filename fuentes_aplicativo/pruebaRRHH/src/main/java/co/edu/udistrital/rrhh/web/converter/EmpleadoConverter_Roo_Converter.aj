// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package co.edu.udistrital.rrhh.web.converter;

import co.edu.udistrital.rrhh.domain.Empleado;
import co.edu.udistrital.rrhh.service.EmpleadoService;
import co.edu.udistrital.rrhh.web.converter.EmpleadoConverter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import org.springframework.beans.factory.annotation.Autowired;

privileged aspect EmpleadoConverter_Roo_Converter {
    
    declare @type: EmpleadoConverter: @FacesConverter("co.edu.udistrital.rrhh.web.converter.EmpleadoConverter");
    
    @Autowired
    EmpleadoService EmpleadoConverter.empleadoService;
    
    public Object EmpleadoConverter.getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        Integer id = Integer.parseInt(value);
        return empleadoService.findEmpleado(id);
    }
    
    public String EmpleadoConverter.getAsString(FacesContext context, UIComponent component, Object value) {
        return value instanceof Empleado ? ((Empleado) value).getEmpCedula().toString() : "";
    }
    
}
