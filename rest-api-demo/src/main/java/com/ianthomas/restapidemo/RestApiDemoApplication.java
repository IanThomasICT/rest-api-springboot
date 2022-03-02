package com.ianthomas.restapidemo;

import com.ianthomas.restapidemo.persistence.model.Customer;
import com.ianthomas.restapidemo.persistence.model.Inventory;
import com.ianthomas.restapidemo.persistence.repository.CustomerRepository;
import com.ianthomas.restapidemo.persistence.repository.InventoryRepository;
import com.ianthomas.restapidemo.persistence.model.Supplier;
import com.ianthomas.restapidemo.persistence.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class RestApiDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiDemoApplication.class, args);
    }
}