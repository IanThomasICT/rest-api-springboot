package com.ianthomas.restapidemo.Inventory;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

// TODO Rename Inventory

// Maps class onto JPA database
@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @SequenceGenerator(
            name = "inventory_sequence",
            sequenceName = "inventory_sequence",
            allocationSize = 5,
            initialValue = 1000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "inventory_sequence"
    )
    private Integer       productId;    // Primary Key
    private Integer      supplierId;    // Foreign Key of Supplier
    private String         itemName;

    public Inventory(Integer productId, Integer supplierId, String itemName) {
        this.productId = productId;
        this.supplierId = supplierId;
        this.itemName = itemName;
    }

    public Inventory() {
        this.productId = 0;
        this.supplierId = 0;
        this.itemName = null;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}


