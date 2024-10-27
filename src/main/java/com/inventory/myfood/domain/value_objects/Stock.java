package com.inventory.myfood.domain.value_objects;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Stock {
    private Double amount;

    public Stock(Double amount) {
        // Verifica que sea un valor válido
        if (amount < 0)
            throw new IllegalArgumentException("The stock can't be negative");
        this.amount = amount;
    }

    public boolean isValidStock() {
        return this.amount >= 0;
    }
}
