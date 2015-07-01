package co.edu.udistrital.rrhh.service.impl;
import co.edu.udistrital.rrhh.domain.Aporte;
import co.edu.udistrital.rrhh.repository.AporteReprository;
import co.edu.udistrital.rrhh.service.AporteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AporteServiceImpl implements AporteService {

	@Autowired
    AporteReprository aporteReprository;

	public long countAllAportes() {
        return aporteReprository.count();
    }

	public void deleteAporte(Aporte aporte) {
        aporteReprository.delete(aporte);
    }

	public Aporte findAporte(Integer id) {
        return aporteReprository.findOne(id);
    }

	public List<Aporte> findAllAportes() {
        return aporteReprository.findAll();
    }

	public List<Aporte> findAporteEntries(int firstResult, int maxResults) {
        return aporteReprository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveAporte(Aporte aporte) {
        aporteReprository.save(aporte);
    }

	public Aporte updateAporte(Aporte aporte) {
        return aporteReprository.save(aporte);
    }
}
