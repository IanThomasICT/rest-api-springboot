package com.ianthomas.restapidemo.persistence.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
public class Inventory {
    @Id
    @SequenceGenerator(name = "inv", initialValue = 1000)
    @GeneratedValue (strategy = GenerationType.AUTO, generator = "inv")
    private int productId;            // Primary Key
    private String itemName;          // Unique
    private float price;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne (optional = false, fetch = FetchType.LAZY)
    private Supplier supplier;                   // FK of Supplier

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "purchasedBy")
    private Customer customer;                   // FK of Customer



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


