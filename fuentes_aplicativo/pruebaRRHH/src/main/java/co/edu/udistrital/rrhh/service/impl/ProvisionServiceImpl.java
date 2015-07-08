package co.edu.udistrital.rrhh.service.impl;
import co.edu.udistrital.rrhh.domain.Provision;
import co.edu.udistrital.rrhh.repository.ProvisionRepository;
import co.edu.udistrital.rrhh.service.ProvisionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProvisionServiceImpl implements ProvisionService {

	@Autowired
    ProvisionRepository provisionReprository;

	public long countAllProvisions() {
        return provisionReprository.count();
    }

	public void deleteProvision(Provision provision) {
        provisionReprository.delete(provision);
    }

	public Provision findProvision(Integer id) {
        return provisionReprository.findOne(id);
    }

	public List<Provision> findAllProvisions() {
        return provisionReprository.findAll();
    }

	public List<Provision> findProvisionEntries(int firstResult, int maxResults) {
        return provisionReprository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveProvision(Provision provision) {
        provisionReprository.save(provision);
    }

	public Provision updateProvision(Provision provision) {
        return provisionReprository.save(provision);
    }
}
