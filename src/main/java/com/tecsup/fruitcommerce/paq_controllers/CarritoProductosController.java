package com.tecsup.fruitcommerce.paq_controllers;

import com.tecsup.fruitcommerce.paq_models.Carrito;
import com.tecsup.fruitcommerce.paq_models.CarritoProductos;
import com.tecsup.fruitcommerce.paq_models.Producto;
import com.tecsup.fruitcommerce.paq_models.User;
import com.tecsup.fruitcommerce.paq_services.CarritoProductosService;
import com.tecsup.fruitcommerce.paq_services.CarritoService;
import com.tecsup.fruitcommerce.paq_services.ProductoService;
import com.tecsup.fruitcommerce.paq_services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/carrito-productos")
public class CarritoProductosController {

    @Autowired
    private CarritoProductosService carritoProductosService;

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductoService productoService;

    @GetMapping("/{carritoId}/productos")
    public ResponseEntity<List<CarritoProductos>> obtenerProductosPorCarrito(@PathVariable Integer carritoId) {
        try {
            Carrito carrito = new Carrito();
            carrito.setIdCarrito(carritoId);
            List<CarritoProductos> productos = carritoProductosService.obtenerProductosPorCarrito(carrito);
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/agregar")
    public ResponseEntity<?> agregarProductoAlCarrito(
            @RequestParam Integer productoId,
            @AuthenticationPrincipal UserDetails userDetails) {
        try {
            String username = userDetails.getUsername();
            User user = userService.findByUsername(username);
            Long usuarioId = user.getUserId();

            Optional<Carrito> carritoOptional = carritoService.obtenerCarritoPorUsuarioId(usuarioId);
            if (!carritoOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrito no encontrado.");
            }

            Carrito carrito = carritoOptional.get();
            Optional<CarritoProductos> existente = carritoProductosService
                    .obtenerPorCarritoYProducto(carrito.getIdCarrito(), productoId);

            if (existente.isPresent()) {
                CarritoProductos carritoProducto = existente.get();
                carritoProducto.setCantidad(String.valueOf(Integer.parseInt(carritoProducto.getCantidad()) + 1));
                carritoProductosService.agregarProductoAlCarrito(carritoProducto);
                return ResponseEntity.ok(carritoProducto);
            }

            Producto producto = productoService.findById(productoId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado."));

            if (producto.getStock() <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El producto no tiene stock disponible.");
            }

            CarritoProductos carritoProducto = new CarritoProductos();
            carritoProducto.setCarrito(carrito);
            carritoProducto.setProducto(producto);
            carritoProducto.setCantidad("1");

            CarritoProductos nuevoProducto = carritoProductosService.agregarProductoAlCarrito(carritoProducto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al agregar el producto al carrito: " + e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{idCarritoProducto}")
    public ResponseEntity<?> eliminarProductoDelCarrito(
            @PathVariable Integer idCarritoProducto,
            @AuthenticationPrincipal UserDetails userDetails) {
        try {
            String username = userDetails.getUsername();
            User user = userService.findByUsername(username);
            Long usuarioId = user.getUserId();

            Optional<Carrito> carritoOptional = carritoService.obtenerCarritoPorUsuarioId(usuarioId);
            if (!carritoOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrito no encontrado.");
            }

            Carrito carrito = carritoOptional.get();
            Optional<CarritoProductos> carritoProductoOptional = carritoProductosService.findById(idCarritoProducto);

            if (!carritoProductoOptional.isPresent() || !carritoProductoOptional.get().getCarrito().equals(carrito)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado en el carrito.");
            }

            carritoProductosService.eliminarProductoDelCarrito(idCarritoProducto);
            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el producto del carrito: " + e.getMessage());
        }
    }

    @PostMapping("/{idCarritoProducto}/actualizar-cantidad")
    public ResponseEntity<?> actualizarCantidad(
            @PathVariable Integer idCarritoProducto,
            @RequestBody Map<String, Object> body) {
        try {
            if (!body.containsKey("cantidad")) {
                return ResponseEntity.badRequest().body("El campo 'cantidad' es obligatorio.");
            }

            double cantidad;
            try {
                cantidad = Double.parseDouble(body.get("cantidad").toString());
            } catch (NumberFormatException e) {
                return ResponseEntity.badRequest().body("La cantidad debe ser un número válido.");
            }

            if (cantidad <= 0) {
                return ResponseEntity.badRequest().body("La cantidad debe ser mayor que 0.");
            }

            CarritoProductos carritoProductoActualizado = carritoProductosService.actualizarCantidad(idCarritoProducto, cantidad);
            return ResponseEntity.ok(carritoProductoActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar la cantidad: " + e.getMessage());
        }
    }

    @GetMapping("/{carritoId}/total")
    public ResponseEntity<?> calcularTotal(
            @PathVariable Integer carritoId,
            @AuthenticationPrincipal UserDetails userDetails) {
        try {
            String username = userDetails.getUsername();
            User user = userService.findByUsername(username);
            Long usuarioId = user.getUserId();

            Optional<Carrito> carritoOptional = carritoService.obtenerCarritoPorUsuarioId(usuarioId);

            if (!carritoOptional.isPresent() || !carritoOptional.get().getIdCarrito().equals(carritoId)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrito no encontrado o no pertenece al usuario.");
            }

            double total = carritoProductosService.calcularTotal(carritoOptional.get());
            return ResponseEntity.ok(total);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al calcular el total del carrito: " + e.getMessage());
        }
    }
}
