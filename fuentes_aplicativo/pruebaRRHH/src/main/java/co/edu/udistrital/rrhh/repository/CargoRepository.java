package co.edu.udistrital.rrhh.repository;

import java.util.List;

import co.edu.udistrital.rrhh.domain.Cargo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RooJpaRepository(domainType = Cargo.class)
public interface CargoRepository extends JpaRepository<Cargo, Integer>, JpaSpecificationExecutor<Cargo> {
//public interface CargoRepository extends JpaSpecificationExecutor<Cargo>, JpaRepository<Cargo, Integer> {
	
	@Query(value ="SELECT c FROM Cargo c WHERE c.carEstado = :estado")
	public List<Cargo> findAllCargoAct(
			@Param("estado") String estado);

}
