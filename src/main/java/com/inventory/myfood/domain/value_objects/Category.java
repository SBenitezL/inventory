package com.inventory.myfood.domain.value_objects;

import lombok.Getter;

@Getter
public enum Category {
    FRUITS("Fruits"),
    VEGETABLES("Vegetables"),
    DAIRY("Dairy"),
    MEAT("Meat"),
    SEAFOOD("Seafood"),
    GRAINS("Grains"),
    BEVERAGES("Beverages"),
    SWEETS("Sweets"),
    SNACKS("Snacks"),
    CONDIMENTS("Condiments"),
    LEGUMES("Legumes"),
    NUTS_AND_SEEDS("Nuts and Seeds");

    private final String desciptiveName;

    Category(String desciptiveName) {
        this.desciptiveName = desciptiveName;
    }

    public static boolean isCategory(String category) {
        for (Category value : Category.values())
            if (value.name().equals(category) || value.getDesciptiveName().equals(category))
                return true;
        return false;
    }
}
