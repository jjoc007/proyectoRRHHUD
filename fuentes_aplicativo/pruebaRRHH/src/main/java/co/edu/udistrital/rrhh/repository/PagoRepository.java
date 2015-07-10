package co.edu.udistrital.rrhh.repository;
import java.util.Date;
import java.util.List;

import co.edu.udistrital.rrhh.domain.Pago;
import co.edu.udistrital.rrhh.domain.Provision;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RooJpaRepository(domainType = Pago.class)
public interface PagoRepository extends JpaSpecificationExecutor<Pago>, JpaRepository<Pago, Integer> {
	
	@Query(value ="SELECT p FROM Pago p WHERE p.pagoEmpleado.empCedula = :empleadoParam AND p.pagPeriodo = :periodoParam AND p.pagConcepto.conTipoPercepcion = :tipoPerParam")
	public List<Pago> findPagosDeduccion(
			@Param("empleadoParam") Integer empleadoParam,
			@Param("periodoParam") Date periodoParam,
			@Param("tipoPerParam") String tipoPerParam);

}
