package com.ianthomas.restapidemo.Inventory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.UUID;

// Configures the Postgres database
@Configuration
public class InventoryConfig {

    @Bean
    CommandLineRunner commandLineRunner(InventoryRepository repository) {
        return args -> {
            Inventory ict = new Inventory(
                    1000,
                    10,
                    "5lb Steak"
            );

            Inventory aus = new Inventory(
                    1005,
                    20,
                    "2lb Chicken Breast"
            );
            repository.saveAll(List.of(ict, aus));
        };
    }
}
