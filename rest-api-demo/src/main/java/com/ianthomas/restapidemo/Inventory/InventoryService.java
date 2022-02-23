package com.ianthomas.restapidemo.Inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<Inventory> getInventoryItems() {
        return inventoryRepository.findAllOrderByProductId();
    }

    public void addInventory(Inventory inventory) {
        Optional<Inventory> inventoryByID = inventoryRepository
                .findInventoryByProductId(inventory.getProductId());
        if (inventoryByID.isPresent()) {
            throw new IllegalStateException("productId already exists");
        }
        inventoryRepository.save(inventory);
    }

    public void deleteInventory(Integer productId) {
        boolean exists = inventoryRepository.existsById(productId);
        if (!exists) {
            throw new IllegalStateException("Inventory id " + productId + " does not exist");
        }
        inventoryRepository.deleteById(productId);
        System.out.println("Deleted inventory item: " + productId + ".");
    }


    // Transactional: Sets classes returned by internal methods (inventory) to a 'managed' state and thus modifiable
    @Transactional
    public void updateInventory(Integer productId, String itemName) {
        Inventory inventory = inventoryRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException("Inventory id " + productId + " does not exist"));

        if (!Objects.equals(inventory.getItemName(), itemName) && itemName.length() > 0) {
            inventory.setItemName(itemName);
        }

        System.out.println("Updated inventory item: " + productId + ".");
    }
}
