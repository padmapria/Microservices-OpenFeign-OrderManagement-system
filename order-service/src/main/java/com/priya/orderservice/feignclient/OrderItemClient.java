package com.priya.orderservice.feignclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.priya.orderservice.common.OrderItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@FeignClient("ORDER-ITEM-SERVICE")
public interface OrderItemClient {

    @GetMapping(value = "/orderItem/orderId/{id}")
    public List<OrderItem> getOrderItemByOrderId(@PathVariable("id") long id) throws JsonProcessingException;

    @PostMapping(value = "/orderItem")
    public ResponseEntity<OrderItem> save(@Valid @RequestBody OrderItem order) throws JsonProcessingException;

    @PostMapping(value = "/saveAll")
    public List<OrderItem> saveAll(@RequestBody List<OrderItem> orderItemList) throws JsonProcessingException;
}
