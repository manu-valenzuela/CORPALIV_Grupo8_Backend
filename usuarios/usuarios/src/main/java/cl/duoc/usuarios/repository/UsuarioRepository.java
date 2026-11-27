package cl.duoc.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.usuarios.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	boolean existsByRut(String rut);

	boolean existsByCorreo(String correo);

	// Para editar: el RUT o el correo no pueden pertenecer a otro usuario
	boolean existsByRutAndIdNot(String rut, Long id);
}
