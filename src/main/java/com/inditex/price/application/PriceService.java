package com.inditex.price.application;

import com.inditex.price.application.dto.PriceDto;
import com.inditex.price.domain.Price;

import com.inditex.price.domain.service.PriceRepository;
import com.inditex.price.infrastructure.mapper.PriceMapper;
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

    public Optional<PriceDto> findValidPrices(Long brandId, Long productId, LocalDateTime date) {

        List<Price> pricesList  = priceRepository.findValidPrices(brandId, productId, date);

        if (!pricesList.isEmpty()) {
            return pricesList.stream().findFirst().map(PriceMapper::toDto);
        }
        return Optional.empty();
    }
}
