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

    @DeleteMapping(path = "{storeId}")
    public void deleteInventory(@PathVariable("storeId") Integer storeId) {
        inventoryService.deleteInventory(storeId);
    }

    @PutMapping(path = "{storeId}")
    public void updateInventory(
            @PathVariable("storeId") Integer storeId,
            @RequestParam(required = false) String  storeLocation,
            @RequestParam(required = false) Integer beefCount,
            @RequestParam(required = false) Integer chickenCount,
            @RequestParam(required = false) Integer milkCount,
            @RequestParam(required = false) Float fridgeTemp) {

        inventoryService.updateInventory(storeId, storeLocation, beefCount, chickenCount, milkCount, fridgeTemp);
    }
}
