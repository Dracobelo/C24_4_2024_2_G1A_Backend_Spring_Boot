package com.tecsup.fruitcommerce.paq_models;

import jakarta.persistence.*;
import com.tecsup.fruitcommerce.paq_models.User;

@Entity
@Table(name = "Proveedor")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User user;

    @Column(name = "nombre_empresa")
    private String nombreEmpresa;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "ruc")
    private String ruc;

    @Column(name = "ubicacion")
    private String ubicacion;

    @Column(name = "horarios_atencion")
    private String horariosAtencion;

    // Constructor vacío
    public Proveedor() {}

    // Constructor con parámetros
    public Proveedor(Long idProveedor, User user, String nombreEmpresa, String telefono, String direccion, String ruc, String ubicacion, String horariosAtencion) {
        this.id = idProveedor;
        this.user = user;
        this.nombreEmpresa = nombreEmpresa;
        this.telefono = telefono;
        this.direccion = direccion;
        this.ruc = ruc;
        this.ubicacion = ubicacion;
        this.horariosAtencion = horariosAtencion;
    }

    public Long getIdProveedor() {
        return id;
    }

    public void setIdProveedor(Long idProveedor) {
        this.id = idProveedor;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

    @Override
    public String toString() {
        return "Proveedor{" +
                "id=" + id +
                ", usuario=" + user +
                ", nombreEmpresa='" + nombreEmpresa + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", ruc='" + ruc + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", horariosAtencion='" + horariosAtencion + '\'' +
                '}';
    }
}