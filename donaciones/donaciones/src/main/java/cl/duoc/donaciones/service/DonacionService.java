package cl.duoc.donaciones.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import cl.duoc.donaciones.dto.DonacionRequest;
import cl.duoc.donaciones.exception.RecursoNoEncontradoException;
import cl.duoc.donaciones.model.Donacion;
import cl.duoc.donaciones.repository.DonacionRepository;

@Service
public class DonacionService {

	private final DonacionRepository donacionRepository;

	public DonacionService(DonacionRepository donacionRepository) {
		this.donacionRepository = donacionRepository;
	}

	public List<Donacion> listar() {
		return donacionRepository.findAllByOrderByFechaDesc();
	}

	public Donacion registrar(DonacionRequest datos) {
		Donacion donacion = new Donacion();
		donacion.setNombre(datos.nombre());
		donacion.setCorreo(datos.correo());
		donacion.setMonto(datos.monto());
		donacion.setFecha(LocalDateTime.now());
		return donacionRepository.save(donacion);
	}

	public void eliminar(Long id) {
		Donacion donacion = donacionRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Donación no encontrada"));
		donacionRepository.delete(donacion);
	}
}
