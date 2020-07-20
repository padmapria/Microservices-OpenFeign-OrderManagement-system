package com.priya.orderitemservice.repo;

import com.priya.orderitemservice.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    @Query("SELECT u FROM OrderItem u  WHERE u.orderId = ?1" )
    List<OrderItem> getAllByOrderId(long orderId);

}
