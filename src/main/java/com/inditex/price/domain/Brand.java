package com.inditex.price.domain;

import java.util.Objects;

public class Brand {

    private final Long id;
    private final String descripcion;

    public Brand(Long id, String descripcion) {
        if (id == null || descripcion == null || descripcion.isEmpty()) {
            throw new IllegalArgumentException("Brand id and descripcion must not be null or empty");
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
        Brand brand = (Brand) o;
        return Objects.equals(id, brand.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

}
