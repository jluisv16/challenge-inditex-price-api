package com.inditex.price.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Product {

    private final Long id;
    private final String descripcion;
    private Brand brand;
    private final List<Price> prices;

    public Product(Long id, String descripcion, Brand brand, List<Price> prices) {
        if (id == null || descripcion == null || descripcion.isEmpty() || brand == null || prices == null || prices.isEmpty()) {
            throw new IllegalArgumentException("Product id, descripcion, brand and prices must not be null or empty");
        }
        this.id = id;
        this.descripcion = descripcion;
        this.brand = brand;
        this.prices = prices;
    }

    // Métodos de negocio
    public void addPrice(Price price) {
        this.prices.add(price);
    }

    public void changeBrand(Brand newBrand) {
        this.brand = newBrand;
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
