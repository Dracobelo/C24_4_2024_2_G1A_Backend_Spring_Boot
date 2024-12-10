package com.tecsup.fruitcommerce.paq_dtos;

public class ProveedorDTO_proveedor {

    private String nombreEmpresa;
    private String telefono;
    private String direccion;
    private String ruc;
    private String ubicacion;
    private String horariosAtencion;

    // Constructor vacío
    public ProveedorDTO_proveedor() {
    }

    // Constructor con parámetros
    public ProveedorDTO_proveedor(String nombreEmpresa, String telefono, String direccion, String ruc, String ubicacion, String horariosAtencion) {
        this.nombreEmpresa = nombreEmpresa;
        this.telefono = telefono;
        this.direccion = direccion;
        this.ruc = ruc;
        this.ubicacion = ubicacion;
        this.horariosAtencion = horariosAtencion;
    }

    // Getters y Setters
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getHorariosAtencion() {
        return horariosAtencion;
    }

    public void setHorariosAtencion(String horariosAtencion) {
        this.horariosAtencion = horariosAtencion;
    }

    // Método toString para depuración
    @Override
    public String toString() {
        return "ProveedorDTO_proveedor{" +
                "nombreEmpresa='" + nombreEmpresa + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", ruc='" + ruc + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", horariosAtencion='" + horariosAtencion + '\'' +
                '}';
    }
}
