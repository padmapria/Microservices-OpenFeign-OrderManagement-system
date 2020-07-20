package com.priya.orderitemservice.exception;

public class OrderItemNotFoundException extends RuntimeException {

    public OrderItemNotFoundException(int id) {
        super(String.format("Order item with Id %d not found", id));
    }


}
