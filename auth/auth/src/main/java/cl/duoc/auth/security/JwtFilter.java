package cl.duoc.auth.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Lee el header "Authorization: Bearer <token>" y, si es válido, identifica al usuario
@Component
public class JwtFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;

	public JwtFilter(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
									FilterChain filterChain) throws ServletException, IOException {
		String header = request.getHeader("Authorization");

		if (header != null && header.startsWith("Bearer ")) {
			Claims datos = jwtUtil.validarToken(header.substring(7));
			if (datos != null) {
				String rol = datos.get("rol", String.class);
				var autenticacion = new UsernamePasswordAuthenticationToken(
						datos.getSubject(), null, List.of(new SimpleGrantedAuthority("ROLE_" + rol)));
				SecurityContextHolder.getContext().setAuthentication(autenticacion);
			}
		}

		filterChain.doFilter(request, response);
	}
}
