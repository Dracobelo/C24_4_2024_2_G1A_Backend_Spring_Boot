package com.tecsup.fruitcommerce.paq_services.paq_impl;

import com.tecsup.fruitcommerce.paq_models.Proveedor;
import com.tecsup.fruitcommerce.paq_repositories.ProveedorRepository;
import com.tecsup.fruitcommerce.paq_services.ProveedorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio para gestionar operaciones relacionadas con Proveedores.
 */
@Service
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository proveedorRepository;

    /**
     * Constructor que inyecta el repositorio de proveedores.
     *
     * @param proveedorRepository Repositorio de proveedores.
     */
    public ProveedorServiceImpl(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    /**
     * Busca un proveedor por su ID.
     *
     * @param id ID del proveedor.
     * @return Un Optional con el proveedor si se encuentra.
     */
    @Override
    public Optional<Proveedor> findById(Long id) {
        return proveedorRepository.findById(id);
    }

    /**
     * Obtiene una lista con todos los proveedores.
     *
     * @return Lista de proveedores.
     */
    @Override
    public List<Proveedor> findAll() {
        return proveedorRepository.findAll();
    }

    /**
     * Guarda o actualiza un proveedor en la base de datos.
     *
     * @param proveedor Proveedor a guardar o actualizar.
     */
    @Override
    public void save(Proveedor proveedor) {
        proveedorRepository.save(proveedor);
    }

    /**
     * Elimina un proveedor por su ID.
     *
     * @param id ID del proveedor a eliminar.
     */
    @Override
    public void deleteById(Long id) {
        proveedorRepository.deleteById(id);
    }

    /**
     * Busca un proveedor por el ID del usuario asociado.
     *
     * @param usuarioId ID del usuario asociado.
     * @return Un Optional con el proveedor si se encuentra.
     */
    @Override
    public Optional<Proveedor> findByUser_UserId(Long usuarioId) {
        return proveedorRepository.findByUser_UserId(usuarioId);
    }
}
