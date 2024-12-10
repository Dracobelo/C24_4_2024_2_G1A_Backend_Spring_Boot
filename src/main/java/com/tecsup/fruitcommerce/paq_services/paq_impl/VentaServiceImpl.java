package com.tecsup.fruitcommerce.paq_services.paq_impl;

import com.tecsup.fruitcommerce.paq_models.Carrito;
import com.tecsup.fruitcommerce.paq_models.CarritoProductos;
import com.tecsup.fruitcommerce.paq_models.DetalleVenta;
import com.tecsup.fruitcommerce.paq_models.Venta;
import com.tecsup.fruitcommerce.paq_repositories.CarritoProductosRepository;
import com.tecsup.fruitcommerce.paq_repositories.DetalleVentaRepository;
import com.tecsup.fruitcommerce.paq_repositories.VentaRepository;
import com.tecsup.fruitcommerce.paq_services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;
    private final DetalleVentaRepository detalleVentaRepository;
    private final CarritoProductosRepository carritoProductosRepository;

    public VentaServiceImpl(VentaRepository ventaRepository,
                            DetalleVentaRepository detalleVentaRepository,
                            CarritoProductosRepository carritoProductosRepository) {
        this.ventaRepository = ventaRepository;
        this.detalleVentaRepository = detalleVentaRepository;
        this.carritoProductosRepository = carritoProductosRepository;
    }

    @Override
    public Venta guardarVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public Optional<Venta> obtenerVentaPorId(Integer id) {
        return ventaRepository.findById(id);
    }

    @Override
    public List<Venta> obtenerTodasLasVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public void eliminarVentaPorId(Integer id) {
        ventaRepository.deleteById(id);
    }

    @Override
    public Venta crearVentaDesdeCarrito(Carrito carrito) {
        List<CarritoProductos> productosEnCarrito = carritoProductosRepository.findByCarrito(carrito);
        if (productosEnCarrito.isEmpty()) {
            throw new IllegalArgumentException("El carrito está vacío.");
        }
        Venta venta = new Venta();
        double totalVenta = 0;
        for (CarritoProductos item : productosEnCarrito) {
            DetalleVenta detalle = new DetalleVenta();
            detalle.setCantidad(item.getCantidad());
            detalle.setProducto(item.getProducto());
            detalle.setPrecioUnitario(item.getProducto().getPrecio());
            int cantidad = Integer.parseInt(item.getCantidad());
            double subtotal = cantidad * item.getProducto().getPrecio();
            detalle.setSubtotal(subtotal);
            detalle.setVenta(venta);
            totalVenta += subtotal;
            detalleVentaRepository.save(detalle);
        }
        venta.setTotal(totalVenta);
        venta.setFechaVenta(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));  // Configurar la fecha actual
        Venta ventaGuardada = ventaRepository.save(venta);

        carritoProductosRepository.deleteAll(productosEnCarrito);

        return ventaGuardada;
    }
}