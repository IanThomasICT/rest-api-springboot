package com.ianthomas.restapidemo.service;

import com.ianthomas.restapidemo.exception.InvalidInputException;
import com.ianthomas.restapidemo.exception.ItemAlreadyExistsException;
import com.ianthomas.restapidemo.exception.ItemNotFoundException;
import com.ianthomas.restapidemo.persistence.model.Supplier;
import com.ianthomas.restapidemo.persistence.repository.SupplierRepository;
import com.ianthomas.restapidemo.rest.dto.ResponseDto;
import com.ianthomas.restapidemo.rest.dto.SupplierDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SupplierService {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> getSupplierItems() {
        return supplierRepository.getSortedSuppliers();
    }

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
    public Supplier updateSupplier(Integer id, String supplierName) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Supplier id " + id + " does not exist"));

        if (!Objects.equals(supplier.getName(), supplierName) && supplierName.length() > 0) {
            supplier.setName(supplierName);
            LOG.info("Updated supplier {}", id);
        } else
            LOG.info("No updates to be made");

        return supplier;
    }
}
