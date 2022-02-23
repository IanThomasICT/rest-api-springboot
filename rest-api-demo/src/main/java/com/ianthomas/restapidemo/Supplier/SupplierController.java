package com.ianthomas.restapidemo.Supplier;

import com.ianthomas.restapidemo.Supplier.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/supplier")
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
    public void registerSupplier(@RequestBody Supplier supplier) {
        supplierService.addSupplier(supplier);
    }

    @DeleteMapping(path = "{supplierId}")
    public void deleteSupplier(@PathVariable("supplierId") Integer supplierId) {
        supplierService.deleteSupplier(supplierId);
    }

    @PutMapping(path = "{supplierId}")
    public void updateSupplier(
            @PathVariable("supplierId") Integer supplierId,
            @RequestParam(required = false) String supplierName) {

        supplierService.updateSupplier(supplierId, supplierName);
    }
}
