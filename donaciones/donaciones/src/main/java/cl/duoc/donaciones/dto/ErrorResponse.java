package cl.duoc.donaciones.dto;

// Cuerpo común de todas las respuestas de error
public record ErrorResponse(
		int estado,
		String mensaje
) {
}
