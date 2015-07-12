package co.edu.udistrital.rrhh.service.impl;
import co.edu.udistrital.rrhh.domain.Cargo;
import co.edu.udistrital.rrhh.repository.CargoRepository;
import co.edu.udistrital.rrhh.service.CargoService;
import co.edu.udistrital.rrhh.web.util.Constantes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CargoServiceImpl implements CargoService {

	@Autowired
    CargoRepository cargoReprository;

	public long countAllCargoes() {
        return cargoReprository.count();
    }

	public void deleteCargo(Cargo cargo) {
        cargoReprository.delete(cargo);
    }
    
	// Cambio de Estado de Cargp
		public void  ActEstadoCargo(Cargo cargo) {
			cargo.setCarEstado(Constantes.GENERAL_ESTADO_INACTIVO);
			saveCargo(cargo);
	}

	public Cargo findCargo(Integer id) {
        return cargoReprository.findOne(id);
    }

	public List<Cargo> findAllCargoes() {
        return cargoReprository.findAll();
    }

	public List<Cargo> findCargoEntries(int firstResult, int maxResults) {
        return cargoReprository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveCargo(Cargo cargo) {
        cargoReprository.save(cargo);
    }

	public Cargo updateCargo(Cargo cargo) {
        return cargoReprository.save(cargo);
    }
	
	// Buscar cargos Activos
	public List<Cargo> findAllCargoAct(String estado) {    
        return cargoReprository.findAllCargoAct(Constantes.GENERAL_ESTADO_ACTIVO);
    }
}
