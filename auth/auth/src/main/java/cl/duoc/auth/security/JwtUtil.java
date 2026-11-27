package cl.duoc.auth.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private final SecretKey clave;
	private final long expiracionMs;

	public JwtUtil(@Value("${jwt.secreto}") String secreto,
				   @Value("${jwt.expiracion-ms}") long expiracionMs) {
		this.clave = Keys.hmacShaKeyFor(secreto.getBytes(StandardCharsets.UTF_8));
		this.expiracionMs = expiracionMs;
	}

	// Solo auth genera tokens
	public String generarToken(String correo, String rol) {
		Date ahora = new Date();
		return Jwts.builder()
				.subject(correo)
				.claim("rol", rol)
				.issuedAt(ahora)
				.expiration(new Date(ahora.getTime() + expiracionMs))
				.signWith(clave)
				.compact();
	}

	// Devuelve los datos del token, o null si es inválido o expiró
	public Claims validarToken(String token) {
		try {
			return Jwts.parser()
					.verifyWith(clave)
					.build()
					.parseSignedClaims(token)
					.getPayload();
		} catch (JwtException | IllegalArgumentException e) {
			return null;
		}
	}
}
