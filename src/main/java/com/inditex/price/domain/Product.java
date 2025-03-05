package com.inditex.price.domain;

import java.util.Objects;

public class Product {

    private final Long id;
    private final String descripcion;

    public Product(Long id, String descripcion) {
        if (id == null || descripcion == null || descripcion.isEmpty()) {
            throw new IllegalArgumentException("Product id and descripcion must not be null or empty");
        }
        this.id = id;
        this.descripcion = descripcion;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

}
