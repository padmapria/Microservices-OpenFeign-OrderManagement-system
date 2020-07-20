package com.priya.orderitemservice.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.validation.ConstraintViolationException;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@ControllerAdvice
public class OrderItemExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = OrderItemNotFoundException.class)
    public ResponseEntity<Object> exception(OrderItemNotFoundException exception) {
        return new ResponseEntity<>("Order item not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = OrderItemsNotFoundException.class)
    public ResponseEntity<Object> exception(OrderItemsNotFoundException exception) {
        return new ResponseEntity<>("Order items not found for the OrderId", HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation Failed",
                ex.getBindingResult().toString());
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

}
