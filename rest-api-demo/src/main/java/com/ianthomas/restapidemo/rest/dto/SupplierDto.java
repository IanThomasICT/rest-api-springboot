package com.ianthomas.restapidemo.rest.dto;

import com.ianthomas.restapidemo.exception.InvalidArgumentsException;
import com.ianthomas.restapidemo.persistence.model.Supplier;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serializable;
import java.util.Objects;

public class SupplierDto implements Serializable {
    private static final long serialVersionUID = 7279580124456060304L;

    private String name;
    private String location;

    public SupplierDto(String oneParam) {
        throw new InvalidArgumentsException("Invalid Supplier input: need two string values 'name' and 'location'");
    }

    public SupplierDto(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Supplier toSupplier(){
        return new Supplier(this.name, this.location);
    }

}
