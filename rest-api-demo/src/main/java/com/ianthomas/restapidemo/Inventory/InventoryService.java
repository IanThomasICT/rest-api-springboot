package com.ianthomas.restapidemo.Inventory;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    public List<Inventory> getInventory() {
        return List.of(
            new Inventory("Wichita, KS",1004,512,124,62,41.3f)
        );
    }
}
