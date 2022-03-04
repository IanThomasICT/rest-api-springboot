package com.ianthomas.restapidemo.rest.controller;

import com.ianthomas.restapidemo.rest.dto.ResponseDto;
import com.ianthomas.restapidemo.rest.dto.SupplierDto;
import com.ianthomas.restapidemo.service.SupplierService;
import com.ianthomas.restapidemo.persistence.model.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/suppliers")
public class SupplierController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    // Instantiate a Service property
    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping(produces = {"application/json", "text/json"})
    public List<Supplier> getSuppliers() {
        return supplierService.getSupplierItems();
    }

    @DeleteMapping(path = "{id}", produces = {"application/json", "text/json"})
    public ResponseDto deleteSupplier(@PathVariable("id") Integer id) {
        supplierService.deleteSupplier(id);
        return new ResponseDto("success", "Supplier deleted successfully.");
    }

    // Map POST request to database
    @PostMapping(consumes = {"application/json"}, produces = {"application/json", "text/json"})
    public ResponseDto addSupplier(@RequestBody SupplierDto supplierDto) {
        return new ResponseDto("success", "Supplier added successfully.", supplierService.addSupplier(supplierDto.getSupplier()));
    }

    @PutMapping(path = "{id}", consumes = {"application/json"}, produces = {"application/json", "text/json"})
    public ResponseDto updateSupplier(
            @PathVariable("id") Integer id,
            @RequestParam(required = false) String supplierName) {
        return new ResponseDto("success", "Supplier updated successfully.", supplierService.updateSupplier(id, supplierName));

    }
}
