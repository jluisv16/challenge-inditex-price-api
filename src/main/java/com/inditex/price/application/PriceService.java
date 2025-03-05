package com.inditex.price.application;

import com.inditex.price.domain.Price;
import com.inditex.price.domain.Product;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PriceService {

    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    /**
     * Método que obtiene el precio de un producto según la fecha, el producto y la marca.
     */
    public Optional<Price> getPriceForProduct(Product product, LocalDate date) {
        List<Price> prices = priceRepository.findPricesForProduct(product, date);
        return prices.stream()
                .filter(price -> price.getProduct().equals(product) && price.getStartDate().isBefore(date) && price.getEndDate().isAfter(date))
                .findFirst();
    }

    /**
     * Método que obtiene todos los precios de un producto en un rango de fechas.
     */
    public List<Price> getPricesForProductAndDateRange(Product product, LocalDate startDate, LocalDate endDate) {
        return priceRepository.findPricesForProductAndDateRange(product, startDate, endDate);
    }

    /**
     * Método que guarda un nuevo precio para un producto.
     */
    public Price savePrice(Price price) {
        return priceRepository.save(price);
    }
}
