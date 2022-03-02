package com.ianthomas.restapidemo;

import com.ianthomas.restapidemo.persistence.model.Customer;
import com.ianthomas.restapidemo.persistence.model.Inventory;
import com.ianthomas.restapidemo.persistence.model.Supplier;
import com.ianthomas.restapidemo.persistence.repository.CustomerRepository;
import com.ianthomas.restapidemo.persistence.repository.InventoryRepository;
import com.ianthomas.restapidemo.persistence.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class RestApiDemoConfig {

    @Autowired private InventoryRepository inventoryRepository;
    @Autowired private CustomerRepository customerRepository;
    @Autowired private SupplierRepository supplierRepository;

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {

            // Create parents first
            Supplier sup1 = new Supplier("Midwest Beef Inc.", "Salina, KS");
            Supplier sup2 = new Supplier("Austin Chicken Corp.", "Austin, TX");
            Customer cus1 = new Customer("Jay Travis","jayT@gmail.com");
            Customer cus2 = new Customer("Ashton Smith", "ashSmith@gmail.com");

            supplierRepository.saveAll(List.of(sup1,sup2));
            customerRepository.saveAll(List.of(cus1,cus2));

            // Add inventory (Child)
            Inventory item1 = new Inventory("5lb Steak", 23.50f, sup1, cus1);
            Inventory item2 = new Inventory("2lb Chicken Breast", 13.99f, sup1, cus2);
            Inventory item3 = new Inventory("5lb Rotisserie Chicken", 27.99f, sup1);
            Inventory item4 = new Inventory("2lb Ribeye", 17.00f, sup2, cus1);
            Inventory item5 = new Inventory("1/4lb Patty", 7.00f, sup2);
            Inventory item6 = new Inventory("Dozen of Eggs", 5.00f, sup2);


            inventoryRepository.saveAll(List.of(item1,item2,item3,item4,item5,item6));
        };
    }
}

//            inventoryRepository.saveAll(List.of(item1,item2,item3,item4,item5,item6));
