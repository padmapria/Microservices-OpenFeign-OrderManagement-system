package com.priya.orderservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.priya.orderservice.common.TransactionResponse;
import com.priya.orderservice.exception.OrderNotFoundException;
import com.priya.orderservice.model.Order;
import com.priya.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping(value = "/{id}")
    public Order getOrder(@PathVariable("id") long id) throws JsonProcessingException {
        return orderService.getOrder(id);
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> save(@Valid @RequestBody Order order) throws JsonProcessingException{
        TransactionResponse response= orderService.save(order);
        return new ResponseEntity<TransactionResponse>(response, HttpStatus.OK);
    }
}
