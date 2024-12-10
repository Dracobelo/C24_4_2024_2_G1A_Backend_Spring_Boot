package com.tecsup.fruitcommerce.paq_models;

import jakarta.persistence.*;

@Entity
@Table(name = "Factura")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFactura")
    private Integer idFactura;

    @Column(name = "numero_factura")
    private String numeroFactura;

    @Column(name = "fecha_emision")
    private String fechaEmision;

    @Column(name = "monto_total")
    private double montoTotal;

    @ManyToOne
    @JoinColumn(name = "Venta_idVenta", nullable = false)
    private Venta venta;

    // Constructor vacío
    public Factura() {}

    // Constructor con parámetros
    public Factura(Integer idFactura, String numeroFactura, String fechaEmision, double montoTotal, Venta venta) {
        this.idFactura = idFactura;
        this.numeroFactura = numeroFactura;
        this.fechaEmision = fechaEmision;
        this.montoTotal = montoTotal;
        this.venta = venta;
    }

    // Getter y Setter para idFactura
    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    // Getter y Setter para numeroFactura
    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    // Getter y Setter para fechaEmision
    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    // Getter y Setter para montoTotal
    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    // Getter y Setter para venta
    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    // Método toString
    @Override
    public String toString() {
        return "Factura{" +
                "idFactura=" + idFactura +
                ", numeroFactura='" + numeroFactura + '\'' +
                ", fechaEmision='" + fechaEmision + '\'' +
                ", montoTotal=" + montoTotal +
                ", venta=" + venta +
                '}';
    }
}
