package com.inventory.myfood.infraestructure.input.category.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryDTORequest {
    @NotBlank(message = "The id of Category can't be blank.")
    private String id;
    @NotBlank(message = "The name of Category can't be blank.")
    private String name;
}
