package com.ianthomas.restapidemo.service;

import com.ianthomas.restapidemo.exception.InvalidInputException;
import com.ianthomas.restapidemo.exception.ItemAlreadyExistsException;
import com.ianthomas.restapidemo.exception.ItemNotFoundException;
import com.ianthomas.restapidemo.persistence.model.Inventory;
import com.ianthomas.restapidemo.persistence.model.Supplier;
import com.ianthomas.restapidemo.persistence.repository.InventoryRepository;
import com.ianthomas.restapidemo.persistence.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final SupplierRepository supplierRepository;    // Used for update function

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository, SupplierRepository supplierRepository) {
        this.inventoryRepository = inventoryRepository;
        this.supplierRepository = supplierRepository;
    }

    public List<Inventory> getInventoryItems() {
        return inventoryRepository.findAll();
    }

    public void addInventory(Inventory inventory) {
        if (Objects.isNull(inventory.getSupplier()))
            throw new InvalidInputException("Supplier information must be provided.");

        inventoryRepository.save(inventory);
    }

    public void deleteInventory(Integer productId){
        boolean exists = inventoryRepository.existsById(productId);
        if (!exists)
            throw new ItemNotFoundException("Inventory id " + productId + " does not exist.");

        inventoryRepository.deleteById(productId);
        System.out.println("Deleted inventory item: " + productId + ".");
    }


    // Transactional: Sets classes returned by internal methods (inventory) to a 'managed' state and thus modifiable
    @Transactional
    public void updateInventory(Integer productId,
                                String name,
                                Float itemPrice,
                                Integer supplierId,
                                String supplierName,
                                String supplierLocation) {
        Inventory inventory = inventoryRepository.findById(productId)
                .orElseThrow(() -> new ItemNotFoundException("Inventory id " + productId + " does not exist."));

        // Let's just allow any item property to be updated if it's a valid input
        if (!Objects.equals(inventory.getItemName(), name) && name.length() > 0)
            inventory.setItemName(name);
        if (!Objects.equals(inventory.getPrice(), itemPrice))
            inventory.setPrice(itemPrice);
        if (supplierId % 10 != 0)
            throw new InvalidInputException("Supplier id " + supplierId + " for item #" + productId + " must be a multiple of 10.");

        // Update supplier of inventory item
        if (!Objects.equals(inventory.getSupplier().getId(), supplierId)) {
            // Check if supplier exists
            Optional<Supplier> supplier = supplierRepository.findById(supplierId);
            if (supplier.isPresent()) {
                inventory.setSupplier(supplier.get());
            } else {
                inventory.setSupplier(new Supplier(supplierName, supplierLocation));
            }
        }

        System.out.println("Updated inventory item: " + productId + ".");
    }

    public List<Inventory> getAvailableInventory() {
        return inventoryRepository.findAvailableInventory();
    }

    public List<Inventory> getPurchasedInventory() {
        return inventoryRepository.findPurchasedInventory();
    }
}
