package cl.duoc.usuarios.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

// Datos que envía la app para crear o editar un perfil (el id lo pone el servidor)
public record UsuarioRequest(
		@NotBlank(message = "El nombre es obligatorio")
		@Size(max = 50, message = "El nombre admite hasta 50 caracteres")
		String nombre,

		@NotBlank(message = "El apellido es obligatorio")
		@Size(max = 50, message = "El apellido admite hasta 50 caracteres")
		String apellido,

		@NotBlank(message = "El RUT es obligatorio")
		@Size(max = 12, message = "El RUT admite hasta 12 caracteres")
		@RutValido
		String rut,

		// Formato JSON: "AAAA-MM-DD"
		@NotNull(message = "La fecha de nacimiento es obligatoria")
		@Past(message = "La fecha de nacimiento debe ser anterior a hoy")
		LocalDate fechaNacimiento,

		@NotBlank(message = "La dirección es obligatoria")
		@Size(max = 150, message = "La dirección admite hasta 150 caracteres")
		String direccion,

		Double latitud,

		Double longitud,

		@NotNull(message = "El monto de donación mensual es obligatorio")
		@Min(value = 1000, message = "El monto mínimo es $1.000")
		Integer montoDonacionMensual,

		@NotBlank(message = "El correo es obligatorio")
		@Email(message = "El correo no es válido")
		String correo
) {
}
