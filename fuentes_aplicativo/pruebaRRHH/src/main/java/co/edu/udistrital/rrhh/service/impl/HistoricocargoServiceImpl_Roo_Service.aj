// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package co.edu.udistrital.rrhh.service.impl;

import co.edu.udistrital.rrhh.domain.Historicocargo;
import co.edu.udistrital.rrhh.repository.HistoricocargoReprository;
import co.edu.udistrital.rrhh.service.impl.HistoricocargoServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect HistoricocargoServiceImpl_Roo_Service {
    
    declare @type: HistoricocargoServiceImpl: @Service;
    
    declare @type: HistoricocargoServiceImpl: @Transactional;
    
    @Autowired
    HistoricocargoReprository HistoricocargoServiceImpl.historicocargoReprository;
    
    public long HistoricocargoServiceImpl.countAllHistoricocargoes() {
        return historicocargoReprository.count();
    }
    
    public void HistoricocargoServiceImpl.deleteHistoricocargo(Historicocargo historicocargo) {
        historicocargoReprository.delete(historicocargo);
    }
    
    public Historicocargo HistoricocargoServiceImpl.findHistoricocargo(Integer id) {
        return historicocargoReprository.findOne(id);
    }
    
    public List<Historicocargo> HistoricocargoServiceImpl.findAllHistoricocargoes() {
        return historicocargoReprository.findAll();
    }
    
    public List<Historicocargo> HistoricocargoServiceImpl.findHistoricocargoEntries(int firstResult, int maxResults) {
        return historicocargoReprository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }
    
    public void HistoricocargoServiceImpl.saveHistoricocargo(Historicocargo historicocargo) {
        historicocargoReprository.save(historicocargo);
    }
    
    public Historicocargo HistoricocargoServiceImpl.updateHistoricocargo(Historicocargo historicocargo) {
        return historicocargoReprository.save(historicocargo);
    }
    
}
