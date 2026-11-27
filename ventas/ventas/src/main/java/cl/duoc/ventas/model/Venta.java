package cl.duoc.ventas.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Guarda nombre y precio del producto al momento de la compra,
// así el historial no cambia si el producto se edita o se elimina
@Entity
@Table(name = "venta")
@Getter
@Setter
@NoArgsConstructor
public class Venta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "producto_id", nullable = false)
	private Long productoId;

	@Column(name = "nombre_producto", nullable = false, length = 100)
	private String nombreProducto;

	@Column(nullable = false)
	private Integer cantidad;

	@Column(name = "precio_unitario", nullable = false)
	private Integer precioUnitario;

	// Se obtiene del token de quien compra
	@Column(name = "correo_comprador", nullable = false, length = 100)
	private String correoComprador;

	// La asigna el servidor al registrar la venta
	@Column(nullable = false)
	private LocalDateTime fecha;
}
