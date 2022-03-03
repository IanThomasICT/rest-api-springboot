package com.ianthomas.restapidemo.rest.controller;

import com.ianthomas.restapidemo.persistence.exception.ApplicationException;
import com.ianthomas.restapidemo.persistence.model.Customer;
import com.ianthomas.restapidemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
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
        try {
            customerService.addCustomer(customer);
        } catch (ApplicationException ae){

        } catch (Exception e){
        }
    }

    @PutMapping(path = "{id}")
    public void updateCustomer(@PathVariable("id") Integer id,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String email) {
        try {
            customerService.updateCustomer(id, name, email);
            // Statement 1
            // Statement 2
            // Statement 3
        } catch (ApplicationException ae){
            // Any error thrown from Service layer is caught here
        } catch (Exception e){
            // logger.error(e)
            throw HttpResponse<>
        }
    }

    @DeleteMapping(path = "{id}")
    public void deleteCustomer(@PathVariable("id") Integer id) {
        try {
            customerService.deleteCustomer(id);
        } catch (ApplicationException ae){

        } catch (Exception e){

        }
    }


}
