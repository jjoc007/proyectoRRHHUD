package co.edu.udistrital.rrhh.service.impl;
import co.edu.udistrital.rrhh.domain.Rol;
import co.edu.udistrital.rrhh.repository.RolReprository;
import co.edu.udistrital.rrhh.service.RolService;
import co.edu.udistrital.rrhh.web.util.Constantes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RolServiceImpl implements RolService {

	@Autowired
    RolReprository rolReprository;

	public long countAllRols() {
        return rolReprository.count();
    }

	public void deleteRol(Rol rol) {
		rol.setRolEstado(Constantes.GENERAL_ESTADO_INACTIVO);
        rolReprository.save(rol);
    }

	public Rol findRol(Integer id) {
        return rolReprository.findOne(id);
    }

	public List<Rol> findAllRols() {
        return rolReprository.findAll();
    }

	public List<Rol> findRolEntries(int firstResult, int maxResults) {
        return rolReprository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveRol(Rol rol) {
        rolReprository.save(rol);
    }

	public Rol updateRol(Rol rol) {
        return rolReprository.save(rol);
    }

	@Override
	public List<Rol> findAllRolesActivos() {
		// TODO Auto-generated method stub
		return rolReprository.findAllRolActivos();
	}
}
