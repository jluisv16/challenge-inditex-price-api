package com.inditex.price.infrastructure.mapper;


import com.inditex.price.application.dto.PriceDto;
import com.inditex.price.domain.Brand;
import com.inditex.price.domain.Price;
import com.inditex.price.domain.Product;
import com.inditex.price.infrastructure.entity.PriceEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PriceMapper {

    public static Price toDomain(PriceEntity priceEntity) {

        Brand brand = new Brand(priceEntity.getBrand().getId(), priceEntity.getBrand().getName());
        Product product = new Product(priceEntity.getProduct().getId(), priceEntity.getProduct().getName(), brand);

        return new Price(priceEntity.getId(), product, priceEntity.getStartDate(),
                priceEntity.getEndDate(), priceEntity.getPriceList(),
                priceEntity.getPrice(), priceEntity.getCurrency());
    }

    public static List<Price> toDomainList(List<PriceEntity> priceEntities) {
        return priceEntities.stream()
                .map(PriceMapper::toDomain)
                .collect(Collectors.toList());
    }


    public static PriceDto toDto(Price price) {
        return new PriceDto(
                price.getProduct().getBrand().getId(),
                price.getProduct().getId(),
                price.getStartDate(),
                price.getEndDate(),
                price.getPriceList(),
                price.getPrice(),
                price.getCurrency()
        );
    }
}
