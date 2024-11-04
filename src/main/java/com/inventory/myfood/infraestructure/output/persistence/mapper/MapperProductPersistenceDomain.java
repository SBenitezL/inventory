package com.inventory.myfood.infraestructure.output.persistence.mapper;

import java.util.ArrayList;
import java.util.List;

import com.inventory.myfood.application.output.ExceptionFormatterIntPort;
import com.inventory.myfood.domain.agregates.Product;
import com.inventory.myfood.domain.agregates.Category;
import com.inventory.myfood.domain.value_objects.ProductName;
import com.inventory.myfood.domain.value_objects.Stock;
import com.inventory.myfood.domain.value_objects.Units;
import com.inventory.myfood.infraestructure.output.persistence.entities.CategoryEntity;
import com.inventory.myfood.infraestructure.output.persistence.entities.ProductEntity;
import com.inventory.myfood.infraestructure.output.persistence.entities.enums.UnitsEnum;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MapperProductPersistenceDomain {
    public final ExceptionFormatterIntPort formatter;

    public Product persistenceToDomain(ProductEntity entity) {
        Category category = this.defineCategory(entity.getCategory());
        Units units = this.defineUnits(entity.getUnits());
        Stock stock = new Stock(entity.getProductStock());
        ProductName name = new ProductName(entity.getProductName());
        return new Product(entity.getProductId(), name, category, stock, units, entity.getProductUseFulLife());
    }

    public ProductEntity domainToPersistence(Product domain) {
        CategoryEntity category = this.defineCategory(domain.getCategory());
        UnitsEnum units = this.defineUnits(domain.getUnit());
        return new ProductEntity(domain.getId(), domain.getName().getName(), domain.getStock().getAmount(),
                category, units, domain.getUsefulLife(), domain.isExpired());
    }

    public List<Product> persistenceToDomain(List<ProductEntity> entities) {
        List<Product> domain = new ArrayList<>();
        for (ProductEntity entity : entities)
            domain.add(this.persistenceToDomain(entity));
        return domain;
    }

    public List<ProductEntity> domainToPersistence(List<Product> products) {
        List<ProductEntity> response = new ArrayList<>();
        for (Product product : products)
            response.add(this.domainToPersistence(product));
        return response;
    }

    private Category defineCategory(CategoryEntity value) {
        return MapperCategoryPersistenceDomain.mapPersistenceDomain(value);
    }

    private Units defineUnits(UnitsEnum value) {
        try {
            Units units = Units.valueOf(value.name());
            return units;
        } catch (Exception e) {
            this.formatter.returnResponseBadFormat("The values accepted are: " + Units.values());
            return null;
        }
    }

    private CategoryEntity defineCategory(Category value) {
        return MapperCategoryPersistenceDomain.mapDomainPeristence(value);
    }

    private UnitsEnum defineUnits(Units value) {
        try {
            UnitsEnum units = UnitsEnum.valueOf(value.name());
            return units;
        } catch (Exception e) {
            this.formatter.returnResponseBadFormat("The values accepted are: " + Units.values());
            return null;
        }
    }
}
