package com.ianthomas.restapidemo.rest.controller;

import com.ianthomas.restapidemo.persistence.model.Customer;
import com.ianthomas.restapidemo.persistence.model.Inventory;
import com.ianthomas.restapidemo.rest.dto.ResponseDto;
import com.ianthomas.restapidemo.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/customers")
public class CustomerController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(produces = {"application/json", "text/json"})
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @DeleteMapping(path = "{id}", produces = {"application/json", "text/json"})
    public ResponseDto deleteCustomer(@PathVariable("id") Integer id) {
        customerService.deleteCustomer(id);
        LOG.info("Deleted Customer {}",id);
        return new ResponseDto("success", "Customer deleted successfully.");
    }

    @PostMapping(consumes = {"application/json"}, produces = {"application/json", "text/json"})
    public ResponseDto addCustomer(@RequestBody Customer customer) {
        return new ResponseDto("success", "Customer added successfully.", customerService.addCustomer(customer));
    }

    @PutMapping(path = "{id}", consumes = {"application/json"}, produces = {"application/json", "text/json"})
    public ResponseDto updateCustomer(@PathVariable("id") Integer id,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String email,
                               @RequestParam(required = false) Set<Inventory> items) {
        return new ResponseDto("success", "Customer updated successfully.",customerService.updateCustomer(id, name, email, items));
    }



}
