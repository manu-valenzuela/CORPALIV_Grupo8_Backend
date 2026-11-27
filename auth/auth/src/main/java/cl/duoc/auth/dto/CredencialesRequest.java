package cl.duoc.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Se usa tanto para registrarse como para iniciar sesión
public record CredencialesRequest(
		@NotBlank(message = "El correo es obligatorio")
		@Email(message = "El correo no es válido")
		String correo,

		@NotBlank(message = "La contraseña es obligatoria")
		@Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
		String contrasena
) {
}
