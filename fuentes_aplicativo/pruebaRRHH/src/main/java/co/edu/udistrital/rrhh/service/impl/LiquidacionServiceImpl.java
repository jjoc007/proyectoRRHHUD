package co.edu.udistrital.rrhh.service.impl;
import co.edu.udistrital.rrhh.domain.Empleado;
import co.edu.udistrital.rrhh.repository.EmpleadoRepository;
import co.edu.udistrital.rrhh.service.LiquidacionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LiquidacionServiceImpl implements LiquidacionService {

	@Autowired
    EmpleadoRepository empleadoReprository;
/*
	public long countAllEmpleadoes() {
        return empleadoReprository.count();
    }

	public void deleteEmpleado(Empleado empleado) {
        empleadoReprository.delete(empleado);
    }

	public Empleado findEmpleado(Integer id) {
        return empleadoReprository.findOne(id);
    }
*/
	public List<Empleado> findAllEmpleados() {
        return empleadoReprository.findAll();
    }
/*
	public List<Empleado> findEmpleadoEntries(int firstResult, int maxResults) {
        return empleadoReprository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveEmpleado(Empleado empleado) {
        empleadoReprository.save(empleado);
    }

	public Empleado updateEmpleado(Empleado empleado) {
        return empleadoReprository.save(empleado);
    }*/
}
