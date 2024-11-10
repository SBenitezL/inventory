package com.inventory.myfood.infraestructure.output.waste.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuantityWaste {
    private String id;
    private double wasteQuantity;
    private double totalWasteQuantity;

}
