package com.inditex.price.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Price {

    private final Long id;
    private final Product product;  // Solo referencia a Product.
    private final BigDecimal amount;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Price(Long id, Product product, BigDecimal amount, LocalDate startDate, LocalDate endDate) {
        if (id == null || product == null || amount == null || startDate == null || endDate == null) {
            throw new IllegalArgumentException("Price, Product, Amount, StartDate and EndDate must not be null");
        }
        this.id = id;
        this.product = product;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Métodos de negocio
    public boolean isActiveOnDate(LocalDate date) {
        return !startDate.isAfter(date) && !endDate.isBefore(date);
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
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
                ", product=" + product.getDescripcion() +  // Aquí usamos la descripción del producto
                ", amount=" + amount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
