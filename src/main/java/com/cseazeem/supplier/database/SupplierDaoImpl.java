package com.cseazeem.supplier.database;

import com.cseazeem.supplier.entity.Supplier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierDaoImpl {

    private final SupplierDao supplierDao;

    public SupplierDaoImpl(SupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }



    public List<Supplier> searchSuppliers(String location, Supplier.NatureOfBusiness natureOfBusiness, Supplier.ManufacturingProcesses manufacturingProcess, int pageNo, int pageSize) {
        return supplierDao.searchSuppliers(location, natureOfBusiness, manufacturingProcess, pageNo, pageSize);
    }
}
