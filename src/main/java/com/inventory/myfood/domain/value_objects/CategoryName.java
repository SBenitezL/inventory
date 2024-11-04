package com.inventory.myfood.domain.value_objects;

import lombok.Getter;

@Getter
public class CategoryName {
    private String name;

    public CategoryName(String name) {
        if (name.isBlank())
            throw new IllegalArgumentException("The name of the category can't be blank");
        this.name = name;
    }

    /**
     * Verifica que el nombre tenga contenido
     * 
     * @param name nombre a validar
     * @return {@code true} en caso de que el nombre cumpla con las reglas y
     *         {@code false} en caso contrario.
     */
    public static boolean isValidName(String name) {
        return !name.isBlank();
    }
}
