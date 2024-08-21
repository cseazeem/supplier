package com.cseazeem.supplier.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Supplier {

    private Long supplierId;
    private String companyName;
    private String website;
    private String location;
    private NatureOfBusiness natureOfBusiness;
    private ManufacturingProcesses manufacturingProcesses;

    public enum NatureOfBusiness {
        small_scale,
        medium_scale,
        large_scale
    }

    public enum ManufacturingProcesses {
        moulding,
        printing_3d,
        casting,
        coating
    }
}

