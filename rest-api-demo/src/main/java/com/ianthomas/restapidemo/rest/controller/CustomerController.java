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
        if (customer.getId() < 0) { throw new IllegalStateException("Invalid: id must be a positive value"); }
        customerService.addCustomer(customer);
    }

    @PutMapping(path = "{id}")
    public void updateCustomer(@PathVariable("id") Integer id,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String email) {
        if (id < 0) { throw new IllegalStateException("Invalid: id must be a positive value"); }
        customerService.updateCustomer(id, name, email);
    }

    @DeleteMapping(path = "{id}")
    public void deleteCustomer(@PathVariable("id") Integer id) {
        if (id < 0) { throw new IllegalStateException("Invalid: id must be a positive value"); }
        customerService.deleteCustomer(id);
    }


}
