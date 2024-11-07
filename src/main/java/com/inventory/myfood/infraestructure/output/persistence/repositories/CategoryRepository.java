package com.inventory.myfood.infraestructure.output.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.myfood.infraestructure.output.persistence.entities.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {
    boolean existsByCategoryName(String name);
}
