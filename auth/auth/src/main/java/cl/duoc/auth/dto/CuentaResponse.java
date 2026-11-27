package cl.duoc.auth.dto;

import cl.duoc.auth.model.Cuenta;
import cl.duoc.auth.model.Rol;

// Datos de una cuenta sin la contraseña
public record CuentaResponse(
		Long id,
		String correo,
		Rol rol
) {
	public static CuentaResponse desde(Cuenta cuenta) {
		return new CuentaResponse(cuenta.getId(), cuenta.getCorreo(), cuenta.getRol());
	}
}
