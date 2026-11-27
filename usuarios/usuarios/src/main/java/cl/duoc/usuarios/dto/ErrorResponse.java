package cl.duoc.usuarios.dto;

// Cuerpo común de todas las respuestas de error
public record ErrorResponse(
		int estado,
		String mensaje
) {
}
