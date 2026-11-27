package cl.duoc.ventas.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.duoc.ventas.dto.VentaRequest;
import cl.duoc.ventas.model.Producto;
import cl.duoc.ventas.model.Venta;
import cl.duoc.ventas.repository.VentaRepository;

@Service
public class VentaService {

	private final VentaRepository ventaRepository;
	private final ProductoService productoService;

	public VentaService(VentaRepository ventaRepository, ProductoService productoService) {
		this.ventaRepository = ventaRepository;
		this.productoService = productoService;
	}

	public List<Venta> listar() {
		return ventaRepository.findAllByOrderByFechaDesc();
	}

	// El correo del comprador viene del token; el producto debe existir (404) y tener stock (409).
	// @Transactional: si algo falla, no se descuenta el stock ni se guarda la venta
	@Transactional
	public Venta registrar(VentaRequest datos, String correoComprador) {
		Producto producto = productoService.obtener(datos.productoId());
		productoService.descontarStock(producto, datos.cantidad());

		Venta venta = new Venta();
		venta.setProductoId(producto.getId());
		venta.setNombreProducto(producto.getNombre());
		venta.setCantidad(datos.cantidad());
		venta.setPrecioUnitario(producto.getPrecio());
		venta.setCorreoComprador(correoComprador);
		venta.setFecha(LocalDateTime.now());
		return ventaRepository.save(venta);
	}
}
