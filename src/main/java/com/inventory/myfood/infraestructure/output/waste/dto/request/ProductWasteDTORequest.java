package com.inventory.myfood.infraestructure.output.waste.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductWasteDTORequest {
    private String id;
    private String name;
    private String category;
    private Double stock;
}
