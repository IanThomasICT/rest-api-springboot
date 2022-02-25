package com.ianthomas.restapidemo.rest.controller;

import com.ianthomas.restapidemo.service.InventoryService;
import com.ianthomas.restapidemo.persistence.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/inventory")
public class InventoryController {

    private InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public List<Inventory> getInventoryItems() {
        return inventoryService.getInventoryItems();
    }

    @GetMapping(path = "available")
    public List<Inventory> getAvailableInventory() {
        return inventoryService.getAvailableInventory();
    }

    @GetMapping(path = "purchased")
    public List<Inventory> getPurchasedInventory() {
        return inventoryService.getPurchasedInventory();
    }

    // Map POST request to database
    @PostMapping
    public void addInventory(@RequestBody Inventory inventory) {
        if (inventory.getProductId() < 1000) { throw new IllegalStateException("Invalid: id must be greater than 1000"); }
        inventoryService.addInventory(inventory);
    }

    @DeleteMapping(path = "{productId}")
    public void deleteInventory(@PathVariable("productId") Integer productId) {
        if (productId < 1000) { throw new IllegalStateException("Invalid: id must be greater than 1000"); }
        inventoryService.deleteInventory(productId);
    }

    @PutMapping(path = "{productId}")
    public void updateInventory(
            @PathVariable("productId") Integer productId,
            @RequestParam(required = false) String itemName,
            @RequestParam(required = false) Float itemPrice,
            @RequestParam(required = false) Integer supplierId,
            @RequestParam(required = false) String supplierName,
            @RequestParam(required = false) String supplierAddress) {

        if (productId < 1000) { throw new IllegalStateException("Invalid: id must be greater than 1000"); }
        inventoryService.updateInventory(productId, itemName, itemPrice, supplierId, supplierName, supplierAddress);
    }
}
