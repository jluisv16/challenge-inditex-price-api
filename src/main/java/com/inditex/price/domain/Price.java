package com.inditex.price.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Price {

    private final Long id;
    private final Product product;
    private final Brand brand;
    private final BigDecimal amount;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Price(Long id, Product product, Brand brand, BigDecimal amount, LocalDate startDate, LocalDate endDate) {
        if (id == null || product == null || brand == null || amount == null || startDate == null || endDate == null) {
            throw new IllegalArgumentException("Price, Product, Brand, Amount, StartDate and EndDate must not be null");
        }
        this.id = id;
        this.product = product;
        this.brand = brand;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Brand getBrand() {
        return brand;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    // Métodos adicionales de negocio
    public boolean isActiveOnDate(LocalDate date) {
        return !startDate.isAfter(date) && !endDate.isBefore(date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return Objects.equals(id, price.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", product=" + product.getDescripcion() +
                ", brand=" + brand.getDescripcion() +
                ", amount=" + amount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
