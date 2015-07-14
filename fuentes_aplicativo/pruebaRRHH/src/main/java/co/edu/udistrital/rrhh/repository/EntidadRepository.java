package co.edu.udistrital.rrhh.repository;
import java.util.List;

import co.edu.udistrital.rrhh.domain.Empleado;
import co.edu.udistrital.rrhh.domain.Entidad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RooJpaRepository(domainType = Entidad.class)
public interface EntidadRepository extends JpaSpecificationExecutor<Entidad>, JpaRepository<Entidad, Integer> {
	
	@Query(value ="SELECT e FROM Entidad e WHERE e.entTipo = :tipoParam")
	public List<Entidad> findAllEntidadesByTipo(
			@Param("tipoParam") String tipoParam);
	
	
	
}
