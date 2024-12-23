package com.inventory.myfood.infraestructure.input.product.dto.response;

import java.util.Date;

import com.inventory.myfood.domain.agregates.Category;

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
