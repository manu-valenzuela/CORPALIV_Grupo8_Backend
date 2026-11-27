package cl.duoc.noticias.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Datos que envía la app para crear o editar una noticia (el id y la fecha los pone el servidor)
public record NoticiaRequest(
		@NotBlank(message = "El título es obligatorio")
		@Size(max = 150, message = "El título admite hasta 150 caracteres")
		String titulo,

		@NotBlank(message = "El resumen es obligatorio")
		@Size(max = 500, message = "El resumen admite hasta 500 caracteres")
		String resumen,

		@Size(max = 255, message = "La URL de la imagen admite hasta 255 caracteres")
		String urlImagen
) {
}
