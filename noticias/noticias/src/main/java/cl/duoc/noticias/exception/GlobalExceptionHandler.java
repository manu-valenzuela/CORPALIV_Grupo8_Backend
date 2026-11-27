package cl.duoc.noticias.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cl.duoc.noticias.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// 400: falló una validación (@Valid)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> manejarValidacion(MethodArgumentNotValidException e) {
		String mensaje = e.getBindingResult().getFieldErrors().isEmpty()
				? "Datos inválidos"
				: e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
		return responder(HttpStatus.BAD_REQUEST, mensaje);
	}

	// 400: el cuerpo de la solicitud no es un JSON válido
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> manejarCuerpoInvalido(HttpMessageNotReadableException e) {
		return responder(HttpStatus.BAD_REQUEST, "El cuerpo de la solicitud no es válido");
	}

	// 404
	@ExceptionHandler(RecursoNoEncontradoException.class)
	public ResponseEntity<ErrorResponse> manejarNoEncontrado(RecursoNoEncontradoException e) {
		return responder(HttpStatus.NOT_FOUND, e.getMessage());
	}

	// 409
	@ExceptionHandler(ConflictoException.class)
	public ResponseEntity<ErrorResponse> manejarConflicto(ConflictoException e) {
		return responder(HttpStatus.CONFLICT, e.getMessage());
	}

	// 401 / 403: se relanza para que responda Spring Security (ver SecurityConfig)
	@ExceptionHandler(AccessDeniedException.class)
	public void manejarAccesoDenegado(AccessDeniedException e) {
		throw e;
	}

	// 500: cualquier otro error no previsto
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> manejarGeneral(Exception e) {
		return responder(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor");
	}

	private ResponseEntity<ErrorResponse> responder(HttpStatus estado, String mensaje) {
		return ResponseEntity.status(estado).body(new ErrorResponse(estado.value(), mensaje));
	}
}
