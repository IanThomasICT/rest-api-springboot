package com.ianthomas.restapidemo.Supplier;

import com.ianthomas.restapidemo.Supplier.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    // Query Example
    @Query("SELECT s FROM Supplier s WHERE s.supplierId = ?1")
    Optional<Supplier> findSupplierBySupplierId(int id);

    @Query("FROM Supplier s ORDER BY s.supplierId ASC")
    List<Supplier> findAllOrderBySupplierId();
}
