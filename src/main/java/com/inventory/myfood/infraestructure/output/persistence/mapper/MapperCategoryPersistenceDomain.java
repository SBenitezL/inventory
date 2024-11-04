package com.inventory.myfood.infraestructure.output.persistence.mapper;

import java.util.ArrayList;
import java.util.List;

import com.inventory.myfood.domain.agregates.Category;
import com.inventory.myfood.infraestructure.output.persistence.entities.CategoryEntity;

public class MapperCategoryPersistenceDomain {
    public static Category mapPersistenceDomain(CategoryEntity entity) {
        return new Category(entity.getCategoryId(), entity.getCategoryName());
    }

    public static CategoryEntity mapDomainPeristence(Category domain) {
        return new CategoryEntity(domain.getId(), domain.getName().getName());
    }

    public static List<Category> mapPersistenceDomain(List<CategoryEntity> entities) {
        List<Category> domain = new ArrayList<>();
        for (CategoryEntity entity : entities)
            domain.add(mapPersistenceDomain(entity));
        return domain;
    }

    public static List<CategoryEntity> mapDomainPeristence(List<Category> domain) {
        List<CategoryEntity> entities = new ArrayList<>();
        for (Category value : domain)
            entities.add(mapDomainPeristence(value));
        return entities;
    }
}
