package co.edu.udistrital.rrhh.repository;
import java.util.List;

import co.edu.udistrital.rrhh.domain.Cargo;
import co.edu.udistrital.rrhh.domain.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RooJpaRepository(domainType = Usuario.class)
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>, JpaSpecificationExecutor<Usuario> {

	@Query(value ="SELECT u.* FROM  usuario u WHERE 1=1 "+
	"AND u.usu_usuario = :user "+
	"AND u.usu_clave = :pass ", nativeQuery = true)
	public List<Object> findUsuarioByUserAndPassword(
			@Param("user") String usuario,
			@Param("pass") String password);
	
	@Query(value ="SELECT c FROM Usuario c WHERE c.usuEstado = 'A'")
	public List<Usuario> findAllUsuariosActivos();
	
}
