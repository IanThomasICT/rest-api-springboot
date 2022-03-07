package com.ianthomas.restapidemo.service;

import com.ianthomas.restapidemo.exception.ItemAlreadyExistsException;
import com.ianthomas.restapidemo.exception.ItemNotFoundException;
import com.ianthomas.restapidemo.persistence.model.Inventory;
import com.ianthomas.restapidemo.persistence.model.Supplier;
import com.ianthomas.restapidemo.persistence.repository.SupplierRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class SupplierService {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> getSuppliers() {
        return supplierRepository.getSortedSuppliers();
    }

    public Optional<Supplier> getSupplier(Integer id) { return supplierRepository.findById(id); }

    public Supplier addSupplier(Supplier supplier) {
        Optional<Supplier> sup = supplierRepository.findBySupplierName(supplier.getName());
        if (sup.isPresent())
            throw new ItemAlreadyExistsException("Supplier already exists");

        supplierRepository.save(supplier);
        LOG.info("Added Supplier {}",supplier.getId());
        return supplier;
    }

    public void deleteSupplier(Integer id) {
        boolean exists = supplierRepository.existsById(id);
        if (!exists)
            throw new ItemNotFoundException("Supplier id " + id + " does not exist");

        supplierRepository.deleteById(id);
        LOG.info("Deleted Supplier {} and branded items",id);
    }


    // Transactional: Sets classes returned by internal methods (supplier) to a 'managed' state and thus modifiable
    @Transactional
    public Supplier updateSupplier(Integer id, String name, String location, Set<Inventory> exports) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Supplier id " + id + " does not exist"));

        boolean changes = false;
        if (name != null && !Objects.equals(supplier.getName(), name) && name.length() > 0) {
            supplier.setName(name);
            changes = true;
        }
        if (location != null && !Objects.equals(supplier.getLocation(), location) && location.length() > 0) {
            supplier.setLocation(location);
            changes = true;
        }
        if (exports != null && !Objects.equals(supplier.getExports(), exports)) {
            supplier.setExports(exports);
            changes = true;
        }
        if (changes)
            LOG.info("Updated supplier {}", id);
        else
            LOG.info("No new updates were made to supplier {}", id);
        return supplier;
    }

    public Supplier getSupplierByName(String name) {
        Optional<Supplier> supplier = supplierRepository.findBySupplierName(name);
        if (supplier.isPresent())
            return supplier.get();
        else
            throw new ItemNotFoundException("Supplier name does not exist");
    }
}
