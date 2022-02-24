package com.ianthomas.restapidemo.service;

import com.ianthomas.restapidemo.persistence.model.Supplier;
import com.ianthomas.restapidemo.persistence.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> getSupplierItems() {
        return supplierRepository.findAllOrderBySupplierId();
    }

    public void addSupplier(Supplier supplier) {
        if (supplier.getId() % 10 != 0) {
            throw new IllegalStateException("Invalid Supplier Id");
        }
        Optional<Supplier> supplierByID = supplierRepository
                .findSupplierBySupplierId(supplier.getId());
        if (supplierByID.isPresent()) {
            throw new IllegalStateException("Supplier Id already exists");
        }
        supplierRepository.save(supplier);
    }

    public void deleteSupplier(Integer id) {
        if (id % 10 != 0) {
            throw new IllegalStateException("Invalid Supplier Id");
        }
        boolean exists = supplierRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Supplier id " + id + " does not exist");
        }
        supplierRepository.deleteById(id);
        System.out.println("Deleted supplier item: " + id + ".");
    }


    // Transactional: Sets classes returned by internal methods (supplier) to a 'managed' state and thus modifiable
    @Transactional
    public void updateSupplier(Integer id, String supplierName) {
        if (id % 10 != 0) {
            throw new IllegalStateException("Invalid Supplier Id");
        }
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Supplier id " + id + " does not exist"));

        if (!Objects.equals(supplier.getName(), supplierName) && supplierName.length() > 0) {
            supplier.setName(supplierName);
        }

        System.out.println("Updated supplier item: " + id + ".");
    }
}
