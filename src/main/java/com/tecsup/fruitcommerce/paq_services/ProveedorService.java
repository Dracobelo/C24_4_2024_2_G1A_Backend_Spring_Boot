package com.tecsup.fruitcommerce.paq_services;

import com.tecsup.fruitcommerce.paq_models.Proveedor;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar operaciones relacionadas con Proveedores.
 */
public interface ProveedorService {

    /**
     * Busca un proveedor por su ID.
     *
     * @param id ID del proveedor.
     * @return Un Optional con el proveedor si se encuentra.
     */
    Optional<Proveedor> findById(Long id);

    /**
     * Obtiene una lista con todos los proveedores.
     *
     * @return Lista de proveedores.
     */
    List<Proveedor> findAll();

    /**
     * Guarda o actualiza un proveedor en la base de datos.
     *
     * @param proveedor Proveedor a guardar o actualizar.
     */
    void save(Proveedor proveedor);

    /**
     * Elimina un proveedor por su ID.
     *
     * @param id ID del proveedor a eliminar.
     */
    void deleteById(Long id);

    /**
     * Busca un proveedor por el ID del usuario asociado.
     *
     * @param usuarioId ID del usuario asociado.
     * @return Un Optional con el proveedor si se encuentra.
     */
    Optional<Proveedor> findByUser_UserId(Long usuarioId);
}
