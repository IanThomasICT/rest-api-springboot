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
        return inventoryRepository.findAllOrderByStoreId();
    }

    public void addInventory(Inventory inventory) {
        Optional<Inventory> inventoryByID = inventoryRepository
                .findInventoryByInventoryId(inventory.getInventoryId());
        if (inventoryByID.isPresent()) {
            throw new IllegalStateException("storeId already exists");
        }
        inventoryRepository.save(inventory);
    }

    public void deleteInventory(Integer storeId) {
        boolean exists = inventoryRepository.existsById(storeId);
        if (!exists) {
            throw new IllegalStateException("Inventory id " + storeId + " does not exist");
        }
        inventoryRepository.deleteById(storeId);
        System.out.println("Deleted inventory: " + storeId + ".");
    }


    // Transactional: Sets classes returned by internal methods (inventory) to a 'managed' state and thus modifiable
    @Transactional
    public void updateInventory(Integer storeId, String storeLocation, Integer beefCount, Integer chickenCount, Integer milkCount, Float fridgeTemp) {
        Inventory inventory = inventoryRepository.findById(storeId)
                .orElseThrow(() -> new IllegalStateException("Inventory id " + storeId + " does not exist"));

        if (storeLocation != null && storeLocation.length() > 0 && !Objects.equals(inventory.getStoreLocation(),storeLocation)) {
            inventory.setStoreLocation(storeLocation);
        }

        if (beefCount != null && !Objects.equals(inventory.getBeefCount(),beefCount)) {
            inventory.setBeefCount(beefCount);
        }

        if (chickenCount != null && !Objects.equals(inventory.getChickenCount(),chickenCount)) {
            inventory.setChickenCount(chickenCount);
        }

        if (milkCount != null && !Objects.equals(inventory.getMilkCount(),milkCount)) {
            inventory.setMilkCount(milkCount);
        }

        if (fridgeTemp != null && !Objects.equals(inventory.getFridgeTemp(),fridgeTemp)) {
            inventory.setFridgeTemp(fridgeTemp);
        }
        System.out.println("Updated inventory: " + storeId + ".");
    }
}
