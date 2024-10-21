package com.inventory.myfood.domain.value_objects;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductName {
    private String name;

    public ProductName(String name) {
        if (name.isBlank())
            throw new IllegalArgumentException("The name of the product can't be blank");
        this.name = name;
    }
}
