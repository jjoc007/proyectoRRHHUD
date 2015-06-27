package co.edu.udistrital.rrhh.repository;
import co.edu.udistrital.rrhh.domain.Concepto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RooJpaRepository(domainType = Concepto.class)
public interface ConceptoReprository extends JpaSpecificationExecutor<Concepto>, JpaRepository<Concepto, Integer> {
}
