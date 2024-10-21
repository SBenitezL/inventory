package com.inventory.myfood.infraestructure.output.persistence.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @brief enum utilizado para crear un dominio en la base de datos
 * @details Representa las categorías sobre las cuales se pueden agrupar los
 *          alimentos.
 */
@Getter
@AllArgsConstructor
public enum CategoryEnum {
    /** @brief Representa a la categoría de frutas. */
    FRUITS("Fruits"),
    /** @brief Representa a la categoría de vegetales. */
    VEGETABLES("Vegetables"),
    /** @brief Representa a la categoría de lácteos. */
    DAIRY("Dairy"),
    /** @brief Representa a la categoría de carnes. */
    MEAT("Meat"),
    /** @brief Representa a la categoría de comida de mar. */
    SEAFOOD("Seafood"),
    /** @brief Representa a la categoría de comida de granos. */
    GRAINS("Grains"),
    /** @brief Representa a la categoría de comida de bebidas. */
    BEVERAGES("Beverages"),
    /** @brief Representa a la categoría de comida de dulces. */
    SWEETS("Sweets"),
    /** @brief Representa a la categoría de comida de bocadillos. */
    SNACKS("Snacks"),
    /** @brief Representa a la categoría de comida de condimentos. */
    CONDIMENTS("Condiments"),
    /** @brief Representa a la categoría de comida de legumbres. */
    LEGUMES("Legumes"),
    /** @brief Representa a la categoría de comida de nueces y semillas. */
    NUTS_AND_SEEDS("Nuts and Seeds");

    /** @brief Establece un nombre en lenguaje natural para cada categoría. */
    private final String desciptiveName;
}
