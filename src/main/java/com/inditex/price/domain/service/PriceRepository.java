package com.inditex.price.domain.service;

import com.inditex.price.domain.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {

    List<Price> findValidPrices(Long brandId, Long productId, LocalDateTime date);

}
