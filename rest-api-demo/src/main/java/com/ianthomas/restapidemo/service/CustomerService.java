package com.ianthomas.restapidemo.service;

import com.ianthomas.restapidemo.exception.ItemAlreadyExistsException;
import com.ianthomas.restapidemo.exception.ItemNotFoundException;
import com.ianthomas.restapidemo.persistence.model.Customer;
import com.ianthomas.restapidemo.persistence.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomer(Integer id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent())
            return customer.get();
        else
            throw new ItemNotFoundException("Customer " + id + " not found");
    }

    public Customer addCustomer(Customer customer) {
        Boolean exists = customerRepository.existsById(customer.getId());
        if (exists) {
            throw new ItemAlreadyExistsException("Customer already exists");
        }
        customerRepository.save(customer);
        LOG.info("Added Customer {} to repository",customer.getId());
        return customer;
    }

    public void deleteCustomer(Integer id) {
        Boolean exists = customerRepository.existsById(id);
        if (!exists) {
            throw new ItemNotFoundException("Customer does not exist");
        }
        customerRepository.deleteById(id);
        LOG.info("Deleted Customer {}",id);
    }
}
