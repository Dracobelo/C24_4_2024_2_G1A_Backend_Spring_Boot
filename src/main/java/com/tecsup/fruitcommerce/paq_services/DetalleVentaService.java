package com.tecsup.fruitcommerce.paq_services;

import com.tecsup.fruitcommerce.paq_models.DetalleVenta;

import java.util.List;
import java.util.Optional;

public interface DetalleVentaService {
    DetalleVenta crearDetalleVenta(DetalleVenta detalleVenta);
    Optional<DetalleVenta> obtenerDetallePorId(Integer id);
    List<DetalleVenta> obtenerDetallesPorVentaId(Integer ventaId);
    void eliminarDetalleVenta(Integer id);
}