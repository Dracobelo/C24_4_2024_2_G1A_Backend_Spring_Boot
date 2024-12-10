package com.tecsup.fruitcommerce.paq_services;

import com.tecsup.fruitcommerce.paq_models.Comprador;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz de servicio para gestionar operaciones relacionadas con el comprador.
 */
public interface CompradorService {

    /**
     * Encuentra un comprador por su ID.
     *
     * @param id ID del comprador.
     * @return Un Optional conteniendo el comprador si existe.
     */
    Optional<Comprador> findById(Long id);

    /**
     * Obtiene una lista de todos los compradores.
     *
     * @return Una lista con todos los compradores.
     */
    List<Comprador> findAll();

    /**
     * Guarda o actualiza un comprador.
     *
     * @param comprador Objeto Comprador a guardar.
     */
    void save(Comprador comprador);

    /**
     * Elimina un comprador por su ID.
     *
     * @param id ID del comprador a eliminar.
     */
    void deleteById(Long id);

    /**
     * Encuentra un comprador asociado a un usuario específico.
     *
     * @param usuarioId ID del usuario.
     * @return Un Optional conteniendo el comprador si existe.
     */
    Optional<Comprador> findByUser_UserId(Long usuarioId);
}
