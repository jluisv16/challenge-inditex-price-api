package com.inditex.price.application.api;

import com.inditex.price.application.PriceService;
import com.inditex.price.application.dto.PriceDto;
import org.springframework.format.annotation.DateTimeFormat;
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

    @GetMapping("/api/prices")
    public ResponseEntity<PriceDto> getValidPrices(
            @RequestParam Long brandId,
            @RequestParam Long productId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {

        return priceService.findValidPrices(brandId, productId, date)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
