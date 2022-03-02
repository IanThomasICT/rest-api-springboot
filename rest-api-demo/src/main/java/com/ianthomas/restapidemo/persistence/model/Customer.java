package com.ianthomas.restapidemo.persistence.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Customer {
    @Id @GeneratedValue private Integer id;
    private String name;
    private String email;

    @JsonManagedReference
    @OneToMany(mappedBy = "customer", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<Inventory> items = new HashSet<>();

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Customer(String name, String email, Set<Inventory> items) {
        this.name = name;
        this.email = email;
        this.items = items;
    }

    public Customer() {

    }

    // Getters are used by JPA to populate repository data

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Inventory> getItems() {
        return items;
    }

    public void setItems(Set<Inventory> items) {
        this.items = items;

        for (Inventory i : items){
            i.setCustomer(this);
        }
    }
}
