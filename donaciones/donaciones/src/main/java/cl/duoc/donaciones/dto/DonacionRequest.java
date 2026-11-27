package cl.duoc.donaciones.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Datos de una donación express (el id y la fecha los pone el servidor)
public record DonacionRequest(
		@NotBlank(message = "El nombre es obligatorio")
		@Size(max = 100, message = "El nombre admite hasta 100 caracteres")
		String nombre,

		@NotBlank(message = "El correo es obligatorio")
		@Email(message = "El correo no es válido")
		String correo,

		@NotNull(message = "El monto es obligatorio")
		@Min(value = 1, message = "El monto debe ser mayor a $0")
		@Max(value = 1000000, message = "El monto máximo permitido es $1.000.000")
		Integer monto
) {
}
