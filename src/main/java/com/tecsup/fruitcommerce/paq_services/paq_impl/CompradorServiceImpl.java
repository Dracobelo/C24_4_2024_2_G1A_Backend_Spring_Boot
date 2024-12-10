package com.tecsup.fruitcommerce.paq_services.paq_impl;

import com.tecsup.fruitcommerce.paq_models.Comprador;
import com.tecsup.fruitcommerce.paq_repositories.CompradorRepository;
import com.tecsup.fruitcommerce.paq_services.CompradorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio para gestionar operaciones relacionadas con los compradores.
 */
@Service
public class CompradorServiceImpl implements CompradorService {

    private final CompradorRepository compradorRepository;

    /**
     * Constructor que inyecta el repositorio del comprador.
     *
     * @param compradorRepository Repositorio para gestionar los datos de los compradores.
     */
    public CompradorServiceImpl(CompradorRepository compradorRepository) {
        this.compradorRepository = compradorRepository;
    }

    /**
     * Encuentra un comprador por su ID.
     *
     * @param id ID del comprador.
     * @return Un Optional que contiene el comprador si existe.
     */
    @Override
    public Optional<Comprador> findById(Long id) {
        return compradorRepository.findById(id);
    }

    /**
     * Obtiene una lista de todos los compradores.
     *
     * @return Lista de compradores.
     */
    @Override
    public List<Comprador> findAll() {
        return compradorRepository.findAll();
    }

    /**
     * Guarda o actualiza un comprador.
     *
     * @param comprador Objeto Comprador a guardar.
     */
    @Override
    public void save(Comprador comprador) {
        compradorRepository.save(comprador);
    }

    /**
     * Elimina un comprador por su ID.
     *
     * @param id ID del comprador a eliminar.
     */
    @Override
    public void deleteById(Long id) {
        compradorRepository.deleteById(id);
    }

    /**
     * Encuentra un comprador asociado a un usuario específico.
     *
     * @param usuarioId ID del usuario asociado al comprador.
     * @return Un Optional que contiene el comprador si existe.
     */
    @Override
    public Optional<Comprador> findByUser_UserId(Long usuarioId) {
        return compradorRepository.findByUser_UserId(usuarioId);
    }
}
