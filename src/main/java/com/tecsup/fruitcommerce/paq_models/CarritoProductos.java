package com.tecsup.fruitcommerce.paq_models;

import jakarta.persistence.*;

@Entity
@Table(name = "carrito_producto")
public class CarritoProductos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCarritoProductos")
    private Integer idCarritoProductos;

    @Column(name = "cantidad")
    private String cantidad;

    @ManyToOne
    @JoinColumn(name = "Carrito_idCarrito", nullable = false)
    private Carrito carrito;

    @ManyToOne
    @JoinColumn(name = "Producto_idProducto", nullable = false)
    private Producto producto;

    public CarritoProductos() {}

    public CarritoProductos(Integer idCarritoProductos, String cantidad, Carrito carrito, Producto producto) {
        this.idCarritoProductos = idCarritoProductos;
        this.cantidad = cantidad;
        this.carrito = carrito;
        this.producto = producto;
    }

    public Integer getIdCarritoProductos() {
        return idCarritoProductos;
    }

    public void setIdCarritoProductos(Integer idCarritoProductos) {
        this.idCarritoProductos = idCarritoProductos;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "CarritoProductos{" +
                "idCarritoProductos=" + idCarritoProductos +
                ", cantidad='" + cantidad + '\'' +
                ", carrito=" + carrito +
                ", producto=" + producto +
                '}';
    }
}
