package cl.duoc.ventas.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

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

	// El correo del comprador viene del token; el producto debe existir (si no, 404)
	public Venta registrar(VentaRequest datos, String correoComprador) {
		Producto producto = productoService.obtener(datos.productoId());

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
