package com.ianthomas.restapidemo.rest.controller;

import com.ianthomas.restapidemo.persistence.model.Customer;
import com.ianthomas.restapidemo.rest.dto.CustomerDto;
import com.ianthomas.restapidemo.rest.dto.ResponseDto;
import com.ianthomas.restapidemo.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(produces = "application/json")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping(path = "{id}", produces = "application/json")
    public Customer getCustomer(@PathVariable Integer id) {
        return customerService.getCustomer(id);
    }

    @PostMapping(produces = {"application/json", "text/json"})
    public ResponseDto addCustomer(@RequestBody CustomerDto customerDto) {
        return new ResponseDto("success", "Customer added successfully", customerService.addCustomer(customerDto.toEntity()));
    }

    @DeleteMapping(path = "{id}")
    public ResponseDto deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
        return new ResponseDto("success","Customer deleted successfully");
    }
}
