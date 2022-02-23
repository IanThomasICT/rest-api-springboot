package com.ianthomas.restapidemo.Inventory;

import javax.persistence.*;
import java.util.List;

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
    private Integer     storeId;
    private String  storeLocation;
    private Integer     beefCount;
    private Integer     chickenCount;
    private Integer     milkCount;
    private Float   fridgeTemp;

    public Inventory(int storeId, String storeLocation, int beefCount, int chickenCount, int milkCount, float fridgeTemp) {
        this.storeId = storeId;
        this.storeLocation = storeLocation;
        this.beefCount = beefCount;
        this.chickenCount = chickenCount;
        this.milkCount = milkCount;
        this.fridgeTemp = fridgeTemp;
    }

    public Inventory() {
        this.storeId = 0;
        this.storeLocation = "Wichita, KS";
        this.beefCount = 1;
        this.chickenCount = 1;
        this.milkCount = 1;
        this.fridgeTemp = 32.0f;
    }

    public void getInventory() {
        System.out.println(List.of(this.storeId, this.storeLocation,this.beefCount, this.chickenCount, this.milkCount, this.fridgeTemp));
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
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


