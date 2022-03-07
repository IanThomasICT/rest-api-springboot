package com.ianthomas.restapidemo.rest.dto;

import com.ianthomas.restapidemo.persistence.model.Supplier;

public class SupplierDto {
    // Used to construct supplier objects from JSON data since
    // JPA constructs using no-arg constructor if RequestBody is passed directly into entity

    private String name;
    private String location;

    public SupplierDto(String name, String location) {
        this.name = name;
        this.location = location;
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


    // Always constructs supplier using the two param constructor
    public Supplier toSupplier() {
        return new Supplier(this.name, this.location);
    }
}
