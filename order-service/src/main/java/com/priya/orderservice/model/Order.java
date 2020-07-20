package com.priya.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.priya.orderservice.common.OrderItem;

import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ordr")
public class Order {

    @Id
    private long id;

    @NotNull
    @Size(min=5, message="customerName should have atleast 4 characters")
    private String customerName;

    @NotNull
    @Size(min=5, message="customerName should have atleast 5 characters")
    private String shippingAddress;

    private double total;
    @Transient
    private List<OrderItem> items;

}