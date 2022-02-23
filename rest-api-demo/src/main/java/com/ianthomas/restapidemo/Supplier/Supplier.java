package com.ianthomas.restapidemo.Supplier;

import javax.persistence.*;

@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @SequenceGenerator(
            name = "supplier_sequence",
            sequenceName = "supplier_sequence",
            allocationSize = 10
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "supplier_sequence"
    )
    private Integer supplierId;
    private String      name;
    private String  location;

    public Supplier(Integer supplierId, String name, String location) {
        this.supplierId = supplierId;
        this.name = name;
        this.location = location;
    }

    public Supplier() {

    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
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
