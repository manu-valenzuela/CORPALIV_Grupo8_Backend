package cl.duoc.noticias.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import cl.duoc.noticias.dto.NoticiaRequest;
import cl.duoc.noticias.exception.RecursoNoEncontradoException;
import cl.duoc.noticias.model.Noticia;
import cl.duoc.noticias.repository.NoticiaRepository;

@Service
public class NoticiaService {

	private final NoticiaRepository noticiaRepository;

	public NoticiaService(NoticiaRepository noticiaRepository) {
		this.noticiaRepository = noticiaRepository;
	}

	public List<Noticia> listar() {
		return noticiaRepository.findAllByOrderByFechaDescIdDesc();
	}

	public Noticia obtener(Long id) {
		return noticiaRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Noticia no encontrada"));
	}

	public Noticia crear(NoticiaRequest datos) {
		Noticia noticia = new Noticia();
		copiarDatos(datos, noticia);
		noticia.setFecha(LocalDate.now());
		return noticiaRepository.save(noticia);
	}

	public Noticia actualizar(Long id, NoticiaRequest datos) {
		Noticia noticia = obtener(id);
		copiarDatos(datos, noticia);
		return noticiaRepository.save(noticia);
	}

	public void eliminar(Long id) {
		noticiaRepository.delete(obtener(id));
	}

	private void copiarDatos(NoticiaRequest datos, Noticia noticia) {
		noticia.setTitulo(datos.titulo());
		noticia.setResumen(datos.resumen());
		noticia.setUrlImagen(datos.urlImagen());
	}
}
