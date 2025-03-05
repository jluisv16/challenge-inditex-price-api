package com.inditex.price.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Product {

    private final Long id;
    private final String descripcion;
    private Brand brand;
    private List<Price> prices;

    public Product(Long id, String descripcion, Brand brand, List<Price> prices) {
        if (id == null || descripcion == null || descripcion.isEmpty() || brand == null || prices == null || prices.isEmpty()) {
            throw new IllegalArgumentException("Product id, descripcion, brand and prices must not be null or empty");
        }
        this.id = id;
        this.descripcion = descripcion;
        this.brand = brand;
        this.prices = prices;
    }

    public Product(Long id, String descripcion, Brand brand) {
        if (id == null || descripcion == null || descripcion.isEmpty() || brand == null) {
            throw new IllegalArgumentException("Product id, descripcion and brand  must not be null or empty");
        }
        this.id = id;
        this.descripcion = descripcion;
        this.brand = brand;

    }

    public Product(Long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    // Métodos de negocio
    public void addPrice(Price price) {
        this.prices.add(price);
    }

    public List<Price> getPricesOnDate(LocalDateTime date) {
        // Devuelve precios activos en una fecha específica
        return prices.stream()
                .filter(price -> price.isValidForDate(date))
                .collect(Collectors.toList());
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Brand getBrand() {
        return brand;
    }

    public List<Price> getPrices() {
        return prices;
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
                ", brand=" + brand +
                ", prices=" + prices +
                '}';
    }

}
