package com.inventory.myfood.infraestructure.output.waste.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductWaste {
    private String id;
    private String name;
    private String category;
    private double stock;
}
