package co.edu.udistrital.rrhh.repository;
import java.util.List;

import co.edu.udistrital.rrhh.domain.Aporte;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RooJpaRepository(domainType = Aporte.class)
public interface AporteRepository extends JpaSpecificationExecutor<Aporte>, JpaRepository<Aporte, Integer> {
	@Query(value =" select  (valor.sum_valor + valor_emp.sum_emp) Valor, "
			+ " e.ent_nombre entidad, "
			+ " valor.periodo periodo , e.ent_cuenta  cuenta, e.ent_codigo codigo "
			+ " from "
			+ " (select sum(a.apo_valor) sum_valor, a.apo_entidad cod, a.apo_periodo periodo "
			+ " from aporte a  group by a.apo_entidad, a.apo_periodo) valor, "
			+ " (select sum(b.apo_valorEmpresa) sum_emp, b.apo_entidad cod, b.apo_periodo  periodo "
			+ " from aporte b  group by b.apo_entidad,b.apo_periodo) valor_emp, "
			+ " entidad e "
			+ " where valor.cod   = valor_emp.cod "
			+ " and valor_emp.cod = e.ent_codigo "
			+ " and valor.periodo = valor_emp.periodo "
			+ " group by e.ent_codigo, valor.periodo "
			+ " order by valor.periodo desc",nativeQuery=true)
	public List<Object[]> findAllAporte();
	}

