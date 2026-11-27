package cl.duoc.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.auth.model.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

	Optional<Cuenta> findByCorreo(String correo);

	boolean existsByCorreo(String correo);
}
