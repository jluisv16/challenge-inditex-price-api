package com.inditex.price.infrastructure.repository;


import com.inditex.price.domain.Price;
import com.inditex.price.domain.service.PriceRepository;
import com.inditex.price.infrastructure.entity.PriceEntity;
import com.inditex.price.infrastructure.mapper.PriceMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepositoryImpl extends JpaRepository<PriceEntity, Long>, PriceRepository {

    List<PriceEntity> findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
            Long brandId, Long productId, LocalDateTime startDate, LocalDateTime endDate);

    @Override
    default List<Price> findValidPrices(Long brandId, Long productId, LocalDateTime date) {
        List<PriceEntity> priceEntities = findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                brandId, productId, date, date);
        return PriceMapper.toDomainList(priceEntities);
    }
}
