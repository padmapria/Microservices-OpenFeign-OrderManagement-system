package com.priya.orderservice.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(long id) {
        super(String.format("Order with Id %d not found", id));
    }
}
