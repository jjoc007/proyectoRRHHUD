package co.edu.udistrital.rrhh.repository;
import co.edu.udistrital.rrhh.domain.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RooJpaRepository(domainType = Empleado.class)
public interface EmpleadoRepository extends JpaSpecificationExecutor<Empleado>, JpaRepository<Empleado, Integer> {
}
