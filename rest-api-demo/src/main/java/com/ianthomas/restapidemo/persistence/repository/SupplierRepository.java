package com.ianthomas.restapidemo.persistence.repository;

import com.ianthomas.restapidemo.persistence.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    // Query Example
    @Query("FROM Supplier s ORDER BY s.id ASC")
    List<Supplier> getSortedSuppliers();

    @Query("SELECT s FROM Supplier s WHERE s.name = ?1")
    Optional<Supplier> findBySupplierName(String name);
}
