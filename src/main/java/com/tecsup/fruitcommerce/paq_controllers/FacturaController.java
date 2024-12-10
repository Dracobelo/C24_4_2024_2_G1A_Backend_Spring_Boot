package com.tecsup.fruitcommerce.paq_controllers;

import com.tecsup.fruitcommerce.paq_models.Factura;
import com.tecsup.fruitcommerce.paq_models.Venta;
import com.tecsup.fruitcommerce.paq_repositories.VentaRepository;
import com.tecsup.fruitcommerce.paq_services.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private VentaRepository ventaRepository;

    /**
     * Crear una nueva factura.
     *
     * @param factura Objeto Factura con los datos para crear la factura.
     * @return La factura creada.
     */
    @PostMapping
    public Factura crearFactura(@RequestBody Factura factura) {
        return facturaService.crearFactura(factura);
    }

    /**
     * Obtener una factura por ID.
     *
     * @param id Identificador de la factura.
     * @return La factura si se encuentra, o una respuesta 404 si no.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Factura> obtenerFacturaPorId(@PathVariable Integer id) {
        Optional<Factura> factura = facturaService.obtenerFacturaPorId(id);
        return factura.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtener todas las facturas.
     *
     * @return Lista de todas las facturas.
     */
    @GetMapping
    public List<Factura> obtenerTodasLasFacturas() {
        return facturaService.obtenerTodasLasFacturas();
    }

    /**
     * Eliminar una factura por ID.
     *
     * @param id Identificador de la factura a eliminar.
     * @return Respuesta 204 No Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFactura(@PathVariable Integer id) {
        facturaService.eliminarFactura(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Crear una factura a partir de una venta.
     *
     * @param ventaId Identificador de la venta.
     * @return La factura creada basada en la venta.
     */
    @PostMapping("/crearDesdeVenta/{ventaId}")
    public ResponseEntity<Factura> crearFacturaDesdeVenta(@PathVariable Integer ventaId) {
        // Buscar la venta por su ID
        Venta venta = ventaRepository.findById(ventaId)
                .orElseThrow(() -> new IllegalArgumentException("Venta no encontrada."));

        // Crear la factura a partir de la venta
        Factura facturaCreada = facturaService.crearFacturaDesdeVenta(venta);

        return ResponseEntity.status(HttpStatus.CREATED).body(facturaCreada);
    }
}
