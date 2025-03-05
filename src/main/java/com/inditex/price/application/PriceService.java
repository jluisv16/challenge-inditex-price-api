package com.inditex.price.application;

import com.inditex.price.application.dto.PriceDto;
import com.inditex.price.domain.Price;
import com.inditex.price.domain.Product;

import com.inditex.price.domain.service.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PriceService {

    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Optional<PriceDto> getApplicablePrice(Long brandId, Long productId, LocalDateTime date) {
        // Lógica de negocio para obtener el precio del
        // producto según la fecha, producto y marca
        List<Price> pricesList  = priceRepository.findValidPrices(brandId, productId, date);

        /*if (!pricesList.isEmpty()) {
            return pricesList.stream().findFirst().map(this::toDto);
        }*/
        return null;
    }
}
