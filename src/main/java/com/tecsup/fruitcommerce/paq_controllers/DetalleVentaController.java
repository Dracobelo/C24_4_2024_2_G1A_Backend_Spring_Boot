package com.tecsup.fruitcommerce.paq_controllers;

import com.tecsup.fruitcommerce.paq_models.DetalleVenta;
import com.tecsup.fruitcommerce.paq_services.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/detalleVentas")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    @PostMapping
    public DetalleVenta crearDetalleVenta(@RequestBody DetalleVenta detalleVenta) {
        return detalleVentaService.crearDetalleVenta(detalleVenta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVenta> obtenerDetallePorId(@PathVariable Integer id) {
        Optional<DetalleVenta> detalle = detalleVentaService.obtenerDetallePorId(id);
        return detalle.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/venta/{ventaId}")
    public List<DetalleVenta> obtenerDetallesPorVentaId(@PathVariable Integer ventaId) {
        return detalleVentaService.obtenerDetallesPorVentaId(ventaId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDetalleVenta(@PathVariable Integer id) {
        detalleVentaService.eliminarDetalleVenta(id);
        return ResponseEntity.noContent().build();
    }
}