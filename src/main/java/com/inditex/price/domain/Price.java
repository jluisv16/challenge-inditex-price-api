package com.inditex.price.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Price {

    private final Long id;
    private final Product product;  // Solo referencia a Product
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Integer priceList;
    private final BigDecimal price;  // Precio como BigDecimal, no como Value Object
    private final String currency;   // Moneda como String (código ISO)

    // Constructor
    public Price(Long id, Product product, LocalDate startDate, LocalDate endDate,
                 Integer priceList, BigDecimal price, String currency) {
        this.id = id;
        this.product = product;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.price = price;
        this.currency = currency;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Integer getPriceList() {
        return priceList;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    // Método para verificar si un precio es válido para una fecha específica
    public boolean isValidForDate(LocalDate date) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }

    // Método para cambiar el precio
    public Price changePrice(BigDecimal newPrice) {
        if (newPrice == null || newPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que 0");
        }
        return new Price(this.id, this.product, this.startDate, this.endDate,
                this.priceList, newPrice, this.currency);
    }

    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", product=" + product +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", priceList=" + priceList +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price1 = (Price) o;
        return id.equals(price1.id) && product.equals(price1.product) && startDate.equals(price1.startDate) &&
                endDate.equals(price1.endDate) && priceList.equals(price1.priceList) &&
                price.equals(price1.price) && currency.equals(price1.currency);
    }

    @Override
    public int hashCode() {
        return 31 * id.hashCode() + product.hashCode() + startDate.hashCode() + endDate.hashCode() +
                priceList.hashCode() + price.hashCode() + currency.hashCode();
    }
}
