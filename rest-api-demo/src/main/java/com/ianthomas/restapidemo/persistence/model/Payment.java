package com.ianthomas.restapidemo.persistence.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Payment extends PersistentEntity {
    @Id @SequenceGenerator(name = "pay", initialValue = 273)
    @GeneratedValue(generator = "pay") Integer id;

    @ManyToOne(optional = false) Customer customer;
    @ManyToOne(optional = false) Employee employee;
    @NotNull Date paymentDate;
    @NotNull Double amount;

    public Payment(Customer customerId, Employee employeeId, Date paymentDate, Double amount) {
        this.customer = customerId;
        this.employee = employeeId;
        this.paymentDate = paymentDate;
        this.amount = amount;
    }

    public Payment() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}

