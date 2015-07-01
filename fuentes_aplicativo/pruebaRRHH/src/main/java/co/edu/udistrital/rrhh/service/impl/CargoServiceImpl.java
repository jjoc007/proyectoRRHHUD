package co.edu.udistrital.rrhh.service.impl;
import co.edu.udistrital.rrhh.domain.Cargo;
import co.edu.udistrital.rrhh.repository.CargoReprository;
import co.edu.udistrital.rrhh.service.CargoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CargoServiceImpl implements CargoService {

	@Autowired
    CargoReprository cargoReprository;

	public long countAllCargoes() {
        return cargoReprository.count();
    }

	public void deleteCargo(Cargo cargo) {
        cargoReprository.delete(cargo);
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
}
