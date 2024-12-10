package com.tecsup.fruitcommerce.paq_controllers;

import com.tecsup.fruitcommerce.paq_dtos.ProductoRequestDTO;
import com.tecsup.fruitcommerce.paq_models.Categoria;
import com.tecsup.fruitcommerce.paq_models.Producto;
import com.tecsup.fruitcommerce.paq_models.Proveedor;
import com.tecsup.fruitcommerce.paq_models.User;
import com.tecsup.fruitcommerce.paq_services.CategoriaService;
import com.tecsup.fruitcommerce.paq_services.ProductoService;
import com.tecsup.fruitcommerce.paq_services.ProveedorService;
import com.tecsup.fruitcommerce.paq_services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_PRODUCTOR') or hasRole('ROLE_CLIENTE')")
    @GetMapping("/all")
    public ResponseEntity<List<Producto>> getAllProductos(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        List<Producto> productos;

        if (userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_PRODUCTOR"))) {
            // Proveedor: obtener solo sus productos
            User user = userService.findByUsername(username);
            Proveedor proveedor = proveedorService.findByUser_UserId(user.getUserId())
                    .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

            productos = productoService.findByProveedorId(proveedor.getIdProveedor());
        } else {
            // Cliente: obtener solo productos activos
            productos = productoService.findAll().stream()
                    .filter(Producto::isActivo)
                    .collect(Collectors.toList());
        }

        return ResponseEntity.ok(productos);
    }

    @PreAuthorize("hasRole('ROLE_PRODUCTOR')")
    @PostMapping("/save")
    public ResponseEntity<?> saveProducto(@RequestBody ProductoRequestDTO productoDTO, @AuthenticationPrincipal UserDetails userDetails) {
        if (productoDTO.getNombre() == null || productoDTO.getNombre().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre del producto es obligatorio.");
        }
        if (productoDTO.getCategoriaId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La categoría es obligatoria.");
        }
        if (productoDTO.getPrecio() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El precio debe ser mayor que cero.");
        }
        if (productoDTO.getStock() < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El stock no puede ser negativo.");
        }

        String username = userDetails.getUsername();
        User user = userService.findByUsername(username);
        Proveedor proveedor = proveedorService.findByUser_UserId(user.getUserId())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        Categoria categoria = categoriaService.findById(productoDTO.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setCategoria(categoria);
        producto.setProveedor(proveedor);
        producto.setStock(productoDTO.getStock());
        producto.setFoto(productoDTO.getFoto());
        producto.setActivo(true); // Por defecto el producto está activo

        productoService.save(producto);
        return ResponseEntity.ok("Producto guardado con éxito");
    }

    @PreAuthorize("hasRole('ROLE_PRODUCTOR')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProducto(@PathVariable Integer id, @RequestBody ProductoRequestDTO productoDTO, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        User user = userService.findByUsername(username);
        Proveedor proveedor = proveedorService.findByUser_UserId(user.getUserId())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        Producto producto = productoService.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        if (!producto.getProveedor().getIdProveedor().equals(proveedor.getIdProveedor())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No puedes actualizar este producto");
        }

        Categoria categoria = categoriaService.findById(productoDTO.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        producto.setNombre(productoDTO.getNombre());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setCategoria(categoria);
        producto.setStock(productoDTO.getStock());
        producto.setFoto(productoDTO.getFoto());

        productoService.save(producto);
        return ResponseEntity.ok("Producto actualizado con éxito");
    }

    @PreAuthorize("hasRole('ROLE_PRODUCTOR')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Integer id, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        User user = userService.findByUsername(username);
        Proveedor proveedor = proveedorService.findByUser_UserId(user.getUserId())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        Producto producto = productoService.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        if (!producto.getProveedor().getIdProveedor().equals(proveedor.getIdProveedor())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No puedes eliminar este producto");
        }

        productoService.deleteById(id);
        return ResponseEntity.ok("Producto eliminado con éxito");
    }

    @PreAuthorize("hasRole('ROLE_PRODUCTOR')")
    @PutMapping("/toggle/{id}")
    public ResponseEntity<?> toggleProducto(@PathVariable Integer id, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        User user = userService.findByUsername(username);
        Proveedor proveedor = proveedorService.findByUser_UserId(user.getUserId())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        Producto producto = productoService.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        if (!producto.getProveedor().getIdProveedor().equals(proveedor.getIdProveedor())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No puedes modificar este producto");
        }

        producto.setActivo(!producto.isActivo()); // Cambiar el estado activo
        productoService.save(producto);
        return ResponseEntity.ok("Estado del producto cambiado con éxito");
    }

    @PreAuthorize("hasRole('ROLE_CLIENTE') or hasRole('ROLE_PRODUCTOR')")
    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        return ResponseEntity.ok(categoriaService.findAll());
    }

    @PreAuthorize("hasRole('ROLE_CLIENTE')")
    @GetMapping("/porNombre/{nombre}")
    public ResponseEntity<List<Producto>> getProductosByNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(productoService.findByNombre(nombre));
    }

    @PreAuthorize("hasRole('ROLE_CLIENTE')")
    @GetMapping("/porCategoria/{categoriaId}")
    public ResponseEntity<List<Producto>> getProductosByCategoria(@PathVariable Integer categoriaId) {
        return ResponseEntity.ok(productoService.findByCategoriaId(categoriaId));
    }

    @PreAuthorize("hasRole('ROLE_CLIENTE')")
    @GetMapping("/porProveedor/{proveedorId}")
    public ResponseEntity<List<Producto>> getProductosByProveedor(@PathVariable Long proveedorId) {
        return ResponseEntity.ok(productoService.findByProveedorId(proveedorId));
    }
}
