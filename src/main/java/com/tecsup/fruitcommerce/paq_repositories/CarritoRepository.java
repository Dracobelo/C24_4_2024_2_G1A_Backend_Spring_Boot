package com.tecsup.fruitcommerce.paq_repositories;

import com.tecsup.fruitcommerce.paq_models.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
    Optional<Carrito> findByCompradorIdComprador(Long compradorId);
    Optional<Carrito> findByCompradorUserUserId(Long usuarioId);
}