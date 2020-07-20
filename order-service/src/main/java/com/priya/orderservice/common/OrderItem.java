package com.priya.orderservice.common;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private int id;
    private String productCode;
    private String productName;
    private String quantity;
    private long orderId;
}
