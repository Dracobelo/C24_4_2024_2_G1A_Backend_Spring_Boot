package com.tecsup.fruitcommerce.paq_repositories;

import com.tecsup.fruitcommerce.paq_models.Carrito;
import com.tecsup.fruitcommerce.paq_models.CarritoProductos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarritoProductosRepository extends JpaRepository<CarritoProductos, Integer> {

    /**
     * Obtiene todos los productos en un carrito específico por su ID.
     */
    List<CarritoProductos> findByCarritoIdCarrito(Integer carritoId);

    /**
     * Busca un producto específico en un carrito por el ID del carrito y el ID del producto.
     */
    Optional<CarritoProductos> findByCarritoIdCarritoAndProductoIdProducto(Integer carritoId, Integer productoId);

    /**
     * Obtiene todos los productos asociados a un carrito.
     */
    List<CarritoProductos> findByCarrito(Carrito carrito);
}
