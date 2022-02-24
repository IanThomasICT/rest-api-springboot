package com.ianthomas.restapidemo.persistence.repository;

import com.ianthomas.restapidemo.persistence.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    // Query Example
    @Query("SELECT i FROM Inventory i WHERE i.productId = ?1")
    Optional<Inventory> findInventoryByProductId(int id);

    @Query("FROM Inventory i ORDER BY i.productId ASC")
    List<Inventory> findAllOrderByProductId();

    @Query("FROM Inventory i WHERE i.itemName = ?1 AND i.price = ?1")
    Optional<Inventory>findItem(String name, Float price);
}
