package com.inditex.price.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Product {

    private final Long id;
    private final String descripcion;
    private final List<Price> prices;

    public Product(Long id, String descripcion, List<Price> prices) {
        if (id == null || descripcion == null || descripcion.isEmpty() || prices == null || prices.isEmpty()) {
            throw new IllegalArgumentException("Product id, descripcion and prices must not be null or empty");
        }
        this.id = id;
        this.descripcion = descripcion;
        this.prices = prices;
    }

    // Métodos de negocio
    public void addPrice(Price price) {
        this.prices.add(price);
    }

    public List<Price> getPricesOnDate(LocalDate date) {
        // Devuelve precios activos en una fecha específica
        return prices.stream()
                .filter(price -> price.isActiveOnDate(date))
                .collect(Collectors.toList());
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
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
                ", prices=" + prices +
                '}';
    }

}
