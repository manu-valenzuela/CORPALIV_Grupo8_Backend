package cl.duoc.donaciones.model;

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

// Donación express: no requiere estar registrado
@Entity
@Table(name = "donacion")
@Getter
@Setter
@NoArgsConstructor
public class Donacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String nombre;

	@Column(nullable = false, length = 100)
	private String correo;

	@Column(nullable = false)
	private Integer monto;

	// La asigna el servidor al registrar la donación
	@Column(nullable = false)
	private LocalDateTime fecha;
}
