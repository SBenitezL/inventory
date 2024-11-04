package com.inventory.myfood.infraestructure.input.category.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryWithoutIdDTORequest {
    @NotBlank(message = "The name of Category can't be blank.")
    private String name;
}
