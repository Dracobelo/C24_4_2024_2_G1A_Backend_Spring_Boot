package com.tecsup.fruitcommerce.paq_models;

import jakarta.persistence.*;

@Entity
@Table(name = "MetodosPago")
public class MetodosPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMetodosPago")
    private Integer idMetodosPago;

    @Column(name = "metodo")
    private String metodo;

    public MetodosPago() {}

    public MetodosPago(Integer idMetodosPago, String metodo) {
        this.idMetodosPago = idMetodosPago;
        this.metodo = metodo;
    }

    public Integer getIdMetodosPago() {
        return idMetodosPago;
    }

    public void setIdMetodosPago(Integer idMetodosPago) {
        this.idMetodosPago = idMetodosPago;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    @Override
    public String toString() {
        return "MetodosPago{" +
                "idMetodosPago=" + idMetodosPago +
                ", metodo='" + metodo + '\'' +
                '}';
    }
}
