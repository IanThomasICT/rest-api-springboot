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
            // Add inventory
            Inventory item1 = new Inventory("5lb Steak", 23.50f);
            Inventory item2 = new Inventory("2lb Chicken Breast", 13.99f);
            Inventory item3 = new Inventory("5lb Rotisserie Chicken", 27.99f);
            Inventory item4 = new Inventory("2lb Ribeye", 17.00f);
            Inventory item5 = new Inventory("1/4lb Patty", 7.00f);
            Inventory item6 = new Inventory("Dozen of Eggs", 5.00f);
            inventoryRepository.saveAllAndFlush(List.of(item1, item2, item3, item4, item5, item6));


            // TODO Fix set methods to sync with existing inventory items
            // Setup Suppliers and Customers
            Supplier mbi = new Supplier("Midwest Beef Inc.", "Salina, KS");
//                mbi.setExports(Set.of(item1, item4, item5));
            Supplier aus = new Supplier("Austin Chicken Corp.", "Austin, TX");
//                aus.setExports(Set.of(item2, item6));
            Customer cus1 = new Customer("Jay Travis","jayT@gmail.com");
//                cus1.setItems(Set.of(item1, item3, item5));
            Customer cus2 = new Customer("Ashton Smith", "ashSmith@gmail.com");
//                cus2.setItems(Set.of(item2, item5));


            supplierRepository.saveAll(List.of(mbi,aus));
            customerRepository.saveAll(List.of(cus1,cus2));

        };
    }
}
