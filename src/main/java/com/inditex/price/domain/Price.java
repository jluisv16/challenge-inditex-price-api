package com.inditex.price.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Price {

    private final Long id;
    private final Long brandId;
    private final Long productId;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final Integer priceList;
    private final Integer priority;
    private BigDecimal price;
    private final String currency;

    public Price(Long id, Long brandId, Long productId, LocalDateTime startDate,
                 LocalDateTime endDate, Integer priceList, Integer priority,
                 BigDecimal price, String currency) {
        this.id = Objects.requireNonNull(id, "Id must not be null");
        this.brandId = Objects.requireNonNull(brandId, "BrandId must not be null");
        this.productId = Objects.requireNonNull(productId, "ProductId must not be null");
        this.startDate = Objects.requireNonNull(startDate, "StartDate must not be null");
        this.endDate = Objects.requireNonNull(endDate, "EndDate must not be null");
        this.priceList = Objects.requireNonNull(priceList, "PriceList must not be null");
        this.priority = Objects.requireNonNull(priority, "Priority must not be null");
        this.price = Objects.requireNonNull(price, "Price must not be null");
        this.currency = Objects.requireNonNull(currency, "Currency must not be null");
    }

    public boolean isApplicable(LocalDateTime date) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }

    public void updatePrice(BigDecimal newPrice) {
        if (newPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }
        this.price = newPrice;
    }

    // Getters
    public Long getId() { return id; }
    public Long getBrandId() { return brandId; }
    public Long getProductId() { return productId; }
    public LocalDateTime getStartDate() { return startDate; }
    public LocalDateTime getEndDate() { return endDate; }
    public Integer getPriceList() { return priceList; }
    public Integer getPriority() { return priority; }
    public BigDecimal getPrice() { return price; }
    public String getCurrency() { return currency; }
}
