package cl.duoc.donaciones.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.donaciones.dto.DonacionRequest;
import cl.duoc.donaciones.model.Donacion;
import cl.duoc.donaciones.service.DonacionService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/donaciones")
public class DonacionController {

	private final DonacionService donacionService;

	public DonacionController(DonacionService donacionService) {
		this.donacionService = donacionService;
	}

	// Público (donación express sin registrarse): 201 | 400
	@PostMapping
	public ResponseEntity<Donacion> registrar(@Valid @RequestBody DonacionRequest datos) {
		return ResponseEntity.status(HttpStatus.CREATED).body(donacionService.registrar(datos));
	}

	// Funcionario: 200 | 401 | 403
	@GetMapping
	@PreAuthorize("hasRole('FUNCIONARIO')")
	public ResponseEntity<List<Donacion>> listar() {
		return ResponseEntity.ok(donacionService.listar());
	}

	// Funcionario: 204 | 401 | 403 | 404
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('FUNCIONARIO')")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		donacionService.eliminar(id);
		return ResponseEntity.noContent().build();
	}
}
