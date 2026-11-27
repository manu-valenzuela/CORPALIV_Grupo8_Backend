package cl.duoc.ventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.ventas.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
