package co.edu.udistrital.rrhh.repository;
import java.util.Date;
import java.util.List;

import co.edu.udistrital.rrhh.domain.Pago;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RooJpaRepository(domainType = Pago.class)
public interface PagoRepository extends JpaSpecificationExecutor<Pago>, JpaRepository<Pago, Integer> {
	
	@Query(value ="SELECT p FROM Pago p WHERE p.pagoEmpleado.empCedula = :empleado AND p.pagPeriodo = :periodo AND p.pagConcepto.conTipoPercepcion = :tipoPer")
	public List<Pago> findPagos(
			@Param("empleado") Integer empleado,
			@Param("periodo") Date periodo,
			@Param("tipoPer") String tipoPer);
	
	@Query(value ="SELECT max(p.pagPeriodo) FROM Pago p")
	public Date findPeriodoPago();
	
}
