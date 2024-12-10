package com.tecsup.fruitcommerce.paq_models;

import jakarta.persistence.*;

@Entity
@Table(name = "carrito")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCarrito")
    private Integer idCarrito;

    @Column(name = "fecha_creacion")
    private String fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "idComprador")
    private Comprador comprador;

    public Carrito() {}

    public Carrito(Integer idCarrito, String fechaCreacion, Comprador comprador) {
        this.idCarrito = idCarrito;
        this.fechaCreacion = fechaCreacion;
        this.comprador = comprador;
    }

    public Integer getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(Integer idCarrito) {
        this.idCarrito = idCarrito;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    @Override
    public String toString() {
        return "Carrito{" +
                "idCarrito=" + idCarrito +
                ", fechaCreacion='" + fechaCreacion + '\'' +
                ", comprador=" + comprador +
                '}';
    }
}