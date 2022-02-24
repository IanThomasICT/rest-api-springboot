package com.ianthomas.restapidemo.persistence.model;

import com.ianthomas.restapidemo.persistence.model.Inventory;

import javax.persistence.*;

@Entity
@Table
public class Supplier {
    @Id private Integer id;
    private String name;
    private String location;

    public Supplier(Integer id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public Supplier() {

    }

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
