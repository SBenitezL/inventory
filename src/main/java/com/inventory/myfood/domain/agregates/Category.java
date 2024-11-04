package com.inventory.myfood.domain.agregates;

import com.inventory.myfood.domain.value_objects.CategoryName;

import lombok.Getter;

@Getter
public class Category {
    private String id;
    private CategoryName name;

    public Category(String id, String name) {
        this.id = id;
        this.name = new CategoryName(name);
    }

    public void changeName(String name) {
        this.name = new CategoryName(name);
    }

    public void update(String name) {
        this.name = new CategoryName(name);
    }

    public boolean isValidname(String name) {
        return CategoryName.isValidName(name);
    }

    public boolean isValidname() {
        return CategoryName.isValidName(name.getName());
    }
}
