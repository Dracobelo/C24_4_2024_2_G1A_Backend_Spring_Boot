package com.tecsup.fruitcommerce.paq_dtos;

import java.io.Serializable;

public class CompradorDTO implements Serializable {

    private String nombres;
    private String apellidos;
    private String dni;
    private String telefono;
    private String direccion;
    private String ruc; // RUC del comprador, opcional

    // Constructor vacío
    public CompradorDTO() {
    }

    // Constructor con todos los campos
    public CompradorDTO(String nombres, String apellidos, String dni, String telefono, String direccion, String ruc) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
        this.ruc = ruc;
    }

    // Getters y Setters
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    @Override
    public String toString() {
        return "CompradorDTO{" +
                "nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", dni='" + dni + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", ruc='" + ruc + '\'' +
                '}';
    }
}
