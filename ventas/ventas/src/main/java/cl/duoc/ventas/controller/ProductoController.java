package cl.duoc.ventas.controller;

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

import cl.duoc.ventas.dto.ProductoRequest;
import cl.duoc.ventas.model.Producto;
import cl.duoc.ventas.service.ProductoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

	private final ProductoService productoService;

	public ProductoController(ProductoService productoService) {
		this.productoService = productoService;
	}

	// Público (navegar la tienda sin registrarse): 200
	@GetMapping
	public ResponseEntity<List<Producto>> listar() {
		return ResponseEntity.ok(productoService.listar());
	}

	// Público: 200 | 404
	@GetMapping("/{id}")
	public ResponseEntity<Producto> obtener(@PathVariable Long id) {
		return ResponseEntity.ok(productoService.obtener(id));
	}

	// Funcionario: 201 | 400 | 401 | 403
	@PostMapping
	@PreAuthorize("hasRole('FUNCIONARIO')")
	public ResponseEntity<Producto> crear(@Valid @RequestBody ProductoRequest datos) {
		return ResponseEntity.status(HttpStatus.CREATED).body(productoService.crear(datos));
	}

	// Funcionario: 200 | 400 | 401 | 403 | 404
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('FUNCIONARIO')")
	public ResponseEntity<Producto> actualizar(@PathVariable Long id, @Valid @RequestBody ProductoRequest datos) {
		return ResponseEntity.ok(productoService.actualizar(id, datos));
	}

	// Funcionario: 204 | 401 | 403 | 404
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('FUNCIONARIO')")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		productoService.eliminar(id);
		return ResponseEntity.noContent().build();
	}
}
