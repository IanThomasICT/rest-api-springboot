package com.ianthomas.restapidemo.Customer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue
    private Integer customerId;
    private String  customerName;
    private Date    dob;

    public Customer(Integer customerId, String customerName, Date dob) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.dob = dob;
    }

    public Customer() {
        this.customerId = 0;
        this.customerName = null;
        this.dob = null;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
