package cl.duoc.ventas.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Datos para crear o editar un producto (el id lo pone el servidor)
public record ProductoRequest(
		@NotBlank(message = "El nombre es obligatorio")
		@Size(max = 100, message = "El nombre admite hasta 100 caracteres")
		String nombre,

		@NotBlank(message = "La descripción es obligatoria")
		@Size(max = 255, message = "La descripción admite hasta 255 caracteres")
		String descripcion,

		@NotNull(message = "El precio es obligatorio")
		@Min(value = 1, message = "El precio debe ser mayor a $0")
		Integer precio,

		@Size(max = 255, message = "La URL de la imagen admite hasta 255 caracteres")
		String urlImagen
) {
}
