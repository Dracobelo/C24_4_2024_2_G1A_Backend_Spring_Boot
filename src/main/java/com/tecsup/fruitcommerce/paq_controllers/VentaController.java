package com.tecsup.fruitcommerce.paq_controllers;

import com.tecsup.fruitcommerce.paq_models.Carrito;
import com.tecsup.fruitcommerce.paq_models.Venta;
import com.tecsup.fruitcommerce.paq_repositories.CarritoRepository;
import com.tecsup.fruitcommerce.paq_services.VentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    private final VentaService ventaService;
    private final CarritoRepository carritoRepository;

    public VentaController(VentaService ventaService, CarritoRepository carritoRepository) {
        this.ventaService = ventaService;
        this.carritoRepository = carritoRepository;
    }

    // Crear venta desde carrito
    @PostMapping("/crearDesdeCarrito/{carritoId}")
    public ResponseEntity<Venta> crearVentaDesdeCarrito(@PathVariable Integer carritoId) {
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new IllegalArgumentException("Carrito no encontrado."));
        Venta ventaCreada = ventaService.crearVentaDesdeCarrito(carrito);
        return ResponseEntity.ok(ventaCreada);
    }

    // Guardar una venta
    @PostMapping
    public ResponseEntity<Venta> guardarVenta(@RequestBody Venta venta) {
        Venta nuevaVenta = ventaService.guardarVenta(venta);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
    }

    // Obtener venta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtenerVentaPorId(@PathVariable Integer id) {
        Optional<Venta> venta = ventaService.obtenerVentaPorId(id);
        return venta.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Obtener todas las ventas
    @GetMapping
    public ResponseEntity<List<Venta>> obtenerTodasLasVentas() {
        List<Venta> ventas = ventaService.obtenerTodasLasVentas();
        return ResponseEntity.ok(ventas);
    }

    // Eliminar venta por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVentaPorId(@PathVariable Integer id) {
        ventaService.eliminarVentaPorId(id);
        return ResponseEntity.noContent().build();
    }
}