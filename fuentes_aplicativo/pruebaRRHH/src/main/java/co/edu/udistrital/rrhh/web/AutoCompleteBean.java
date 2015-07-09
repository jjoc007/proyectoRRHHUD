package co.edu.udistrital.rrhh.web;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;

import co.edu.udistrital.rrhh.domain.Cargo;
import co.edu.udistrital.rrhh.domain.Rol;
import co.edu.udistrital.rrhh.service.CargoService;
import co.edu.udistrital.rrhh.service.RolService;

@Configurable
@ManagedBean(name = "autoCompleteBean")
@SessionScoped
@RooSerializable
@RooJsfManagedBean(entity = AutoCompleteBean.class, beanName = "autoCompleteBean")
public class AutoCompleteBean implements Serializable {

	
	@Autowired
	RolService  rolService;
	
	@Autowired
	CargoService cargoService; 
	
	public AutoCompleteBean() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Rol> completeRol(String query) {
        List<Rol> suggestions = new ArrayList<Rol>();
        for (Rol rol : rolService.findAllRols()) {
            String t27clasemercadoStr = String.valueOf(rol.getRolNombre());
            if (t27clasemercadoStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(rol);
            }
        }
        return suggestions;
    }
	
	public List<Cargo> completeCargo(String query) {
        List<Cargo> suggestions = new ArrayList<Cargo>();
        for (Cargo cargo : cargoService.findAllCargoes()) {
            String t27clasemercadoStr = String.valueOf(cargo.getCarNombre());
            if (t27clasemercadoStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(cargo);
            }
        }
        return suggestions;
    }
	
	private static final long serialVersionUID = 1L;
}

