package co.edu.udistrital.rrhh.repository;
import java.util.List;

import co.edu.udistrital.rrhh.domain.Concepto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RooJpaRepository(domainType = Concepto.class)
public interface ConceptoRepository extends JpaRepository<Concepto, Integer>, JpaSpecificationExecutor<Concepto> {
		
	@Query(value ="SELECT c FROM Concepto c WHERE c.conEstado = :estado")
	public List<Concepto> findAllConceptosByEstado(
			@Param("estado") String estado);
	
	@Query(value ="SELECT c FROM Concepto c WHERE c.conEstado = :estado AND c.conEliminar <> 'N' AND conTipoPercepcion IN (:tipoPer)")
	public List<Concepto> findAllConceptoLiq(
			@Param("estado") String estado,
			@Param("tipoPer") List<String> tipoPer);
	
}
