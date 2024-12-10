package com.tecsup.fruitcommerce.paq_controllers;

import com.tecsup.fruitcommerce.paq_models.Proveedor;
import com.tecsup.fruitcommerce.paq_models.User;
import com.tecsup.fruitcommerce.paq_services.ProveedorService;
import com.tecsup.fruitcommerce.paq_services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores_proveedor")
public class Tids_proveController {

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    UserService userService;

    @PreAuthorize("hasRole('ROLE_CLIENTE') or hasRole('ROLE_PRODUCTOR')")
    @GetMapping
    public ResponseEntity<List<Proveedor>> getAllProveedores() {
        List<Proveedor> proveedores = proveedorService.findAll();

        if (proveedores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(proveedores);
    }
    @PreAuthorize("hasRole('ROLE_CLIENTE') or hasRole('ROLE_PRODUCTOR')")
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> getProveedorById(@PathVariable Long id) {
        return proveedorService.findById(id)
                .map(proveedor -> ResponseEntity.ok(proveedor))
                .orElse(ResponseEntity.notFound().build());
    }
    @PreAuthorize("hasRole('ROLE_PRODUCTOR')")
    @PutMapping("/proveedores")
    public ResponseEntity<String> updateProveedor(
            @RequestParam String telefono,
            @RequestParam String direccion,
            @RequestParam String ruc,
            @RequestParam String nombreEmpresa,
            @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        User user = userService.findByUsername(username);
        Proveedor proveedor = proveedorService.findByUser_UserId(user.getUserId())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado para el usuario autenticado"));
        proveedor.setNombreEmpresa(nombreEmpresa);
        proveedor.setTelefono(telefono);
        proveedor.setDireccion(direccion);
        proveedor.setRuc(ruc);
        proveedorService.save(proveedor);
        return ResponseEntity.ok("Proveedor actualizado con éxito");
    }
}