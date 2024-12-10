package com.tecsup.fruitcommerce.paq_repositories;

import com.tecsup.fruitcommerce.paq_models.Comprador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompradorRepository extends JpaRepository<Comprador, Long> {

    /**
     * Encuentra un comprador por su ID.
     *
     * @param id ID del comprador.
     * @return Un Optional conteniendo el comprador si existe.
     */
    Optional<Comprador> findById(Long id);

    /**
     * Encuentra un comprador asociado a un usuario específico.
     *
     * @param usuarioId ID del usuario.
     * @return Un Optional conteniendo el comprador si existe.
     */
    Optional<Comprador> findByUser_UserId(Long usuarioId);

    /**
     * Obtiene todos los compradores en la base de datos.
     *
     * @return Una lista con todos los compradores.
     */
    List<Comprador> findAll();

    /**
     * Elimina un comprador por su ID.
     *
     * @param id ID del comprador a eliminar.
     */
    void deleteById(Long id);
}
