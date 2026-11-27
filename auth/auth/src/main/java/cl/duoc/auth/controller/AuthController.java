package cl.duoc.auth.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.auth.dto.CambioRolRequest;
import cl.duoc.auth.dto.CredencialesRequest;
import cl.duoc.auth.dto.CuentaResponse;
import cl.duoc.auth.dto.TokenResponse;
import cl.duoc.auth.service.AuthService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	// 201 + token | 400 datos inválidos | 409 correo repetido
	@PostMapping("/registro")
	public ResponseEntity<TokenResponse> registrar(@Valid @RequestBody CredencialesRequest datos) {
		return ResponseEntity.status(HttpStatus.CREATED).body(authService.registrar(datos));
	}

	// 200 + token | 400 datos inválidos | 401 credenciales incorrectas
	@PostMapping("/login")
	public ResponseEntity<TokenResponse> iniciarSesion(@Valid @RequestBody CredencialesRequest datos) {
		return ResponseEntity.ok(authService.iniciarSesion(datos));
	}

	// 200 | 401 sin token | 403 no es funcionario
	@GetMapping("/cuentas")
	@PreAuthorize("hasRole('FUNCIONARIO')")
	public ResponseEntity<List<CuentaResponse>> listarCuentas() {
		return ResponseEntity.ok(authService.listarCuentas());
	}

	// 200 | 400 | 401 | 403 | 404 cuenta inexistente
	@PatchMapping("/cuentas/{id}/rol")
	@PreAuthorize("hasRole('FUNCIONARIO')")
	public ResponseEntity<CuentaResponse> cambiarRol(@PathVariable Long id,
													 @Valid @RequestBody CambioRolRequest datos) {
		return ResponseEntity.ok(authService.cambiarRol(id, datos.rol()));
	}
}
