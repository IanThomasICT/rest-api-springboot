package com.ianthomas.restapidemo.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ianthomas.restapidemo.exception.InvalidArgumentsException;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
public class Supplier {
    @Id @SequenceGenerator(name = "sup", sequenceName = "sup", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sup") private Integer id;
    @NotNull private String name;
    @NotNull private String location;

    @OneToMany (mappedBy = "supplier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Inventory> exports = new HashSet<>();

    public Supplier(String name, String location, Set<Inventory> exports) {
        this.name = name;
        this.location = location;
        this.exports = exports;
    }

    public Supplier(String name, String location) {
        try {
            Objects.requireNonNull(name,"Invalid Supplier name: null value");
            Objects.requireNonNull(location,"Invalid Supplier location: null value");
            if (name.length() >= 5) { this.name = name; }       // Arbitrary length
            else { throw new InvalidArgumentsException("Supplier name must be 5 characters or more");}

            if (location.length() >= 5) { this.location = location; }
            else { throw new InvalidArgumentsException("Supplier location must be 5 characters or more");}
        } catch (NullPointerException e) {
            throw new InvalidArgumentsException(e.getMessage(), e);
        }
    }

    public Supplier() {

    }

    public Supplier Supplier(Supplier s) {
        this.id = s.getId();
        this.name = s.getName();
        this.location = s.getLocation();
        this.exports = s.getExports();
        return this;
    }

    public Integer getId() {
        return id;
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

    public Set<Inventory> getExports() {
        return exports;
    }

    public void setExports(Set<Inventory> exports) {
        this.exports = exports;

        for (Inventory i : exports){
            i.setSupplier(this);
        }
    }
}
