package co.edu.udistrital.rrhh.service.impl;
import co.edu.udistrital.rrhh.domain.Afiliacion;
import co.edu.udistrital.rrhh.domain.AfiliacionPK;
import co.edu.udistrital.rrhh.repository.AfiliacionReprository;
import co.edu.udistrital.rrhh.service.AfiliacionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AfiliacionServiceImpl implements AfiliacionService {

	@Autowired
    AfiliacionReprository afiliacionReprository;

	public long countAllAfiliacions() {
        return afiliacionReprository.count();
    }

	public void deleteAfiliacion(Afiliacion afiliacion) {
        afiliacionReprository.delete(afiliacion);
    }

	public Afiliacion findAfiliacion(AfiliacionPK id) {
        return afiliacionReprository.findOne(id);
    }

	public List<Afiliacion> findAllAfiliacions() {
        return afiliacionReprository.findAll();
    }

	public List<Afiliacion> findAfiliacionEntries(int firstResult, int maxResults) {
        return afiliacionReprository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveAfiliacion(Afiliacion afiliacion) {
        afiliacionReprository.save(afiliacion);
    }

	public Afiliacion updateAfiliacion(Afiliacion afiliacion) {
        return afiliacionReprository.save(afiliacion);
    }
}
