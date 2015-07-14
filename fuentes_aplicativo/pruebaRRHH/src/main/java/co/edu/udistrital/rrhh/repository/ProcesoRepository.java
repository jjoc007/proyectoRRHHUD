package co.edu.udistrital.rrhh.repository;

import java.util.List;

import co.edu.udistrital.rrhh.domain.Proceso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RooJpaRepository(domainType = Proceso.class)
public interface ProcesoRepository extends JpaRepository<Proceso, Integer>, JpaSpecificationExecutor<Proceso> {

}
