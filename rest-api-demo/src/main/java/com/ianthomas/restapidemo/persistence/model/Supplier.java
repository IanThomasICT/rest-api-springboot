package com.ianthomas.restapidemo.persistence.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ianthomas.restapidemo.persistence.model.Inventory;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Supplier {
    @Id
    @SequenceGenerator(name = "sup", initialValue = 0, allocationSize = 10)
    @GeneratedValue (strategy = GenerationType.AUTO, generator = "sup")
    private Integer id;
    private String name;
    private String location;

    @JsonManagedReference
    @OneToMany (mappedBy = "supplier", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<Inventory> exports = new HashSet<>();



    public Supplier(String name, String location, Set<Inventory> exports) {
        this.name = name;
        this.location = location;
        this.exports = exports;
    }

    public Supplier(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public Supplier() {

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
