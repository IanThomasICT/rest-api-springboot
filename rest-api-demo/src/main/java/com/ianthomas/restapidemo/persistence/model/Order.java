package com.ianthomas.restapidemo.persistence.model;

import javax.persistence.*;
import java.time.LocalDate;

public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne private Integer itemId;         // FK from Inventory
    @ManyToOne (cascade = CascadeType.ALL) private Integer customerId;     // FK from Customer
    private LocalDate date;



}
