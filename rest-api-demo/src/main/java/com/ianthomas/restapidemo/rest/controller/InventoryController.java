package com.ianthomas.restapidemo.rest.controller;

import com.ianthomas.restapidemo.rest.dto.ResponseDto;
import com.ianthomas.restapidemo.service.InventoryService;
import com.ianthomas.restapidemo.persistence.model.Inventory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/inventory")
public class InventoryController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping(produces = {"application/json", "text/json"})
    public List<Inventory> getInventoryItems() {
        return inventoryService.getInventoryItems();
    }

    @GetMapping(path = "available", produces = {"application/json", "text/json"})
    public List<Inventory> getAvailableInventory() {
        return inventoryService.getAvailableInventory();
    }

    @GetMapping(path = "purchased", produces = {"application/json", "text/json"})
    public List<Inventory> getPurchasedInventory() {
        return inventoryService.getPurchasedInventory();
    }

    @DeleteMapping(path = "{productId}", produces = {"application/json", "text/json"})
    public ResponseDto deleteInventory(@PathVariable("productId") Integer productId) {
        inventoryService.deleteInventory(productId);
        LOG.info("Deleted inventory item: {}",productId);
        return new ResponseDto("success", "Inventory deleted successfully.");
    }

    // Map POST request to database
    @PostMapping(consumes = {"application/json"}, produces = {"application/json", "text/json"})
    public ResponseDto addInventory(@RequestBody Inventory inventory) {
        inventoryService.addInventory(inventory);
        LOG.info("Added inventory item: {}",inventory.getProductId());
        return new ResponseDto("success", "Inventory added successfully.");

    }



    @PutMapping(path = "{productId}", consumes = {"application/json"}, produces = {"application/json", "text/json"})
    public ResponseDto updateInventory(
            @PathVariable("productId") Integer productId,
            @RequestParam(required = false) String itemName,
            @RequestParam(required = false) Float itemPrice,
            @RequestParam(required = false) Integer supplierId,
            @RequestParam(required = false) String supplierName,
            @RequestParam(required = false) String supplierAddress) {

        inventoryService.updateInventory(productId, itemName, itemPrice, supplierId, supplierName, supplierAddress);
        LOG.info("Updated inventory item: {}",productId);
        return new ResponseDto("success", "Inventory updated successfully.");

    }
}
