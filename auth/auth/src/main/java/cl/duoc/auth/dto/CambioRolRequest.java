package cl.duoc.auth.dto;

import cl.duoc.auth.model.Rol;
import jakarta.validation.constraints.NotNull;

public record CambioRolRequest(
		@NotNull(message = "El rol es obligatorio")
		Rol rol
) {
}
