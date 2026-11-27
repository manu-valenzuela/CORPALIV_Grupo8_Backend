package cl.duoc.auth.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cuenta")
@Getter
@Setter
@NoArgsConstructor
public class Cuenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, length = 100)
	private String correo;

	@Column(nullable = false, length = 100)
	private String contrasena;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private Rol rol;

	public Cuenta(String correo, String contrasena, Rol rol) {
		this.correo = correo;
		this.contrasena = contrasena;
		this.rol = rol;
	}
}
