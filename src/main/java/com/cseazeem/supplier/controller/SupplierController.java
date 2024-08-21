package com.cseazeem.supplier.controller;

import com.cseazeem.supplier.entity.Supplier;
import com.cseazeem.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping("/query")
    public ResponseEntity<List<Supplier>> searchSuppliers(
            @RequestParam(value = "location", required = false) String location,
            @RequestParam(value = "natureOfBusiness", required = false) Supplier.NatureOfBusiness natureOfBusiness,
            @RequestParam(value = "manufacturingProcess", required = false) Supplier.ManufacturingProcesses manufacturingProcess,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "offset", defaultValue = "0") int offset) {

        List<Supplier> suppliers = supplierService.searchSuppliers(location, natureOfBusiness, manufacturingProcess, limit, offset);
        return ResponseEntity.ok(suppliers);
    }

}
