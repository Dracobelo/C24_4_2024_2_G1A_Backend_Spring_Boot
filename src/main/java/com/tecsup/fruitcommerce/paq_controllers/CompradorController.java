package com.tecsup.fruitcommerce.paq_controllers;

import com.tecsup.fruitcommerce.paq_models.Comprador;
import com.tecsup.fruitcommerce.paq_dtos.CompradorDTO;
import com.tecsup.fruitcommerce.paq_models.User;
import com.tecsup.fruitcommerce.paq_repositories.CompradorRepository;
import com.tecsup.fruitcommerce.paq_repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/compradores")
public class CompradorController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompradorRepository compradorRepository;

    /**
     * Endpoint para actualizar el perfil del comprador.
     */
    @PutMapping("/update")
    public ResponseEntity<?> updateComprador(
            @RequestBody CompradorDTO compradorDTO,
            @AuthenticationPrincipal UserDetails userDetails) {

        // Obtener el username del usuario autenticado
        String username = userDetails.getUsername();

        // Buscar el usuario asociado al username
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        // Buscar el comprador asociado al usuario
        Comprador comprador = compradorRepository.findByUser_UserId(user.getUserId())
                .orElseThrow(() -> new RuntimeException("Comprador no encontrado."));

        // Actualizar los datos del comprador solo si no son nulos
        comprador.setNombres(
                compradorDTO.getNombres() != null ? compradorDTO.getNombres() : comprador.getNombres());
        comprador.setApellidos(
                compradorDTO.getApellidos() != null ? compradorDTO.getApellidos() : comprador.getApellidos());
        comprador.setDni(
                compradorDTO.getDni() != null ? compradorDTO.getDni() : comprador.getDni());
        comprador.setTelefono(
                compradorDTO.getTelefono() != null ? compradorDTO.getTelefono() : comprador.getTelefono());
        comprador.setDireccion(
                compradorDTO.getDireccion() != null ? compradorDTO.getDireccion() : comprador.getDireccion());
        comprador.setRuc(
                compradorDTO.getRuc() != null ? compradorDTO.getRuc() : comprador.getRuc());

        // Guardar los cambios
        compradorRepository.save(comprador);

        return ResponseEntity.ok("Perfil de comprador actualizado correctamente.");
    }

    /**
     * Endpoint para obtener el perfil del comprador autenticado.
     */
    @GetMapping("/profile")
    public ResponseEntity<?> getCompradorProfile(@AuthenticationPrincipal UserDetails userDetails) {
        // Obtener el username del usuario autenticado
        String username = userDetails.getUsername();

        // Buscar el usuario asociado al username
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        // Buscar el comprador asociado al usuario
        Comprador comprador = compradorRepository.findByUser_UserId(user.getUserId())
                .orElseThrow(() -> new RuntimeException("Comprador no encontrado."));

        // Convertir el comprador en DTO para devolver solo los datos necesarios
        CompradorDTO compradorDTO = new CompradorDTO();
        compradorDTO.setNombres(comprador.getNombres());
        compradorDTO.setApellidos(comprador.getApellidos());
        compradorDTO.setDni(comprador.getDni());
        compradorDTO.setTelefono(comprador.getTelefono());
        compradorDTO.setDireccion(comprador.getDireccion());
        compradorDTO.setRuc(comprador.getRuc());

        return ResponseEntity.ok(compradorDTO);
    }
}
