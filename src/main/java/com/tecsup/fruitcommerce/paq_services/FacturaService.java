package com.tecsup.fruitcommerce.paq_services;

import com.tecsup.fruitcommerce.paq_models.Factura;
import com.tecsup.fruitcommerce.paq_models.Venta;

import java.util.List;
import java.util.Optional;

public interface FacturaService {
    Factura crearFactura(Factura factura);
    Optional<Factura> obtenerFacturaPorId(Integer id);
    List<Factura> obtenerTodasLasFacturas();
    void eliminarFactura(Integer id);
    Factura crearFacturaDesdeVenta(Venta venta);

}