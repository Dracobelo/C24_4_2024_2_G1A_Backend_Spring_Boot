package com.tecsup.fruitcommerce.paq_services;

import com.tecsup.fruitcommerce.paq_models.Carrito;
import com.tecsup.fruitcommerce.paq_models.Venta;

import java.util.List;
import java.util.Optional;

public interface VentaService {
    Venta guardarVenta(Venta venta);
    Optional<Venta> obtenerVentaPorId(Integer id);
    List<Venta> obtenerTodasLasVentas();
    void eliminarVentaPorId(Integer id);
    Venta crearVentaDesdeCarrito(Carrito carrito);
}