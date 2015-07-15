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
import co.edu.udistrital.rrhh.domain.Entidad;
import co.edu.udistrital.rrhh.domain.Rol;
import co.edu.udistrital.rrhh.service.CargoService;
import co.edu.udistrital.rrhh.service.EntidadService;
import co.edu.udistrital.rrhh.service.RolService;
import co.edu.udistrital.rrhh.web.util.Constantes;

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
	

	@Autowired
	EntidadService entidadService; 
	
	public AutoCompleteBean() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Rol> completeRol(String query) {
        List<Rol> suggestions = new ArrayList<Rol>();
        for (Rol rol : rolService.findAllRolesActivos()) {
            String t27clasemercadoStr = String.valueOf(rol.getRolNombre());
            if (t27clasemercadoStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(rol);
            }
        }
        return suggestions;
    }
	
	public List<Cargo> completeCargo(String query) {
        List<Cargo> suggestions = new ArrayList<Cargo>();
        for (Cargo cargo : cargoService.findAllCargoAct(Constantes.GENERAL_ESTADO_ACTIVO)) {
            String t27clasemercadoStr = String.valueOf(cargo.getCarNombre());
            if (t27clasemercadoStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(cargo);
            }
        }
        return suggestions;
    }
	
	
	public List<Entidad> completeEntidadesSalud(String query) {
        List<Entidad> suggestions = new ArrayList<Entidad>();
        for (Entidad entidad : entidadService.findAllEntidadesByTipo(Constantes.TIPO_ENTIDAD_SALUD)) {
            String t27clasemercadoStr = String.valueOf(entidad.getEntNombre());
            if (t27clasemercadoStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(entidad);
            }
        }
        return suggestions;
    }
	
	
	public List<Entidad> completeEntidadesCajaCompensacion(String query) {
        List<Entidad> suggestions = new ArrayList<Entidad>();
        for (Entidad entidad : entidadService.findAllEntidadesByTipo(Constantes.TIPO_ENTIDAD_CAJA_COMPENSACION)) {
            String t27clasemercadoStr = String.valueOf(entidad.getEntNombre());
            if (t27clasemercadoStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(entidad);
            }
        }
        return suggestions;
    }
	
	public List<Entidad> completeEntidadesPension(String query) {
        List<Entidad> suggestions = new ArrayList<Entidad>();
        for (Entidad entidad : entidadService.findAllEntidadesByTipo(Constantes.TIPO_ENTIDAD_PENSION)) {
            String t27clasemercadoStr = String.valueOf(entidad.getEntNombre());
            if (t27clasemercadoStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(entidad);
            }
        }
        return suggestions;
    }
	
	public List<Entidad> completeEntidadesCesantias(String query) {
        List<Entidad> suggestions = new ArrayList<Entidad>();
        for (Entidad entidad : entidadService.findAllEntidadesByTipo(Constantes.TIPO_ENTIDAD_CESANTIAS)) {
            String t27clasemercadoStr = String.valueOf(entidad.getEntNombre());
            if (t27clasemercadoStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(entidad);
            }
        }
        return suggestions;
    }
	
	public List<Entidad> completeEntidadesArl(String query) {
        List<Entidad> suggestions = new ArrayList<Entidad>();
        for (Entidad entidad : entidadService.findAllEntidadesByTipo(Constantes.TIPO_ENTIDAD_ARL)) {
            String t27clasemercadoStr = String.valueOf(entidad.getEntNombre());
            if (t27clasemercadoStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(entidad);
            }
        }
        return suggestions;
    }
	
	
	private static final long serialVersionUID = 1L;
}

