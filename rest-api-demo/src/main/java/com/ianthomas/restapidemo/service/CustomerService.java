package com.ianthomas.restapidemo.service;

import com.ianthomas.restapidemo.persistence.exception.ApplicationException;
import com.ianthomas.restapidemo.persistence.model.Customer;
import com.ianthomas.restapidemo.persistence.model.Inventory;
import com.ianthomas.restapidemo.persistence.repository.CustomerRepository;
import com.ianthomas.restapidemo.persistence.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final InventoryRepository inventoryRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, InventoryRepository inventoryRepository) {
        this.customerRepository = customerRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findCustomersById();
    }

    public void addCustomer(Customer customer) throws ApplicationException {
        try {
            if (customer.getId() < 0) {
                throw new ApplicationException("Invalid: id must be a positive value");
            }
            Optional<Customer> idMatch = customerRepository.findCustomerById(customer.getId());
            if (idMatch.isPresent()) {
                throw new ApplicationException("Customer id " + customer.getId() + " already exists");
            }
            Optional<Customer> emailMatch = customerRepository.findCustomerByEmail(customer.getEmail());
            if (emailMatch.isPresent()) {
                throw new ApplicationException("Customer email " + customer.getEmail() + " already exists");
            }
            customerRepository.save(customer);
        } catch (ApplicationException ae){
            throw ae;
        } catch (Exception e) {

        }
    }

    @Transactional
    public void updateCustomer(int id, String name, String email) throws ApplicationException {
        try {
            if (id < 0) {
                throw new ApplicationException("Invalid: id must be a positive value");
            }
            Customer customer = customerRepository.findById(id)
                    .orElseThrow(() -> new ApplicationException("Customer id " + id + " does not exist"));

            if (!Objects.equals(customer.getName(), name) && name.length() > 0) {
                customer.setName(name);
            }
            if (!Objects.equals(customer.getEmail(), email) && email.length() > 0) {
                customer.setEmail(email);
            }

            System.out.println("Updated customer: " + id + ".");
        } catch (ApplicationException ae) {
            throw ae;
        } catch (Exception e) {
            // Log database error
            // logger.error(e)
            ApplicationException ae = new ApplicationException(e);
            throw ae;
        }
    }

    public void deleteCustomer(Integer id) throws ApplicationException {
        try {
            if (id < 0) {
                throw new ApplicationException("Invalid: id must be a positive value");
            }
            boolean exists = customerRepository.existsById(id);
            if (exists) {
                customerRepository.deleteById(id);
            }

            System.out.println("Deleted customer: " + id + ".");
        } catch (ApplicationException ae) {
            throw ae;
        } catch (Exception e) {
            // Log database error
            // logger.error(e)
            ApplicationException ae = new ApplicationException(e);
            throw ae;
        }
    }
}
