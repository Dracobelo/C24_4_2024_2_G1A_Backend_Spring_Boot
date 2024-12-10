package com.tecsup.fruitcommerce.paq_dtos;

public class ProductoRequestDTO {
    private String nombre;
    private double precio;
    private Integer categoriaId;
    private Long proveedorId;
    private double stock; // Nuevo campo para el stock en kilogramos
    private String foto; // Nuevo campo para la URL de la foto

    public ProductoRequestDTO() {
    }

    // Constructor con parámetros, incluyendo los nuevos campos
    public ProductoRequestDTO(String nombre, double precio, Integer categoriaId, Long proveedorId, double stock, String foto) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoriaId = categoriaId;
        this.proveedorId = proveedorId;
        this.stock = stock;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Long getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Long proveedorId) {
        this.proveedorId = proveedorId;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
