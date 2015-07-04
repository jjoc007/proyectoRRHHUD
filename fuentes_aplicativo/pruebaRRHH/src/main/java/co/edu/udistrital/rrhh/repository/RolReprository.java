package co.edu.udistrital.rrhh.repository;
import co.edu.udistrital.rrhh.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RooJpaRepository(domainType = Rol.class)
public interface RolReprository extends JpaSpecificationExecutor<Rol>, JpaRepository<Rol, Integer> {
}
