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
                    1,
                    "Wichita, KS",
                    123,
                    512,
                    41,
                    41.3f
            );

            Inventory aus = new Inventory(
                    2,
                    "Austin, TX",
                    533,
                    1125,
                    712,
                    39.2f
            );
            repository.saveAll(List.of(ict, aus));
        };
    }
}
