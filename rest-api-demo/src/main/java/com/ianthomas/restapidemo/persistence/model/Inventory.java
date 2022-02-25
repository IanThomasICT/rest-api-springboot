package com.ianthomas.restapidemo.persistence.model;

import javax.persistence.*;


@Entity
@Table
public class Inventory {
    @Id
    @SequenceGenerator(name = "inv", initialValue = 1000)
    @GeneratedValue (strategy = GenerationType.AUTO, generator = "inv")
    private int productId;            // Primary Key
    private String itemName;          // Unique
    private float price;

    @ManyToOne private Supplier supplier;        // FK of Supplier

    @ManyToOne
    @JoinColumn(name = "purchasedBy")
    private Customer customer;                   // FK of Customer







    public Inventory(String itemName, float price) {
        this.itemName = itemName;
        this.price = price;
    }

    public Inventory(String itemName, float price, Supplier supplier) {
        this.itemName = itemName;
        this.price = price;
        this.supplier = supplier;
    }

    public Inventory(String itemName, float price, Supplier supplier, Customer customer) {
        this.itemName = itemName;
        this.price = price;
        this.supplier = supplier;
        this.customer = customer;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}


