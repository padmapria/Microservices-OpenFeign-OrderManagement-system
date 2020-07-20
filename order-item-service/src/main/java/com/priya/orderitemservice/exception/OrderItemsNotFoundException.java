package com.priya.orderitemservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class OrderItemsNotFoundException extends RuntimeException {

    public OrderItemsNotFoundException(long id) {
        super(
                String.format("Order item with Id %d not found", id));
    }


}
