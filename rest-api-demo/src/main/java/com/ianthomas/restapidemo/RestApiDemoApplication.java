package com.ianthomas.restapidemo;

import com.ianthomas.restapidemo.persistence.model.Customer;
import com.ianthomas.restapidemo.persistence.model.Inventory;
import com.ianthomas.restapidemo.persistence.repository.CustomerRepository;
import com.ianthomas.restapidemo.persistence.repository.InventoryRepository;
import com.ianthomas.restapidemo.persistence.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class RestApiDemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RestApiDemoApplication.class, args);
    }

    @Autowired private InventoryRepository inventoryRepository;
    @Autowired private CustomerRepository customerRepository;

    // Configures repositories from ground up
    @Override
    public void run(String[] args) throws Exception {
        Supplier mbi = new Supplier(10, "Midwest Beef Inc.", "Salina, KS");
        Supplier aus = new Supplier(20, "Austin Chicken Corp.", "Austin, TX");

        Inventory item1 = new Inventory("5lb Steak", 23.50f, mbi);
        Inventory item2 = new Inventory("2lb Chicken Breast", 13.99f, aus);
        Inventory item3 = new Inventory("5lb Rotisserie Chicken", 27.99f, aus);
        Inventory item4 = new Inventory("2lb Ribeye", 17.00f, mbi);

        Customer cust1 = new Customer("Jay Travis","jayT@gmail.com");
        Customer cust2 = new Customer("Ashton Smith", "ashSmith@gmail.com");

        inventoryRepository.saveAll(List.of(item1,item2,item3,item4));
        customerRepository.saveAll(List.of(cust1,cust2));

    }
}
