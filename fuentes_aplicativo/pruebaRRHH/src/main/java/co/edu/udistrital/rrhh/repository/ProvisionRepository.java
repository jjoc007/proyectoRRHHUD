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

	@Query(value ="SELECT COUNT(p.pro_periodo) FROM  provision p"+
			"WHERE p.pro_empleado = :empleado "+
			"AND   p.pro_concepto = :concepto ", nativeQuery = true)
			public int findProviVacaciones(
					@Param("empleado") int empleado,
					@Param("concepto") int concepto);
   
	@Query(value ="UPDATE  provision p"+
			"SET   p.pro_estado = :estado "+
			"WHERE p.pro_empleado = :empleado "+
			"AND   p.pro_concepto = :concepto ", nativeQuery = true)
			public void updateProviVacaciones(
					@Param("empleado") int empleado,
					@Param("concepto") int concepto,
					@Param("estado") String estado);
}
