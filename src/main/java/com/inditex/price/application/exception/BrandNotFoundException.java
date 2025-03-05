package com.inditex.price.application.exception;

import lombok.Getter;

@Getter
public class BrandNotFoundException extends RuntimeException{

    private final String brandId;

    public BrandNotFoundException(String brandId) {
        this.brandId = brandId;
    }


}
