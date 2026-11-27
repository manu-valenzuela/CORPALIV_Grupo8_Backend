package cl.duoc.donaciones.exception;

// 409: se intenta repetir un dato único (por ejemplo, un correo ya registrado)
public class ConflictoException extends RuntimeException {

	public ConflictoException(String mensaje) {
		super(mensaje);
	}
}
