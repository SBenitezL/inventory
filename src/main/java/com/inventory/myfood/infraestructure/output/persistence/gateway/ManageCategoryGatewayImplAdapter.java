package com.inventory.myfood.infraestructure.output.persistence.gateway;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inventory.myfood.application.output.ManageCategoryGatewayIntPort;
import com.inventory.myfood.domain.agregates.Category;
import com.inventory.myfood.infraestructure.output.persistence.entities.CategoryEntity;
import com.inventory.myfood.infraestructure.output.persistence.mapper.MapperCategoryPersistenceDomain;
import com.inventory.myfood.infraestructure.output.persistence.repositories.CategoryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ManageCategoryGatewayImplAdapter implements ManageCategoryGatewayIntPort {

    private final CategoryRepository serviceDB;

    @Override
    public List<Category> findAll() {
        return MapperCategoryPersistenceDomain.mapPersistenceDomain(this.serviceDB.findAll());
    }

    @Override
    public Category findById(String id) {
        Optional<CategoryEntity> entity = this.serviceDB.findById(id);
        if (!entity.isPresent())
            return null;
        return MapperCategoryPersistenceDomain.mapPersistenceDomain(entity.get());
    }

    @Override
    public Category save(Category category) {
        return MapperCategoryPersistenceDomain
                .mapPersistenceDomain(
                        this.serviceDB.save(MapperCategoryPersistenceDomain.mapDomainPeristence(category)));
    }

    @Override
    public boolean existByName(String name) {
        return this.serviceDB.existsByCategoryName(name);
    }

    @Override
    public boolean existById(String uuid) {
        return this.serviceDB.existsById(uuid);
    }

}
