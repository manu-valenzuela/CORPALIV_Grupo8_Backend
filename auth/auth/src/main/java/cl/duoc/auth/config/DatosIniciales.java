package cl.duoc.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import cl.duoc.auth.model.Cuenta;
import cl.duoc.auth.model.Rol;
import cl.duoc.auth.repository.CuentaRepository;

// Solo en auth: crea el primer funcionario si aún no existe,
// para poder asignar roles desde la app
@Component
public class DatosIniciales implements CommandLineRunner {

	private final CuentaRepository cuentaRepository;
	private final PasswordEncoder passwordEncoder;
	private final String correoAdmin;
	private final String contrasenaAdmin;

	public DatosIniciales(CuentaRepository cuentaRepository, PasswordEncoder passwordEncoder,
						  @Value("${admin.correo}") String correoAdmin,
						  @Value("${admin.contrasena}") String contrasenaAdmin) {
		this.cuentaRepository = cuentaRepository;
		this.passwordEncoder = passwordEncoder;
		this.correoAdmin = correoAdmin;
		this.contrasenaAdmin = contrasenaAdmin;
	}

	@Override
	public void run(String... args) {
		if (!cuentaRepository.existsByCorreo(correoAdmin)) {
			cuentaRepository.save(new Cuenta(correoAdmin, passwordEncoder.encode(contrasenaAdmin), Rol.FUNCIONARIO));
		}
	}
}
