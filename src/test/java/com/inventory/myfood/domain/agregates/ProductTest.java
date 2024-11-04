package com.inventory.myfood.domain.agregates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.inventory.myfood.domain.value_objects.ProductName;
import com.inventory.myfood.domain.value_objects.Stock;
import com.inventory.myfood.domain.value_objects.Units;

@TestInstance(Lifecycle.PER_CLASS)
public class ProductTest {
    private Product objProduct;

    @BeforeAll
    void configuration() {
        // Idntifiador único
        String id = UUID.randomUUID().toString();
        // Nombre del producto
        ProductName name = new ProductName("Zanahoria");
        // Categoría
        Category category = new Category(UUID.randomUUID().toString(), "Vegetales", false);
        // Cantidad de producto
        Stock stock = new Stock(15.0);
        // Unidades en las que se mide la cantidad de producto
        Units unit = Units.POUNDS;
        // Calculo para que tenga 1 día de vida util.
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        // Vida util
        Date usefulLife = calendar.getTime();
        // Creación de la instancia
        this.objProduct = new Product(id, name, category, stock, unit, usefulLife);
    }

    // #region changeName
    @Test
    void changeNameEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            objProduct.changeName("");
        });
    }

    @Test
    void changeNameBlank() {
        assertThrows(IllegalArgumentException.class, () -> {
            objProduct.changeName("  ");
        });
    }

    @Test
    void changeNameValid() {
        final ProductName name = new ProductName("Brocoli");
        objProduct.changeName("Brocoli");
        assertEquals(name.getName(), objProduct.getName().getName());
    }
    // #endregion

    // #region Decrease
    @Test
    void decreaseStockNegative() {
        assertEquals(false, objProduct.decreaseStock(-0.1));
    }

    @Test
    void decreaseStockZero() {
        assertEquals(true, objProduct.decreaseStock(0.0));
    }

    @Test
    void decreaseStockGreatterThanZero() {
        assertEquals(true, objProduct.decreaseStock(0.1));
    }
    // #endregion
}
