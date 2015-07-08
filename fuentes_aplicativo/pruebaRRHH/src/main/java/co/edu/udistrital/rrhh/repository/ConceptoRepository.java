package co.edu.udistrital.rrhh.repository;
import java.math.BigDecimal;
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
	
	@Query("select c from Concepto c where c.conTipoPercepcion = :paramConTipoPer")
	public List<Concepto> findByTipoPer(@Param("paramConTipoPer") String paramConTipoPer);
	
}
