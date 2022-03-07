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

    @Query("SELECT s FROM Supplier s WHERE s.name = ?1")
    List<Supplier> getByName(String name);

    @Query("SELECT s FROM Supplier s WHERE s.location = ?1")
    List<Supplier> getByLocation(String location);

    @Query("SELECT s FROM Supplier s WHERE s.id = ?1")
    List<Supplier> getByIds(Integer id);

    @Query("SELECT s FROM Supplier s WHERE s.name = ?1 AND s.location = ?2")
    List<Supplier> getByNameAndLocation(String name, String location);
}
