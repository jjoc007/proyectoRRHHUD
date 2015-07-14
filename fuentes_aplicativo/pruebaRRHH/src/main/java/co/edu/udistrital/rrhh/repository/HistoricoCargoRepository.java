package co.edu.udistrital.rrhh.repository;
import java.util.List;

import co.edu.udistrital.rrhh.domain.Historicocargo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RooJpaRepository(domainType = Historicocargo.class)
public interface HistoricoCargoRepository extends JpaRepository<Historicocargo, Integer>, JpaSpecificationExecutor<Historicocargo> {
	@Query(value ="SELECT c FROM Historicocargo c WHERE c.hisCargo.carCogigo = :codigoCargoParam and c.hisEstado = :estadoParam")
	public List<Historicocargo> findHistoricoCargosActivosByCargo(
			@Param("codigoCargoParam") Integer codigoCargoParam,
			@Param("estadoParam") String estadoParam
			);
	
}


