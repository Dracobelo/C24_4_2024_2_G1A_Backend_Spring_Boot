package com.tecsup.fruitcommerce.paq_models;

import jakarta.persistence.*;

@Entity
@Table(name = "Venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVenta")
    private Integer idVenta;

    @Column(name = "fecha_venta")
    private String fechaVenta;

    @Column(name = "numero_venta")
    private String numeroVenta;

    @Column(name = "total")
    private double total;

    @ManyToOne
    @JoinColumn(name = "Comprador_idComprador", nullable = false)
    private Comprador comprador;

    @ManyToOne
    @JoinColumn(name = "MetodosPago_idMetodosPago", nullable = false)
    private MetodosPago metodosPago;

    public Venta() {}

    public Venta(Integer idVenta, String fechaVenta, String numeroVenta, double total, Comprador comprador, MetodosPago metodosPago) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.numeroVenta = numeroVenta;
        this.total = total;
        this.comprador = comprador;
        this.metodosPago = metodosPago;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getNumeroVenta() {
        return numeroVenta;
    }

    public void setNumeroVenta(String numeroVenta) {
        this.numeroVenta = numeroVenta;
    }
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }
    public MetodosPago getMetodosPago() {
        return metodosPago;
    }

    public void setMetodosPago(MetodosPago metodosPago) {
        this.metodosPago = metodosPago;
    }
    @Override
    public String toString() {
        return "Venta{" +
                "idVenta=" + idVenta +
                ", fechaVenta='" + fechaVenta + '\'' +
                ", numeroVenta='" + numeroVenta + '\'' +
                ", total=" + total +
                ", comprador=" + comprador +
                ", metodosPago=" + metodosPago +
                '}';
    }
}