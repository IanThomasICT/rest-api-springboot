package com.ianthomas.restapidemo.Inventory;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

// TODO Rename Inventory

// Maps class onto JPA database
@Entity
@Table
public class Inventory {
    @Id
    @SequenceGenerator(
            name = "inventory_sequence",
            sequenceName = "inventory_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "inventory_sequence"
    )
    private Integer    inventoryId;    // Primary Key
    private UUID        supplierId;    // Foreign Key of Supplier
    private String   storeLocation;
    private Integer      beefCount;
    private Integer   chickenCount;
    private Integer      milkCount;
    private Float       fridgeTemp;

    public Inventory(Integer inventoryId, String storeLocation, Integer beefCount, Integer chickenCount, Integer milkCount, Float fridgeTemp) {
        this.inventoryId = inventoryId;
        this.supplierId = UUID.randomUUID();
        this.storeLocation = storeLocation;
        this.beefCount = beefCount;
        this.chickenCount = chickenCount;
        this.milkCount = milkCount;
        this.fridgeTemp = fridgeTemp;
    }

    public Inventory() {
        this.inventoryId = 0;
        this.supplierId = UUID.randomUUID();
        this.storeLocation = "Wichita, KS";
        this.beefCount = 100;
        this.chickenCount = 100;
        this.milkCount = 100;
        this.fridgeTemp = 40.0f;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }

    public UUID getSupplierId() {
        return supplierId;
    }

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getBeefCount() {
        return beefCount;
    }

    public void setBeefCount(int beefCount) {
        this.beefCount = beefCount;
    }

    public int getChickenCount() {
        return chickenCount;
    }

    public void setChickenCount(int chickenCount) {
        this.chickenCount = chickenCount;
    }

    public int getMilkCount() {
        return milkCount;
    }

    public void setMilkCount(int milkCount) {
        this.milkCount = milkCount;
    }

    public float getFridgeTemp() {
        return fridgeTemp;
    }

    public void setFridgeTemp(float fridgeTemp) {
        this.fridgeTemp = fridgeTemp;
    }



}


