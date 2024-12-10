package com.tecsup.fruitcommerce.paq_services;

import com.tecsup.fruitcommerce.paq_models.Carrito;
import com.tecsup.fruitcommerce.paq_models.CarritoProductos;

import java.util.List;
import java.util.Optional;

public interface CarritoProductosService {

    /**
     * Obtiene los productos asociados a un carrito específico.
     *
     * @param carrito El carrito del cual se obtendrán los productos.
     * @return Lista de productos en el carrito.
     */
    List<CarritoProductos> obtenerProductosPorCarrito(Carrito carrito);

    /**
     * Agrega un producto al carrito.
     *
     * @param carritoProducto El objeto que representa el producto y carrito asociado.
     * @return El objeto CarritoProductos agregado.
     */
    CarritoProductos agregarProductoAlCarrito(CarritoProductos carritoProducto);

    /**
     * Elimina un producto del carrito usando su ID.
     *
     * @param id El ID del producto en el carrito a eliminar.
     */
    void eliminarProductoDelCarrito(Integer id);

    /**
     * Incrementa la cantidad de un producto en el carrito.
     *
     * @param idCarritoProducto El ID del producto en el carrito.
     * @return El objeto actualizado con la cantidad incrementada.
     */
    CarritoProductos aumentarCantidad(Integer idCarritoProducto);

    /**
     * Reduce la cantidad de un producto en el carrito. Si la cantidad llega a 0, el producto se elimina.
     *
     * @param idCarritoProducto El ID del producto en el carrito.
     * @return El objeto actualizado o null si el producto fue eliminado.
     */
    CarritoProductos reducirCantidad(Integer idCarritoProducto);

    /**
     * Actualiza la cantidad de un producto en el carrito.
     *
     * @param idCarritoProducto El ID del producto en el carrito.
     * @param cantidad La nueva cantidad a asignar.
     * @return El objeto actualizado con la cantidad ajustada.
     * @throws IllegalArgumentException Si la cantidad es menor o igual a 0.
     */
    CarritoProductos actualizarCantidad(Integer idCarritoProducto, double cantidad);

    /**
     * Calcula el total de todos los productos en un carrito.
     *
     * @param carrito El carrito del cual se calculará el total.
     * @return El total de los precios de los productos en el carrito.
     */
    double calcularTotal(Carrito carrito);

    /**
     * Busca un producto en el carrito por su ID.
     *
     * @param id El ID del producto en el carrito.
     * @return Un Optional que contiene el producto si se encuentra.
     */
    Optional<CarritoProductos> findById(Integer id);

    /**
     * Obtiene un producto específico en un carrito por su ID de carrito y producto.
     *
     * @param idCarrito El ID del carrito.
     * @param idProducto El ID del producto.
     * @return Un Optional que contiene el producto si existe.
     */
    Optional<CarritoProductos> obtenerPorCarritoYProducto(Integer idCarrito, Integer idProducto);
}
