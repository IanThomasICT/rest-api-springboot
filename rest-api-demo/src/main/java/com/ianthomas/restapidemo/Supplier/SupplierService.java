package com.ianthomas.restapidemo.Supplier;

import com.ianthomas.restapidemo.Supplier.Supplier;
import com.ianthomas.restapidemo.Supplier.SupplierRepository;
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
        Optional<Supplier> supplierByID = supplierRepository
                .findSupplierBySupplierId(supplier.getSupplierId());
        if (supplierByID.isPresent()) {
            throw new IllegalStateException("supplierId already exists");
        }
        supplierRepository.save(supplier);
    }

    public void deleteSupplier(Integer supplierId) {
        boolean exists = supplierRepository.existsById(supplierId);
        if (!exists) {
            throw new IllegalStateException("Supplier id " + supplierId + " does not exist");
        }
        supplierRepository.deleteById(supplierId);
        System.out.println("Deleted supplier item: " + supplierId + ".");
    }


    // Transactional: Sets classes returned by internal methods (supplier) to a 'managed' state and thus modifiable
    @Transactional
    public void updateSupplier(Integer supplierId, String supplierName) {
        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new IllegalStateException("Supplier id " + supplierId + " does not exist"));

        if (!Objects.equals(supplier.getName(), supplierName) && supplierName.length() > 0) {
            supplier.setName(supplierName);
        }

        System.out.println("Updated supplier item: " + supplierId + ".");
    }
}
