package cl.duoc.auth.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cl.duoc.auth.dto.CredencialesRequest;
import cl.duoc.auth.dto.CuentaResponse;
import cl.duoc.auth.dto.TokenResponse;
import cl.duoc.auth.exception.ConflictoException;
import cl.duoc.auth.exception.CredencialesInvalidasException;
import cl.duoc.auth.exception.RecursoNoEncontradoException;
import cl.duoc.auth.model.Cuenta;
import cl.duoc.auth.model.Rol;
import cl.duoc.auth.repository.CuentaRepository;
import cl.duoc.auth.security.JwtUtil;

@Service
public class AuthService {

	private final CuentaRepository cuentaRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	public AuthService(CuentaRepository cuentaRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
		this.cuentaRepository = cuentaRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
	}

	// Quien se registra desde la app siempre queda como SOCIO
	public TokenResponse registrar(CredencialesRequest datos) {
		if (cuentaRepository.existsByCorreo(datos.correo())) {
			throw new ConflictoException("El correo ya está registrado");
		}
		Cuenta cuenta = cuentaRepository.save(
				new Cuenta(datos.correo(), passwordEncoder.encode(datos.contrasena()), Rol.SOCIO));
		return crearToken(cuenta);
	}

	public TokenResponse iniciarSesion(CredencialesRequest datos) {
		Cuenta cuenta = cuentaRepository.findByCorreo(datos.correo())
				.orElseThrow(CredencialesInvalidasException::new);
		if (!passwordEncoder.matches(datos.contrasena(), cuenta.getContrasena())) {
			throw new CredencialesInvalidasException();
		}
		return crearToken(cuenta);
	}

	public List<CuentaResponse> listarCuentas() {
		return cuentaRepository.findAll().stream().map(CuentaResponse::desde).toList();
	}

	public CuentaResponse cambiarRol(Long id, Rol nuevoRol) {
		Cuenta cuenta = cuentaRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Cuenta no encontrada"));
		cuenta.setRol(nuevoRol);
		return CuentaResponse.desde(cuentaRepository.save(cuenta));
	}

	private TokenResponse crearToken(Cuenta cuenta) {
		String token = jwtUtil.generarToken(cuenta.getCorreo(), cuenta.getRol().name());
		return new TokenResponse(token, cuenta.getCorreo(), cuenta.getRol());
	}
}
