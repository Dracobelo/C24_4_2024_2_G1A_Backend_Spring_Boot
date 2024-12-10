package com.tecsup.fruitcommerce.paq_services.paq_impl;

import com.tecsup.fruitcommerce.paq_models.Carrito;
import com.tecsup.fruitcommerce.paq_models.CarritoProductos;
import com.tecsup.fruitcommerce.paq_repositories.CarritoProductosRepository;
import com.tecsup.fruitcommerce.paq_services.CarritoProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoProductosServiceImpl implements CarritoProductosService {

    @Autowired
    private CarritoProductosRepository carritoProductosRepository;

    @Override
    public List<CarritoProductos> obtenerProductosPorCarrito(Carrito carrito) {
        if (carrito == null) {
            throw new IllegalArgumentException("Carrito no puede ser nulo");
        }
        return carritoProductosRepository.findByCarrito(carrito);
    }

    @Override
    public CarritoProductos agregarProductoAlCarrito(CarritoProductos carritoProducto) {
        // Verificar si el producto ya existe en el carrito
        Optional<CarritoProductos> existente = carritoProductosRepository
                .findByCarritoIdCarritoAndProductoIdProducto(
                        carritoProducto.getCarrito().getIdCarrito(),
                        carritoProducto.getProducto().getIdProducto()
                );

        if (existente.isPresent()) {
            // Incrementar la cantidad si ya existe
            CarritoProductos existenteProducto = existente.get();
            double nuevaCantidad = Double.parseDouble(existenteProducto.getCantidad()) + 1;
            existenteProducto.setCantidad(String.valueOf(nuevaCantidad));
            return carritoProductosRepository.save(existenteProducto);
        }

        // Agregar el producto si no existe
        return carritoProductosRepository.save(carritoProducto);
    }

    @Override
    public void eliminarProductoDelCarrito(Integer id) {
        // Verificar si el producto existe antes de eliminarlo
        if (!carritoProductosRepository.existsById(id)) {
            throw new IllegalArgumentException("Producto no encontrado en el carrito");
        }
        carritoProductosRepository.deleteById(id);
    }

    @Override
    public CarritoProductos aumentarCantidad(Integer idCarritoProducto) {
        CarritoProductos carritoProducto = carritoProductosRepository.findById(idCarritoProducto)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado en el carrito"));

        double nuevaCantidad = Double.parseDouble(carritoProducto.getCantidad()) + 1;
        carritoProducto.setCantidad(String.valueOf(nuevaCantidad));
        return carritoProductosRepository.save(carritoProducto);
    }

    @Override
    public CarritoProductos reducirCantidad(Integer idCarritoProducto) {
        CarritoProductos carritoProducto = carritoProductosRepository.findById(idCarritoProducto)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado en el carrito"));

        double nuevaCantidad = Double.parseDouble(carritoProducto.getCantidad()) - 1;

        if (nuevaCantidad <= 0) {
            carritoProductosRepository.deleteById(idCarritoProducto);
            return null;
        } else {
            carritoProducto.setCantidad(String.valueOf(nuevaCantidad));
            return carritoProductosRepository.save(carritoProducto);
        }
    }

    @Override
    public CarritoProductos actualizarCantidad(Integer idCarritoProducto, double cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0.");
        }

        CarritoProductos carritoProducto = carritoProductosRepository.findById(idCarritoProducto)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado en el carrito"));

        carritoProducto.setCantidad(String.valueOf(cantidad));
        return carritoProductosRepository.save(carritoProducto);
    }

    @Override
    public double calcularTotal(Carrito carrito) {
        if (carrito == null) {
            throw new IllegalArgumentException("Carrito no puede ser nulo");
        }

        List<CarritoProductos> productos = carritoProductosRepository.findByCarrito(carrito);

        return productos.stream()
                .mapToDouble(p -> {
                    Double precio = p.getProducto().getPrecio();
                    double cantidad = (p.getCantidad() != null && !p.getCantidad().isEmpty())
                            ? Double.parseDouble(p.getCantidad())
                            : 0.0;
                    return precio != null ? precio * cantidad : 0.0;
                })
                .sum();
    }

    @Override
    public Optional<CarritoProductos> findById(Integer id) {
        return carritoProductosRepository.findById(id);
    }

    @Override
    public Optional<CarritoProductos> obtenerPorCarritoYProducto(Integer carritoId, Integer productoId) {
        return carritoProductosRepository.findByCarritoIdCarritoAndProductoIdProducto(carritoId, productoId);
    }
}
