package com.inventory.myfood.domain.value_objects;

import lombok.Getter;

@Getter
public enum Units {
    MILLILITERS("ml", true),
    LITERS("l", true),
    CUPS("cup", false),
    TEASPOONS("tsp", false),
    TABLESPOONS("tbsp", false),
    FLUID_OUNCES("fl oz", true),
    GRAMS("g", true),
    KILOGRAMS("kg", true),
    OUNCES("oz", true),
    POUNDS("lb", true),
    PIECES("pieces", false),
    SLICES("slices", false),
    SERVINGS("servings", false);

    private final String abbreviation;
    private final boolean allowDecimals;

    Units(String abbreviation, boolean allowDecimals) {
        this.abbreviation = abbreviation;
        this.allowDecimals = allowDecimals;
    }
}
