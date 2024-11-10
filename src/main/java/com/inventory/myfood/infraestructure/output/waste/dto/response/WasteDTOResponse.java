package com.inventory.myfood.infraestructure.output.waste.dto.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WasteDTOResponse {
    private String idWaste;
    private ProductWaste product;
    private QuantityWaste quantityWaste;
    private CauseWaste cause;
    private Date dateRegister;
}
