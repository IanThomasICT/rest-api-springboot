package com.ianthomas.restapidemo.Inventory;

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

    // Map POST request to database
    @PostMapping
    public void registerInventory(@RequestBody Inventory inventory) {
        inventoryService.addInventory(inventory);
    }

    @DeleteMapping(path = "{productId}")
    public void deleteInventory(@PathVariable("productId") Integer productId) {
        inventoryService.deleteInventory(productId);
    }

    @PutMapping(path = "{productId}")
    public void updateInventory(
            @PathVariable("productId") Integer productId,
            @RequestParam(required = false) String itemName) {

        inventoryService.updateInventory(productId, itemName);
    }
}
