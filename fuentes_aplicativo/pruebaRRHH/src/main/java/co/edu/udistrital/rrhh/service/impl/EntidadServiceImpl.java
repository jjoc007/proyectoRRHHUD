package co.edu.udistrital.rrhh.service.impl;
import co.edu.udistrital.rrhh.domain.Entidad;
import co.edu.udistrital.rrhh.repository.EntidadRepository;
import co.edu.udistrital.rrhh.service.EntidadService;
import co.edu.udistrital.rrhh.web.util.Constantes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EntidadServiceImpl implements EntidadService {

	@Autowired
    EntidadRepository entidadReprository;

	public long countAllEntidads() {
        return entidadReprository.count();
    }

	public void deleteEntidad(Entidad entidad) {
		entidad.setEntEstado(Constantes.GENERAL_ESTADO_INACTIVO);
        entidadReprository.save(entidad);
    }

	public Entidad findEntidad(Integer id) {
        return entidadReprository.findOne(id);
    }

	public List<Entidad> findAllEntidads() {
        return entidadReprository.findAllEntidadesActivas();
    }

	public List<Entidad> findEntidadEntries(int firstResult, int maxResults) {
        return entidadReprository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveEntidad(Entidad entidad) {
        entidadReprository.save(entidad);
    }

	public Entidad updateEntidad(Entidad entidad) {
        return entidadReprository.save(entidad);
    }

	@Override
	public List<Entidad> findAllEntidadesByTipo(String tipo) {
		// TODO Auto-generated method stub
		return entidadReprository.findAllEntidadesByTipo(tipo);
	}
}
