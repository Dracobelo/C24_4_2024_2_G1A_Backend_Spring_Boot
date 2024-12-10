package com.tecsup.fruitcommerce.paq_services;

import com.tecsup.fruitcommerce.paq_models.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    void save(Categoria categoria);
    Optional<Categoria> findById(Integer id);
    List<Categoria> findAll();
    void deleteById(Integer id);
}