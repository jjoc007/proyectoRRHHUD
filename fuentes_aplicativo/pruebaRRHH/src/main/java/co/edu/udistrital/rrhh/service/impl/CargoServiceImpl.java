package co.edu.udistrital.rrhh.service.impl;
import co.edu.udistrital.rrhh.domain.Cargo;
import co.edu.udistrital.rrhh.domain.Concepto;
import co.edu.udistrital.rrhh.domain.Historicocargo;
import co.edu.udistrital.rrhh.repository.CargoRepository;
import co.edu.udistrital.rrhh.repository.ConceptoRepository;
import co.edu.udistrital.rrhh.repository.HistoricoCargoRepository;
import co.edu.udistrital.rrhh.service.CargoService;
import co.edu.udistrital.rrhh.web.util.Constantes;
import co.edu.udistrital.rrhh.web.util.NominaException;
import co.edu.udistrital.rrhh.web.util.Utilidades;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CargoServiceImpl implements CargoService {

	@Autowired
	CargoRepository cargoReprository;

	@Autowired
	HistoricoCargoRepository historicoCargoRepository;

	@Autowired
	ConceptoRepository conceptoRepository;
	
	public long countAllCargoes() {
		return cargoReprository.count();
	}

	public void deleteCargo(Cargo cargo) {
		cargoReprository.delete(cargo);
	}

	// Cambio de Estado de Cargp
	public void  ActEstadoCargo(Cargo cargo) throws NominaException{

		//valida que en historico dcargo no hallan registros con ese cargo activo,  con esto se demuestra que el cargo no esta en uso y se puede innactivar 
		List<Historicocargo> historicos =  historicoCargoRepository.findHistoricoCargosActivosByCargo(cargo.getCarCogigo(), Constantes.GENERAL_ESTADO_ACTIVO);

		if(historicos != null && historicos.size() > 0){
			 throw new NominaException("Hay empeados asignados a este cargo ");

		}else{

			cargo.setCarEstado(Constantes.GENERAL_ESTADO_INACTIVO);
			saveCargo(cargo);
		}
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

	public void saveCargo(Cargo cargo)throws NominaException {
		
		Concepto conceptoSMLV = conceptoRepository.findOne(Constantes.CONCEPTO_SMLV);
		
		
		if(cargo.getCarSalario() >= conceptoSMLV.getConValor()){		
			cargoReprository.save(cargo);
		}else{
			throw new NominaException("El salario delc argo debe ser mayor a: "+Utilidades.doubleFormated(conceptoSMLV.getConValor()));
		}
	}

	public Cargo updateCargo(Cargo cargo) {
		return cargoReprository.save(cargo);
	}

	// Buscar cargos Activos
	public List<Cargo> findAllCargoAct(String estado) {    
		return cargoReprository.findAllCargoAct(Constantes.GENERAL_ESTADO_ACTIVO);
	}
}
