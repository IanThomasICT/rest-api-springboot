package com.ianthomas.restapidemo.rest.controller;

import com.ianthomas.restapidemo.persistence.model.Customer;
import com.ianthomas.restapidemo.rest.dto.CustomerDto;
import com.ianthomas.restapidemo.rest.dto.ResponseDto;
import com.ianthomas.restapidemo.service.CustomerService;
import com.ianthomas.restapidemo.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final SearchService searchService;
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    public CustomerController(CustomerService customerService, SearchService searchService) {
        this.customerService = customerService;
        this.searchService = searchService;
    }

    @GetMapping(produces = "application/json")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping(path = "{id}", produces = "application/json")
    public Customer getCustomer(@PathVariable Integer id) {
        return customerService.getCustomer(id);
    }

    @GetMapping(path = "/query", produces = "application/json")
    public List<Customer> queryCustomers(@RequestParam(required = false) String country,
                                         @RequestParam(required = false) String city){
        return customerService.queryCustomers(country, city);
    }

    @GetMapping(path = "/match", produces = "application/json")
    public ResponseDto queryMatchCustomers(@RequestParam(required = true) String index,
                                           @RequestParam(required = true) String field,
                                           @RequestParam(required = true) String matchParam
                                              ){

        return new ResponseDto("success", "Displaying top hits for match query", searchService.queryMatchCustomers(index, field, matchParam));
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
