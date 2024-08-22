package com.cseazeem.supplier.service;

import com.cseazeem.supplier.database.SupplierDaoImpl;
import com.cseazeem.supplier.entity.Supplier;
import com.cseazeem.supplier.exception.SupplierNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierDaoImpl supplierDaoImpl;

    public SupplierService(SupplierDaoImpl supplierDaoImpl) {
        this.supplierDaoImpl = supplierDaoImpl;
    }

    public List<Supplier> searchSuppliers(String location, Supplier.NatureOfBusiness natureOfBusiness, Supplier.ManufacturingProcesses manufacturingProcess, int limit, int offset) {
        List<Supplier> suppliers = supplierDaoImpl.searchSuppliers(location, natureOfBusiness, manufacturingProcess, limit, offset);

        if (suppliers.isEmpty()) {
            throw new SupplierNotFoundException("No suppliers found with the given criteria");
        }

        return suppliers;
    }

}
