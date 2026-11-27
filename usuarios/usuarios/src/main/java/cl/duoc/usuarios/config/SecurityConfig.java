package cl.duoc.usuarios.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cl.duoc.usuarios.security.JwtFilter;
import jakarta.servlet.http.HttpServletResponse;

// Igual en todos los microservicios: los permisos por rol se definen
// en cada controlador con @PreAuthorize
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	private final JwtFilter jwtFilter;

	public SecurityConfig(JwtFilter jwtFilter) {
		this.jwtFilter = jwtFilter;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(csrf -> csrf.disable())
				.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
				.exceptionHandling(e -> e
						// 401: falta el token o no es válido
						.authenticationEntryPoint((req, res, ex) ->
								responderError(res, HttpStatus.UNAUTHORIZED, "Debe iniciar sesión"))
						// 403: el rol no tiene permiso
						.accessDeniedHandler((req, res, ex) ->
								responderError(res, HttpStatus.FORBIDDEN, "No tiene permiso para esta acción")))
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	private static void responderError(HttpServletResponse res, HttpStatus estado, String mensaje)
			throws IOException {
		res.setStatus(estado.value());
		res.setContentType("application/json;charset=UTF-8");
		res.getWriter().write("{\"estado\":" + estado.value() + ",\"mensaje\":\"" + mensaje + "\"}");
	}
}
