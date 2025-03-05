package com.inditex.price.application.exception;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException{

    private final String productId;

    public ProductNotFoundException(String productId) {
        this.productId = productId;
    }


}
