package com.ianthomas.restapidemo.Inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    // Query Example
    @Query("SELECT i FROM Inventory i WHERE i.inventoryId = ?1")
    Optional<Inventory> findInventoryByInventoryId(int id);

    @Query("FROM Inventory i ORDER BY i.inventoryId ASC")
    List<Inventory> findAllOrderByStoreId();
}
