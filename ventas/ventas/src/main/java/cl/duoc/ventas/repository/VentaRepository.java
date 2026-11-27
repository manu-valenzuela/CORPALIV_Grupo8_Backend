package cl.duoc.ventas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.ventas.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long> {

	// Las más recientes primero
	List<Venta> findAllByOrderByFechaDesc();
}
