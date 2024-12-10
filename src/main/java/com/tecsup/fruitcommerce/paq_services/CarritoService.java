package com.tecsup.fruitcommerce.paq_services;

import com.tecsup.fruitcommerce.paq_models.Carrito;

import java.util.Optional;

public interface CarritoService {
    Carrito crearCarrito(Long idComprador);
    Optional<Carrito> obtenerCarritoPorId(Integer id);
    Carrito crearCarrito(Carrito carrito);
    void eliminarCarrito(Integer id);
    Optional<Carrito> buscarCarritoPorCompradorId(Long compradorId);
    Optional<Carrito> obtenerCarritoPorUsuarioId(Long usuarioId);
}