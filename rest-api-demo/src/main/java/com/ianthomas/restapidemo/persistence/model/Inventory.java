package com.ianthomas.restapidemo.persistence.model;

import javax.persistence.*;


@Entity
@Table
public class Inventory {
    @SequenceGenerator(name = "inventory_sequence", initialValue = 1000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "inventory_sequence")
    @Id private int productId;        // Primary Key
    private String itemName;          // Unique
    private float price;

    @OneToOne(cascade = CascadeType.ALL) private Supplier supplier;        // Foreign Key of Supplier

    public Inventory(String itemName, float price, Supplier supplier) {
        this.itemName = itemName;
        this.price = price;
        this.supplier = supplier;
    }

    public Inventory() {

    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}


