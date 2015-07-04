package co.edu.udistrital.rrhh.repository;
import co.edu.udistrital.rrhh.domain.Afiliacion;
import co.edu.udistrital.rrhh.domain.AfiliacionPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RooJpaRepository(domainType = Afiliacion.class)
public interface AfiliacionRepository extends JpaRepository<Afiliacion, AfiliacionPK>, JpaSpecificationExecutor<Afiliacion> {
}
