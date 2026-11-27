package cl.duoc.usuarios.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.usuarios.dto.UsuarioRequest;
import cl.duoc.usuarios.model.Usuario;
import cl.duoc.usuarios.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	// Funcionario: 200 | 401 | 403
	@GetMapping
	@PreAuthorize("hasRole('FUNCIONARIO')")
	public ResponseEntity<List<Usuario>> listar() {
		return ResponseEntity.ok(usuarioService.listar());
	}

	// Con sesión: 200 | 401 | 404
	@GetMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Usuario> obtener(@PathVariable Long id) {
		return ResponseEntity.ok(usuarioService.obtener(id));
	}

	// Con sesión (se llama justo después de registrarse en auth): 201 | 400 | 401 | 409
	@PostMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Usuario> crear(@Valid @RequestBody UsuarioRequest datos) {
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crear(datos));
	}

	// Con sesión: 200 | 400 | 401 | 404 | 409
	@PutMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @Valid @RequestBody UsuarioRequest datos) {
		return ResponseEntity.ok(usuarioService.actualizar(id, datos));
	}

	// Funcionario: 204 | 401 | 403 | 404
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('FUNCIONARIO')")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		usuarioService.eliminar(id);
		return ResponseEntity.noContent().build();
	}
}
