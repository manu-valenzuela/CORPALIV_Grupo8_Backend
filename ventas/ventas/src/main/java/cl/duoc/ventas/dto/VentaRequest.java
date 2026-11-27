package cl.duoc.ventas.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// Lo que envía la app al comprar: el resto de los datos los completa el servidor
public record VentaRequest(
		@NotNull(message = "El producto es obligatorio")
		Long productoId,

		@NotNull(message = "La cantidad es obligatoria")
		@Min(value = 1, message = "La cantidad debe ser al menos 1")
		Integer cantidad
) {
}
