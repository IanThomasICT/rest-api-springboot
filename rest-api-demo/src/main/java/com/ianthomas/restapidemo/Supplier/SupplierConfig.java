package com.ianthomas.restapidemo.Supplier;

import com.ianthomas.restapidemo.Supplier.Supplier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SupplierConfig {

    @Bean
    CommandLineRunner commandLineRunner2(SupplierRepository repository) {
        return args -> {
            Supplier mbi = new Supplier(
                    10,
                    "Midwest Beef Inc.",
                    "Salina, KS"
            );

            Supplier scc = new Supplier(
                    20,
                    "Sal's Chicken Coop",
                    "Dallas, TX"
            );
            repository.saveAll(List.of(mbi, scc));
        };
    }
}
