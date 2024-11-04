package com.inventory.myfood.infraestructure.output.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @brief enum utilizado para crear un dominio en la base de datos
 * @details Representa las categorías sobre las cuales se pueden agrupar los
 *          alimentos.
 */
@Getter
@AllArgsConstructor
@Entity
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String categoryId;
    @Column(unique = true)
    private String categoryName;
}
