package com.ianthomas.restapidemo.persistence.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Customer {
    @Id @GeneratedValue private Integer id;
    private String name;
//    private LocalDate dob;            // Add when able to serialize JSON data
//    @Transient private Integer age;
    private String email;
    @OneToMany(targetEntity = Customer.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "purchasedBy", referencedColumnName = "id")
    private List<Inventory> items;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
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

    public List<Inventory> getItems() {
        return items;
    }

    public void setItems(List<Inventory> items) {
        this.items = items;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
