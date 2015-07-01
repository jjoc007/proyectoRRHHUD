package co.edu.udistrital.rrhh.service.impl;
import co.edu.udistrital.rrhh.domain.Usuario;
import co.edu.udistrital.rrhh.repository.UsuarioReprository;
import co.edu.udistrital.rrhh.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
    UsuarioReprository usuarioReprository;

	public long countAllUsuarios() {
        return usuarioReprository.count();
    }

	public void deleteUsuario(Usuario usuario) {
        usuarioReprository.delete(usuario);
    }

	public Usuario findUsuario(Integer id) {
        return usuarioReprository.findOne(id);
    }

	public List<Usuario> findAllUsuarios() {
        return usuarioReprository.findAll();
    }

	public List<Usuario> findUsuarioEntries(int firstResult, int maxResults) {
        return usuarioReprository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveUsuario(Usuario usuario) {
        usuarioReprository.save(usuario);
    }

	public Usuario updateUsuario(Usuario usuario) {
        return usuarioReprository.save(usuario);
    }
}
