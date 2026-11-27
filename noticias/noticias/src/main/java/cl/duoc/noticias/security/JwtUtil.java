package cl.duoc.noticias.security;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

// Valida los tokens emitidos por auth (este servicio no genera tokens)
@Component
public class JwtUtil {

	private final SecretKey clave;

	public JwtUtil(@Value("${jwt.secreto}") String secreto) {
		this.clave = Keys.hmacShaKeyFor(secreto.getBytes(StandardCharsets.UTF_8));
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
