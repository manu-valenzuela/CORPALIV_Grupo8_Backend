package cl.duoc.usuarios.dto;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

// Valida que el RUT tenga un dígito verificador correcto (ej: 12.345.678-5 o 12345678-5)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidadorRut.class)
public @interface RutValido {

	String message() default "El RUT no es válido";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
