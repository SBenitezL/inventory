package com.inventory.myfood.domain.agregates;

import java.util.Date;

import com.inventory.myfood.domain.value_objects.Category;
import com.inventory.myfood.domain.value_objects.ProductName;
import com.inventory.myfood.domain.value_objects.Stock;
import com.inventory.myfood.domain.value_objects.Units;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String id;
    private ProductName name;
    private Category category;
    private Stock stock;
    private Units unit;
    private Date usefulLife;
    private boolean isExpired;

    public Product(String id, ProductName name, Category category, Stock stock, Units unit, Date usefulLife) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.stock = stock;
        this.unit = unit;
        this.usefulLife = usefulLife;
        this.calculeExpired();
    }

    public boolean decreaseStock(Double amount) {
        // Calcula la nueva cantidad
        amount = this.stock.getAmount() - amount;
        // Determina si es válida la nueva cantidad.
        if (amount < 0)
            return false;
        // Actualiza el inventario
        this.stock = new Stock(amount);
        return true;
    }

    public boolean calculeExpired() {
        if (this.usefulLife == null) {
            this.isExpired = false;
            return isExpired;
        }
        // Determina si esta vencido el producto
        this.isExpired = this.usefulLife.before(new Date());
        return isExpired;
    }

    public boolean increaseStock(Double amount) {
        // Verifica que la cantidad a ingresar sea válida.
        if (amount <= 0)
            return false;
        // Calcula la nueva cantidad
        amount += this.stock.getAmount();
        // Actualiza la nueva cantidad
        this.stock = new Stock(amount);
        return true;
    }

    public boolean isCategory(String catgory) {
        return Category.isCategory(catgory);
    }

    public boolean isValidName() {
        return this.name.isValidName();
    }

    public boolean isValidStock() {
        return this.stock.isValidStock();
    }

    public void update(Product data) {
        this.category = data.category;
        this.name = data.name;
        this.unit = data.unit;
        this.usefulLife = data.usefulLife;
        this.calculeExpired();
    }

    public void changeName(String name) {
        this.name = new ProductName(name);
    }

}
