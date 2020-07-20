package com.priya.orderitemservice.model;
;
import lombok.*;
import org.hibernate.annotations.Generated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="OrderItem")
public class OrderItem {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=2, message="ProductCode should have atleast 2 characters")
    private String productCode;

    @NotNull
    @Size(min=3, message="ProductName should have atleast 3 characters")
    private String productName;

    @NotNull
    @Positive(message="Quantity should be 1 or more")
    private int quantity;

    @NotNull
    private long orderId;




}