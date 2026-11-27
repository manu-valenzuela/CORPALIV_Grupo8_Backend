package cl.duoc.ventas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.ventas.dto.VentaRequest;
import cl.duoc.ventas.model.Venta;
import cl.duoc.ventas.service.VentaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

	private final VentaService ventaService;

	public VentaController(VentaService ventaService) {
		this.ventaService = ventaService;
	}

	// Con sesión (comprar pide iniciar sesión): 201 | 400 | 401 | 404 producto inexistente
	@PostMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Venta> registrar(@Valid @RequestBody VentaRequest datos, Authentication autenticacion) {
		// getName() devuelve el correo guardado en el token
		Venta venta = ventaService.registrar(datos, autenticacion.getName());
		return ResponseEntity.status(HttpStatus.CREATED).body(venta);
	}

	// Funcionario: 200 | 401 | 403
	@GetMapping
	@PreAuthorize("hasRole('FUNCIONARIO')")
	public ResponseEntity<List<Venta>> listar() {
		return ResponseEntity.ok(ventaService.listar());
	}
}
