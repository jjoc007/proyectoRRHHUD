package co.edu.udistrital.rrhh.repository;
import java.util.List;

import co.edu.udistrital.rrhh.domain.Provision;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RooJpaRepository(domainType = Provision.class)
public interface ProvisionRepository extends JpaRepository<Provision, Integer>, JpaSpecificationExecutor<Provision> {

	@Query(value ="SELECT p FROM Provision p WHERE p.proEmpleado = :empleado AND p.proConcepto = :concepto AND p.proEstado = :estado ")
			public List<Provision> findProvisiones(
					@Param("empleado") Integer empleado,
					@Param("concepto") Integer concepto,
					@Param("estado") String estado);
	
}
