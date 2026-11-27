package cl.duoc.noticias.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.noticias.model.Noticia;

public interface NoticiaRepository extends JpaRepository<Noticia, Long> {

	// Las más recientes primero
	List<Noticia> findAllByOrderByFechaDescIdDesc();
}
