package com.nasr.productservice.core.exception;

public class ProductNotFoundException extends EntityNotFoundException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
