package com.ianthomas.restapidemo.rest.controller;

import com.ianthomas.restapidemo.persistence.model.Customer;
import com.ianthomas.restapidemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @PostMapping
    public void addCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
    }

    @PutMapping(path = "{id}")
    public void updateCustomer(@RequestParam int id,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String email,
                               @RequestParam(required = false) String item_name,
                               @RequestParam(required = false) Float item_price) {
        customerService.updateCustomer(id, name, email, item_name, item_price);
    }


}
