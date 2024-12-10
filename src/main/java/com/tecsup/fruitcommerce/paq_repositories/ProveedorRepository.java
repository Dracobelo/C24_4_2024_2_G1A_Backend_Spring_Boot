package com.tecsup.fruitcommerce.paq_repositories;

import com.tecsup.fruitcommerce.paq_models.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    /**
     * Encuentra un proveedor por su ID.
     *
     * @param idProveedor ID del proveedor
     * @return Un Optional con el proveedor si se encuentra
     */
    Optional<Proveedor> findById(Long idProveedor);

    /**
     * Encuentra un proveedor por el ID del usuario asociado.
     *
     * @param usuarioId ID del usuario asociado
     * @return Un Optional con el proveedor si se encuentra
     */
    Optional<Proveedor> findByUser_UserId(Long usuarioId);

    /**
     * Devuelve una lista con todos los proveedores.
     *
     * @return Lista de proveedores
     */
    List<Proveedor> findAll();

    /**
     * Elimina un proveedor por su ID.
     *
     * @param id ID del proveedor a eliminar
     */
    void deleteById(Long id);
}
