package com.ianthomas.restapidemo.persistence.model;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;

public class Order {
    @Id @GeneratedValue private Integer        orderId;
    @OneToOne private Integer itemId;     // FK from Inventory
    @OneToOne private Integer customerId;     // FK from Customer
    private LocalDate orderDate;

    public Order(Integer orderId, LocalDate orderDate) {
        this.orderId = orderId;
        this.orderDate = orderDate;
    }

    public Order(Integer orderId) {
        this.orderId = orderId;
        this.orderDate = LocalDate.now();
    }
}
