package com.cseazeem.supplier.database;

import com.cseazeem.supplier.entity.Supplier;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import java.util.List;

public interface SupplierDao {

    @SqlQuery("SELECT supplier_id, company_name, website, location, nature_of_business, manufacturing_processes " +
            "FROM supplier " +
            "WHERE location = COALESCE(:location, location) " +
            "  AND nature_of_business = COALESCE(:natureOfBusiness::nature_of_business, nature_of_business) " +
            "  AND manufacturing_processes = COALESCE(:manufacturingProcess::manufacturing_processes, manufacturing_processes) LIMIT :pageNo OFFSET :pageSize; ")
    @RegisterBeanMapper(Supplier.class)
    List<Supplier> searchSuppliers(
            @Bind("location") String location,
            @Bind("natureOfBusiness") Supplier.NatureOfBusiness natureOfBusiness,
            @Bind("manufacturingProcess") Supplier.ManufacturingProcesses manufacturingProcess,
            @Bind("pageNo") int pageNo,
            @Bind("pageSize") int pageSize
    );


}
