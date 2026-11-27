package cl.duoc.noticias.controller;

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

import cl.duoc.noticias.dto.NoticiaRequest;
import cl.duoc.noticias.model.Noticia;
import cl.duoc.noticias.service.NoticiaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/noticias")
public class NoticiaController {

	private final NoticiaService noticiaService;

	public NoticiaController(NoticiaService noticiaService) {
		this.noticiaService = noticiaService;
	}

	// Público: 200
	@GetMapping
	public ResponseEntity<List<Noticia>> listar() {
		return ResponseEntity.ok(noticiaService.listar());
	}

	// Público: 200 | 404
	@GetMapping("/{id}")
	public ResponseEntity<Noticia> obtener(@PathVariable Long id) {
		return ResponseEntity.ok(noticiaService.obtener(id));
	}

	// Funcionario: 201 | 400 | 401 | 403
	@PostMapping
	@PreAuthorize("hasRole('FUNCIONARIO')")
	public ResponseEntity<Noticia> crear(@Valid @RequestBody NoticiaRequest datos) {
		return ResponseEntity.status(HttpStatus.CREATED).body(noticiaService.crear(datos));
	}

	// Funcionario: 200 | 400 | 401 | 403 | 404
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('FUNCIONARIO')")
	public ResponseEntity<Noticia> actualizar(@PathVariable Long id, @Valid @RequestBody NoticiaRequest datos) {
		return ResponseEntity.ok(noticiaService.actualizar(id, datos));
	}

	// Funcionario: 204 | 401 | 403 | 404
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('FUNCIONARIO')")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		noticiaService.eliminar(id);
		return ResponseEntity.noContent().build();
	}
}
