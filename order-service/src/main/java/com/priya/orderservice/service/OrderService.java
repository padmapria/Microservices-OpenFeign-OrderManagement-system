package com.priya.orderservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.priya.orderservice.common.OrderItem;
import com.priya.orderservice.common.TransactionResponse;
import com.priya.orderservice.feignclient.OrderItemClient;
import com.priya.orderservice.exception.OrderNotFoundException;
import com.priya.orderservice.model.Order;
import com.priya.orderservice.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class OrderService {

    Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private OrderItemClient client;

    /*
     Finding an order
     */
    public Order getOrder(long id) throws JsonProcessingException {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        logger.info("Result for getting Order by id : " + new ObjectMapper().writeValueAsString(order));

        List<OrderItem> items = client.getOrderItemByOrderId(id);
        logger.info("FeignClient response for number of Orderitem retrived by Order id : " + new ObjectMapper().writeValueAsString(items.size()));
        order.setItems(items);
        return order;
    }

    /*
    creating a new order
     */
    public TransactionResponse save(@RequestBody Order order) throws JsonProcessingException {
        Order saved_order =null;
        long orderID = getOrderIdValue();
        order.setId(orderID);
        String message="Failed";

            if (!order.getItems().isEmpty()) {
                //setting the FK for order Items
                for (OrderItem orderItem: order.getItems()){
                    orderItem.setOrderId(orderID);
                    client.save(orderItem);
                }
                saved_order = orderRepository.save(order);
                logger.info("Order and order items are saved : " + new ObjectMapper().writeValueAsString(order));
                message="Success";
            }
        return new TransactionResponse(saved_order, message);
    }

    public long getOrderIdValue() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Instant instant = timestamp.toInstant();
        return instant.toEpochMilli();
    }

}