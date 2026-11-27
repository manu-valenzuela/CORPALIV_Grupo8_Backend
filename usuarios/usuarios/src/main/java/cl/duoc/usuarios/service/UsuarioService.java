package cl.duoc.usuarios.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.duoc.usuarios.dto.UsuarioRequest;
import cl.duoc.usuarios.exception.ConflictoException;
import cl.duoc.usuarios.exception.RecursoNoEncontradoException;
import cl.duoc.usuarios.model.Usuario;
import cl.duoc.usuarios.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}

	public Usuario obtener(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado"));
	}

	public Usuario crear(UsuarioRequest datos) {
		if (usuarioRepository.existsByRut(datos.rut())) {
			throw new ConflictoException("El RUT ya está registrado");
		}
		if (usuarioRepository.existsByCorreo(datos.correo())) {
			throw new ConflictoException("El correo ya está registrado");
		}
		Usuario usuario = new Usuario();
		copiarDatos(datos, usuario);
		return usuarioRepository.save(usuario);
	}

	public Usuario actualizar(Long id, UsuarioRequest datos) {
		Usuario usuario = obtener(id);
		if (usuarioRepository.existsByRutAndIdNot(datos.rut(), id)) {
			throw new ConflictoException("El RUT ya está registrado");
		}
		if (usuarioRepository.existsByCorreoAndIdNot(datos.correo(), id)) {
			throw new ConflictoException("El correo ya está registrado");
		}
		copiarDatos(datos, usuario);
		return usuarioRepository.save(usuario);
	}

	public void eliminar(Long id) {
		usuarioRepository.delete(obtener(id));
	}

	private void copiarDatos(UsuarioRequest datos, Usuario usuario) {
		usuario.setNombre(datos.nombre());
		usuario.setApellido(datos.apellido());
		usuario.setRut(datos.rut());
		usuario.setFechaNacimiento(datos.fechaNacimiento());
		usuario.setDireccion(datos.direccion());
		usuario.setLatitud(datos.latitud());
		usuario.setLongitud(datos.longitud());
		usuario.setMontoDonacionMensual(datos.montoDonacionMensual());
		usuario.setCorreo(datos.correo());
	}
}
