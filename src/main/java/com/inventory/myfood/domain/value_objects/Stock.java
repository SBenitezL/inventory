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

    public boolean isValidStock(boolean allowDecimals) {
        boolean response = this.amount >= 0;
        if (!allowDecimals)
            response = response && this.amount % 1 == 0;
        return response;
    }

    public boolean isValidDecrease(Double descrease, Units unit) {
        if (descrease < 0)
            return false;
        if (unit == Units.PIECES)
            return this.amount - descrease >= 0 && descrease % 1 == 0;
        return this.amount - descrease >= 0;

    }

    public boolean isValidIncrease(Double increase, Units unit) {
        if (unit == Units.PIECES)
            return increase > 0 && increase % 1 == 0;
        return increase > 0;
    }
}
