package cl.duoc.ventas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.duoc.ventas.dto.ProductoRequest;
import cl.duoc.ventas.exception.ConflictoException;
import cl.duoc.ventas.exception.RecursoNoEncontradoException;
import cl.duoc.ventas.model.Producto;
import cl.duoc.ventas.repository.ProductoRepository;

@Service
public class ProductoService {

	private final ProductoRepository productoRepository;

	public ProductoService(ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}

	public List<Producto> listar() {
		return productoRepository.findAll();
	}

	public Producto obtener(Long id) {
		return productoRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado"));
	}

	public Producto crear(ProductoRequest datos) {
		Producto producto = new Producto();
		copiarDatos(datos, producto);
		return productoRepository.save(producto);
	}

	public Producto actualizar(Long id, ProductoRequest datos) {
		Producto producto = obtener(id);
		copiarDatos(datos, producto);
		return productoRepository.save(producto);
	}

	public void eliminar(Long id) {
		productoRepository.delete(obtener(id));
	}

	// 409 si no hay unidades suficientes
	public void descontarStock(Producto producto, int cantidad) {
		if (producto.getStock() < cantidad) {
			throw new ConflictoException("Stock insuficiente: quedan " + producto.getStock() + " unidades");
		}
		producto.setStock(producto.getStock() - cantidad);
		productoRepository.save(producto);
	}

	private void copiarDatos(ProductoRequest datos, Producto producto) {
		producto.setNombre(datos.nombre());
		producto.setDescripcion(datos.descripcion());
		producto.setPrecio(datos.precio());
		producto.setStock(datos.stock());
		producto.setUrlImagen(datos.urlImagen());
	}
}
