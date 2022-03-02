package com.ianthomas.restapidemo.rest.controller;

import com.ianthomas.restapidemo.service.SupplierService;
import com.ianthomas.restapidemo.persistence.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/suppliers")
public class SupplierController {

    // Instantiate a Service property
    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public List<Supplier> getSuppliers() {
        return supplierService.getSupplierItems();
    }

    // Map POST request to database
    @PostMapping
    public void addSupplier(@RequestBody Supplier supplier) {
        if (supplier.getId() < 0) { throw new IllegalStateException("Invalid: id must be a positive value"); }
        supplierService.addSupplier(supplier);
    }

    @DeleteMapping(path = "{id}")
    public void deleteSupplier(@PathVariable("id") Integer id) {
        if (id < 0) { throw new IllegalStateException("Invalid: id must be a positive value"); }
        supplierService.deleteSupplier(id);
    }

    @PutMapping(path = "{id}")
    public void updateSupplier(
            @PathVariable("id") Integer id,
            @RequestParam(required = false) String supplierName) {
        if (id < 0) { throw new IllegalStateException("Invalid: id must be a positive value"); }
        supplierService.updateSupplier(id, supplierName);
    }
}