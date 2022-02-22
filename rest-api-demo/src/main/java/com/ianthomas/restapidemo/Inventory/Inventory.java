package com.ianthomas.restapidemo.Inventory;

public class Inventory {
    String  storeLocation;
    int     storeId;
    int     beefCount;
    int     chickenCount;
    int     milkCount;
    float   FridgeTemp;

    public Inventory(String storeLocation, int storeId, int beefCount, int chickenCount, int milkCount, float fridgeTemp) {
        this.storeLocation = storeLocation;
        this.storeId = storeId;
        this.beefCount = beefCount;
        this.chickenCount = chickenCount;
        this.milkCount = milkCount;
        FridgeTemp = fridgeTemp;
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
        return FridgeTemp;
    }

    public void setFridgeTemp(float fridgeTemp) {
        FridgeTemp = fridgeTemp;
    }
}


