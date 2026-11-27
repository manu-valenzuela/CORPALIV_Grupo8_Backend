package cl.duoc.auth.exception;

// 401: correo o contraseña incorrectos (solo en auth)
public class CredencialesInvalidasException extends RuntimeException {

	public CredencialesInvalidasException() {
		super("Correo o contraseña incorrectos");
	}
}
