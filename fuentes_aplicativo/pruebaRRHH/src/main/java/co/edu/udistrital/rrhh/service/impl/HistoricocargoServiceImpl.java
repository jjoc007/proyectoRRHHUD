package co.edu.udistrital.rrhh.service.impl;
import co.edu.udistrital.rrhh.domain.Historicocargo;
import co.edu.udistrital.rrhh.repository.HistoricoCargoRepository;
import co.edu.udistrital.rrhh.service.HistoricocargoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HistoricocargoServiceImpl implements HistoricocargoService {

	@Autowired
    HistoricoCargoRepository historicocargoReprository;

	public long countAllHistoricocargoes() {
        return historicocargoReprository.count();
    }

	public void deleteHistoricocargo(Historicocargo historicocargo) {
        historicocargoReprository.delete(historicocargo);
    }

	public Historicocargo findHistoricocargo(Integer id) {
        return historicocargoReprository.findOne(id);
    }

	public List<Historicocargo> findAllHistoricocargoes() {
        return historicocargoReprository.findAll();
    }

	public List<Historicocargo> findHistoricocargoEntries(int firstResult, int maxResults) {
        return historicocargoReprository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveHistoricocargo(Historicocargo historicocargo) {
        historicocargoReprository.save(historicocargo);
    }

	public Historicocargo updateHistoricocargo(Historicocargo historicocargo) {
        return historicocargoReprository.save(historicocargo);
    }
}
