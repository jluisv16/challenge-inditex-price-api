package com.inditex.price.domain.service;

import com.inditex.price.domain.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {

    Optional<Price> findApplicablePrice(Long brandId, Long productId, LocalDateTime date);

    //List<Price> findAllPricesByProduct(Long productId);
}
