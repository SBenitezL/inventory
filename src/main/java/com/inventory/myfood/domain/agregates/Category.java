package com.inventory.myfood.domain.agregates;

import com.inventory.myfood.domain.value_objects.CategoryName;

import lombok.Getter;

@Getter
public class Category {
    private String id;
    private CategoryName name;
    private boolean allowDecimals = true;

    public Category(String id, String name, boolean allowDecimals) {
        this.id = id;
        this.name = new CategoryName(name);
        this.allowDecimals = allowDecimals;
    }

    public void changeName(String name) {
        this.name = new CategoryName(name);
    }

    public void changeAllowDecimals() {
        this.allowDecimals = !allowDecimals;
    }

    public void update(String name, boolean allowDecimals) {
        this.name = new CategoryName(name);
        this.allowDecimals = allowDecimals;
    }

    public boolean isValidname(String name) {
        return CategoryName.isValidName(name);
    }

    public boolean isValidname() {
        return CategoryName.isValidName(name.getName());
    }
}
