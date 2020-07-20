package com.priya.orderitemservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.priya.orderitemservice.model.OrderItem;
import com.priya.orderitemservice.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
@RequestMapping("/orderItem")
public class OrderItemController {

    @Autowired
    OrderItemService orderService;

    //Fetching orderItem based on orderId
    @GetMapping(value = "/orderId/{id}")
    public List<OrderItem> getAllByOrderId(@PathVariable("id") long id) throws JsonProcessingException {
        return orderService.getAllByOrderId(id);
    }

    //Saving order item
    @PostMapping
    public ResponseEntity<OrderItem> save(@Valid @RequestBody OrderItem order) throws JsonProcessingException{
        orderService.save(order);
        return new ResponseEntity<OrderItem>(order, HttpStatus.OK);
    }


    /* Not used */
    // Fetching orderItem based on Id
    @GetMapping(value = "/{id}")
    public OrderItem getOrderItem(@PathVariable("id") int id) {
        return orderService.getOrderItem(id);
    }

    @PostMapping(value = "/saveAll")
    public List<OrderItem>  saveAll(@Valid @RequestBody List<OrderItem> orderItemList) throws JsonProcessingException {
        return orderService.saveAll(orderItemList);
    }
}
