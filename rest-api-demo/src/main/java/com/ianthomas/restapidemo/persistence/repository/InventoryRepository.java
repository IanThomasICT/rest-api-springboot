package com.ianthomas.restapidemo.persistence.repository;

import com.ianthomas.restapidemo.persistence.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    @Query("SELECT i FROM Inventory i WHERE i.itemName = ?1")
    Optional<Inventory> findInventoryByItemName(String name);

    @Query("FROM Inventory i WHERE i.itemName = ?1 AND i.price = ?2")
    Optional<Inventory>findItem(String name, Float price);

    @Query("FROM Inventory i WHERE i.customer IS NULL")
    List<Inventory> findAvailableInventory();

    @Query("FROM Inventory i WHERE i.customer IS NOT NULL")
    List<Inventory> findPurchasedInventory();


}
