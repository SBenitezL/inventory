package com.inventory.myfood.infraestructure.output.persistence.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @brief enum utilizado para crear un dominio en la base de datos
 * @details Representa las unidades en las cuales se pueden almacenar los
 *          alimentos.
 */
@Getter
@AllArgsConstructor
public enum UnitsEnum {
    /** @brief Representa la unidad de mililitros. */
    MILLILITERS("ml"),
    /** @brief representa la unidad de litros. */
    LITERS("l"),
    /** @brief representa la unidad de tazas. */
    CUPS("cup"),
    /** @brief representa la unidad de cuharaditas. */
    TEASPOONS("tsp"),
    /** @brief representa la unidad de cuharadas. */
    TABLESPOONS("tbsp"),
    /** @brief representa la unidad de onzas líquidas */
    FLUID_OUNCES("fl oz"),
    /** @brief representa la unidad de gramos */
    GRAMS("g"),
    /** @brief representa la unidad de kilogramos */
    KILOGRAMS("kg"),
    /** @brief representa la unidad de onzas */
    OUNCES("oz"),
    /** @brief representa la unidad de libras */
    POUNDS("lb"),
    /** @brief representa la unidad de piezas */
    PIECES("pieces"),
    /** @brief representa la unidad de rebanadas */
    SLICES("slices"),
    /** @brief representa la unidad de porciones */
    SERVINGS("servings");

    /**
     * @brief designa una abreviación a cada tipo de unidad.
     */
    private final String unitAbbreviation;
}
