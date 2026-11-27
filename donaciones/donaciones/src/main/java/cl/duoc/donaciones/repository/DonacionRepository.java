package cl.duoc.donaciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.donaciones.model.Donacion;

public interface DonacionRepository extends JpaRepository<Donacion, Long> {

	// Las más recientes primero
	List<Donacion> findAllByOrderByFechaDesc();
}
