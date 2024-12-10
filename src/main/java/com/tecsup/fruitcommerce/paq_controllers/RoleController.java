package com.tecsup.fruitcommerce.paq_controllers;

import com.tecsup.fruitcommerce.paq_models.Comprador;
import com.tecsup.fruitcommerce.paq_models.Proveedor;
import com.tecsup.fruitcommerce.paq_services.CarritoService;
import com.tecsup.fruitcommerce.paq_services.CompradorService;
import com.tecsup.fruitcommerce.paq_services.ProveedorService;
import com.tecsup.fruitcommerce.paq_services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.tecsup.fruitcommerce.paq_models.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private CompradorService compradorService;

    @Autowired
    private CarritoService carritoService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/elegir_rol")
    public ResponseEntity<String> updateUserRoleFromUser(@RequestParam String roleName,
                                                         @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        User user = userService.findByUsername(username);

        if (!roleName.equals("ROLE_CLIENTE") && !roleName.equals("ROLE_PRODUCTOR")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid role selection");
        }

        // Actualiza el rol del usuario
        userService.updateUserRole(user.getUserId(), roleName);

        // Verifica si el rol es PRODUCTOR y crea el registro en la tabla Proveedor
        if ("ROLE_PRODUCTOR".equals(roleName) && proveedorService.findByUser_UserId(user.getUserId()).isEmpty()) {
            Proveedor proveedor = new Proveedor();
            proveedor.setUser(user);
            proveedorService.save(proveedor); // Guarda el nuevo proveedor
        }

        // Verifica si el rol es CLIENTE y crea el registro en la tabla Comprador
        else if ("ROLE_CLIENTE".equals(roleName) && compradorService.findByUser_UserId(user.getUserId()).isEmpty()) {
            Comprador comprador = new Comprador();
            comprador.setUser(user);
            compradorService.save(comprador); // Guarda el nuevo comprador
            carritoService.crearCarrito(comprador.getIdComprador()); // Crear carrito para el comprador
        }

        return ResponseEntity.ok("User role updated to " + roleName + " and related entity created if not existing.");
    }

    @PreAuthorize("hasRole('ROLE_PRODUCTOR')")
    @PostMapping("/proveedores")
    public ResponseEntity<String> saveProveedor(
            @RequestParam String telefono,
            @RequestParam String direccion,
            @RequestParam String ruc,
            @RequestParam String nombreEmpresa,
            @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        User user = userService.findByUsername(username);
        Proveedor proveedor = new Proveedor();
        proveedor.setUser(user);
        proveedor.setNombreEmpresa(nombreEmpresa);
        proveedor.setTelefono(telefono);
        proveedor.setDireccion(direccion);
        proveedor.setRuc(ruc);
        proveedorService.save(proveedor);
        return ResponseEntity.ok("Proveedor guardado con éxito");
    }

    @PreAuthorize("hasRole('ROLE_CLIENTE')")
    @PostMapping("/compradores")
    public ResponseEntity<String> saveComprador(
            @RequestParam String telefono,
            @RequestParam String direccion,
            @RequestParam String ruc,
            @RequestParam String nombres,
            @RequestParam String apellidos,
            @RequestParam String dni,
            @AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();
        User user = userService.findByUsername(username);

        Comprador comprador = new Comprador();
        comprador.setUser(user);
        comprador.setNombres(nombres);
        comprador.setApellidos(apellidos);
        comprador.setDni(dni);
        comprador.setTelefono(telefono);
        comprador.setDireccion(direccion);
        comprador.setRuc(ruc);
        compradorService.save(comprador);
        carritoService.crearCarrito(comprador.getIdComprador());
        return ResponseEntity.ok("Comprador y carrito guardados con éxito");
    }
}
