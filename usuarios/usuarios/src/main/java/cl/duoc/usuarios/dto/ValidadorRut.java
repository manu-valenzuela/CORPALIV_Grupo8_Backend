package cl.duoc.usuarios.dto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// Mismo algoritmo (módulo 11) que usa la app para validar el RUT
public class ValidadorRut implements ConstraintValidator<RutValido, String> {

	@Override
	public boolean isValid(String rut, ConstraintValidatorContext contexto) {
		if (rut == null || rut.isBlank()) {
			return true; // de eso se encarga @NotBlank
		}
		String limpio = rut.replace(".", "").replace("-", "").toUpperCase();
		if (limpio.length() < 8) {
			return false;
		}
		String cuerpo = limpio.substring(0, limpio.length() - 1);
		char dv = limpio.charAt(limpio.length() - 1);

		int suma = 0;
		int multiplicador = 2;
		for (int i = cuerpo.length() - 1; i >= 0; i--) {
			char c = cuerpo.charAt(i);
			if (!Character.isDigit(c)) {
				return false;
			}
			suma += Character.getNumericValue(c) * multiplicador;
			multiplicador = multiplicador < 7 ? multiplicador + 1 : 2;
		}

		int resto = 11 - (suma % 11);
		char dvEsperado = resto == 11 ? '0' : resto == 10 ? 'K' : (char) ('0' + resto);
		return dv == dvEsperado;
	}
}
