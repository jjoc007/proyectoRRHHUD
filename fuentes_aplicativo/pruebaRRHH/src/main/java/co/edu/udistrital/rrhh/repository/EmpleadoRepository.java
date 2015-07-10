package co.edu.udistrital.rrhh.repository;
import java.util.List;

import co.edu.udistrital.rrhh.domain.Empleado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RooJpaRepository(domainType = Empleado.class)
public interface EmpleadoRepository extends JpaSpecificationExecutor<Empleado>, JpaRepository<Empleado, Integer> {
	@Query(value ="SELECT e FROM Empleado e WHERE e.empEstado = :estado")
	public List<Empleado> findAllEmpleadosAct(
			@Param("estado") String estado);
	}
