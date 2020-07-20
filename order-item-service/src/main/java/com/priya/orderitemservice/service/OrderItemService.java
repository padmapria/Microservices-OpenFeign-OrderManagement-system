package com.priya.orderitemservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.priya.orderitemservice.exception.OrderItemNotFoundException;
import com.priya.orderitemservice.exception.OrderItemsNotFoundException;
import com.priya.orderitemservice.model.OrderItem;
import com.priya.orderitemservice.repo.OrderItemRepository;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class OrderItemService {

    Logger logger= LoggerFactory.getLogger(OrderItemService.class);

    @Autowired
    OrderItemRepository orderItemRepository;

    public OrderItem getOrderItem(int id){
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new OrderItemNotFoundException(id));
    }

    public List<OrderItem> getAllByOrderId(long id) throws JsonProcessingException {
        List<OrderItem> items = orderItemRepository.getAllByOrderId(id);
        if(items.isEmpty()){
            throw new OrderItemsNotFoundException(id);
        }
        logger.info("Result for getting Orderitem by Order id : "+new ObjectMapper().writeValueAsString(items));
        return items;
    }

    @Transactional
    public OrderItem save(OrderItem order) throws JsonProcessingException{
        logger.info("Saving all orderItems : "+new ObjectMapper().writeValueAsString(order));
        return orderItemRepository.save(order);
    }

    public List<OrderItem> saveAll(List<OrderItem> orderItemList) throws JsonProcessingException{
        List<OrderItem> items =this.orderItemRepository.saveAll(orderItemList);
        logger.info("Saving all orderItems : "+new ObjectMapper().writeValueAsString(items));
        return items;
    }


}
