package com.tecsup.fruitcommerce.paq_services;

import com.tecsup.fruitcommerce.paq_models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    void save(Producto producto);
    Optional<Producto> findById(Integer id);
    List<Producto> findAll();
    void update(Integer id, Producto producto);
    void deleteById(Integer id);
    List<Producto> findByNombre(String nombre);
    List<Producto> findByCategoriaId(Integer categoriaId);
    List<Producto> findByProveedorId(Long proveedorId);
}

