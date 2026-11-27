package cl.duoc.noticias.model;

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

@Entity
@Table(name = "noticia")
@Getter
@Setter
@NoArgsConstructor
public class Noticia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 150)
	private String titulo;

	@Column(nullable = false, length = 500)
	private String resumen;

	@Column(name = "url_imagen", length = 255)
	private String urlImagen;

	// Fecha de publicación: la asigna el servidor al crear la noticia
	@Column(nullable = false)
	private LocalDate fecha;
}
