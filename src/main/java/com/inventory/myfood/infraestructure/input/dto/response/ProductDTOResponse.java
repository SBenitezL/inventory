package com.inventory.myfood.infraestructure.input.dto.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTOResponse {
    private String id;
    private String name;
    private Double stock;
    private String category;
    private String units;
    private Date usefulLife;
    private boolean isExpired;
}
