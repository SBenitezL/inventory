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

    public boolean decreaseStock(int amount) {
        // Calcula la nueva cantidad
        amount = this.stock.getAmount() - amount;
        // Determina si es válida la nueva cantidad.
        if (amount < 0)
            return false;
        // Actualiza el inventario
        this.stock = new Stock(amount);
        return true;
    }

    public boolean markExpired() {
        // Determina si esta vencido el producto
        this.isExpired = this.usefulLife.after(new Date());
        return isExpired;
    }

    public boolean increaseStock(int amount) {
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

}
