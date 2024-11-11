package com.inventory.myfood.infraestructure.input.product.dto.request;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class ProductWithoutIdDTORequest {
    @NotBlank(message = "You must add the product's name.")
    private String name;
    @PositiveOrZero(message = "The min value of stock is 0.")
    private Double stock;
    @NotBlank(message = "You most add the product's category.")
    private String category;
    @NotBlank(message = "You most add the product's units.")
    private String units;
    private Date usefulLife;
}
