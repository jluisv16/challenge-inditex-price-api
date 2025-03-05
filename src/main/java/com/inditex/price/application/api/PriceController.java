package com.inditex.price.application.api;

import com.inditex.price.application.PriceService;
import com.inditex.price.application.dto.PriceDto;
import com.inditex.price.application.exception.ProductNotFoundException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

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

        // Llamar al servicio para obtener los precios válidos
        Optional<PriceDto> validPrice = priceService.findValidPrices(brandId, productId, date);

        if (validPrice.isPresent()) {
            return ResponseEntity.ok(validPrice.get());
        } else {
            if(Objects.isNull(productId)){
                throw new ProductNotFoundException("Se requere un producto" + productId);
            }
            if(Objects.isNull(brandId)){
                throw new ProductNotFoundException("Se requiere un brand " + brandId);
            }
        }

        return ResponseEntity.noContent().build();
    }

}
