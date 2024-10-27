package com.inventory.myfood.infraestructure.input.dto.request;

import java.util.Date;

import lombok.Data;

@Data
public class ProductWithoutIdDTORequest {
    private String name;
    private Double stock;
    private String category;
    private String units;
    private Date usefulLife;
}
