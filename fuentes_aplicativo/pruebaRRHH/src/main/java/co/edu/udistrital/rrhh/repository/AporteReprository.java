package co.edu.udistrital.rrhh.repository;
import co.edu.udistrital.rrhh.domain.Aporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RooJpaRepository(domainType = Aporte.class)
public interface AporteReprository extends JpaSpecificationExecutor<Aporte>, JpaRepository<Aporte, Integer> {
}
