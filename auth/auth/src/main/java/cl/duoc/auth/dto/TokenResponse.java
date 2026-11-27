package cl.duoc.auth.dto;

import cl.duoc.auth.model.Rol;

public record TokenResponse(
		String token,
		String correo,
		Rol rol
) {
}
