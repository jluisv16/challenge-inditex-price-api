package com.inditex.price.application.api;

import com.inditex.price.application.PriceService;
import com.inditex.price.application.dto.PriceDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/pricing/prices")
    public ResponseEntity<PriceDto> getValidPrices(
            @RequestParam Long brandId,
            @RequestParam Long productId,
            @RequestParam String date) {

        LocalDateTime dateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return priceService.findValidPrices(brandId, productId, dateTime)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
