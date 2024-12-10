package com.tecsup.fruitcommerce.paq_controllers;

import com.tecsup.fruitcommerce.paq_dtos.ProveedorDTO_proveedor;
import com.tecsup.fruitcommerce.paq_models.Proveedor;
import com.tecsup.fruitcommerce.paq_models.User;
import com.tecsup.fruitcommerce.paq_repositories.ProveedorRepository;
import com.tecsup.fruitcommerce.paq_repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    /**
     * Endpoint para actualizar el perfil del proveedor.
     */
    @PutMapping("/update")
    public ResponseEntity<?> updateProveedor(
            @RequestBody ProveedorDTO_proveedor proveedorDTO,
            @AuthenticationPrincipal UserDetails userDetails) {

        // Obtener el username del usuario autenticado
        String username = userDetails.getUsername();

        // Buscar el usuario asociado al username
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        // Buscar el proveedor asociado al usuario
        Proveedor proveedor = proveedorRepository.findByUser_UserId(user.getUserId())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado."));

        // Actualizar los datos del proveedor solo si no son nulos
        proveedor.setNombreEmpresa(
                proveedorDTO.getNombreEmpresa() != null ? proveedorDTO.getNombreEmpresa() : proveedor.getNombreEmpresa());
        proveedor.setTelefono(
                proveedorDTO.getTelefono() != null ? proveedorDTO.getTelefono() : proveedor.getTelefono());
        proveedor.setDireccion(
                proveedorDTO.getDireccion() != null ? proveedorDTO.getDireccion() : proveedor.getDireccion());
        proveedor.setRuc(
                proveedorDTO.getRuc() != null ? proveedorDTO.getRuc() : proveedor.getRuc());
        proveedor.setUbicacion(
                proveedorDTO.getUbicacion() != null ? proveedorDTO.getUbicacion() : proveedor.getUbicacion());
        proveedor.setHorariosAtencion(
                proveedorDTO.getHorariosAtencion() != null ? proveedorDTO.getHorariosAtencion() : proveedor.getHorariosAtencion());

        // Guardar los cambios
        proveedorRepository.save(proveedor);

        return ResponseEntity.ok("Perfil de proveedor actualizado correctamente.");
    }

    /**
     * Endpoint para obtener el perfil del proveedor autenticado.
     */
    @GetMapping("/profile")
    public ResponseEntity<?> getProveedorProfile(@AuthenticationPrincipal UserDetails userDetails) {

        // Obtener el username del usuario autenticado
        String username = userDetails.getUsername();

        // Buscar el usuario asociado al username
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        // Buscar el proveedor asociado al usuario
        Proveedor proveedor = proveedorRepository.findByUser_UserId(user.getUserId())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado."));

        // Crear un DTO con los datos del proveedor
        ProveedorDTO_proveedor proveedorDTO = new ProveedorDTO_proveedor(
                proveedor.getNombreEmpresa(),
                proveedor.getTelefono(),
                proveedor.getDireccion(),
                proveedor.getRuc(),
                proveedor.getUbicacion(),
                proveedor.getHorariosAtencion()
        );

        // Retornar el DTO como respuesta
        return ResponseEntity.ok(proveedorDTO);
    }
}
