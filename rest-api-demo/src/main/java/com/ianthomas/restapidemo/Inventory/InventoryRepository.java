package com.ianthomas.restapidemo.Inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    // Query Example
    @Query("SELECT i FROM Inventory i WHERE i.storeId = ?1")
    Optional<Inventory> findInventoryByStoreId(int id);

    @Query("FROM Inventory i ORDER BY i.storeId ASC")
    List<Inventory> findAllOrderByStoreId();
}
