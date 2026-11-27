package cl.duoc.usuarios.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Perfil del socio. La contraseña y el rol viven en auth.
@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 50)
	private String nombre;

	@Column(nullable = false, length = 50)
	private String apellido;

	@Column(nullable = false, unique = true, length = 12)
	private String rut;

	@Column(name = "fecha_nacimiento", nullable = false)
	private LocalDate fechaNacimiento;

	@Column(nullable = false, length = 150)
	private String direccion;

	private Double latitud;

	private Double longitud;

	@Column(name = "monto_donacion_mensual", nullable = false)
	private Integer montoDonacionMensual;

	@Column(nullable = false, unique = true, length = 100)
	private String correo;
}
