package com.inventory.myfood.domain.use_cases;

import java.util.List;

import com.inventory.myfood.application.input.ManageCategoryCUIntPort;
import com.inventory.myfood.application.output.ExceptionFormatterIntPort;
import com.inventory.myfood.application.output.ManageCategoryGatewayIntPort;
import com.inventory.myfood.domain.agregates.Category;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ManageCategoryCUImplAdapter implements ManageCategoryCUIntPort {

    private final ManageCategoryGatewayIntPort serviceDB;
    private final ExceptionFormatterIntPort formatter;

    @Override
    public Category save(Category category) {
        if (!category.isValidname())
            this.formatter.returnResponseBusinessRuleViolated("The name can't be blank.");
        if (serviceDB.existByName(category.getName().getName()))
            this.formatter.returnResponseErrorEntityExists(
                    "All ready exists a Category with name " + category.getName().getName() + ".");
        return serviceDB.save(category);
    }

    @Override
    public Category update(Category category) {
        if (!this.serviceDB.existById(category.getId()))
            this.formatter.returNoData("A category has not been registered with the provided identifier");
        Category old = this.serviceDB.findById(category.getId());
        if (!category.getName().getName().equals(old.getName().getName())) {
            if (!category.isValidname())
                this.formatter.returnResponseBusinessRuleViolated("The name can't be blank.");
            if (serviceDB.existByName(category.getName().getName()))
                this.formatter.returnResponseErrorEntityExists(
                        "All ready exists a Category with name " + category.getName().getName() + ".");
        }
        old.update(category.getName().getName());
        return this.serviceDB.save(old);
    }

    @Override
    public Category changeName(String uuid, String name) {
        if (!this.serviceDB.existById(uuid))
            this.formatter.returNoData("A category has not been registered with the provided identifier");
        Category old = this.serviceDB.findById(uuid);
        if (!old.isValidname(name))
            this.formatter.returnResponseBusinessRuleViolated("The name can't be blank.");
        if (serviceDB.existByName(name))
            this.formatter.returnResponseErrorEntityExists(
                    "All ready exists a Category with name " + name + ".");
        old.changeName(name);
        return this.serviceDB.save(old);
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = this.serviceDB.findAll();
        if (categories.isEmpty())
            this.formatter.returNoData("There are no registered categories");
        return categories;
    }

    @Override
    public Category findById(String uuid) {
        Category category = this.serviceDB.findById(uuid);
        if (category == null)
            this.formatter.returNoData("There are no registered categories with the id " + uuid + ".");
        return category;
    }

}
