package com.ianthomas.restapidemo.rest.dto;

import com.ianthomas.restapidemo.persistence.model.Supplier;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serializable;
import java.util.Objects;

public class SupplierDto implements Serializable {
    private static final long serialVersionUID = 7279580124456060304L;

    private String name;
    private String location;
    private Supplier supplier = null;

    public SupplierDto(String name, String location) {
        this.name = name;
        this.location = location;
        this.supplier = new Supplier(name, location);
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Supplier getSupplier() {
        return supplier;
    }

}
