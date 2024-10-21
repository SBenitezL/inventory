package com.unicauca.my_food.domain.inventory.service;

import org.springframework.stereotype.Service;

import com.unicauca.my_food.domain.inventory.Product;
import com.unicauca.my_food.domain.inventory.value_objects.Category;
import com.unicauca.my_food.infrastucture.exceptionHandler.ownException.NoDataException;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ProductDomainService implements IProductDomainService {

    @Override
    public boolean decreaseStock(Product product, int amount) {
        if (product == null)
            throw new NoDataException("The product do not exits...");
        return product.decreaseStock(amount);
    }

    @Override
    public boolean increaseStock(Product product, int amount) {
        if (product == null)
            throw new NoDataException("The product do not exits...");
        return product.increaseStock(amount);
    }

    @Override
    public boolean markExpired(Product product) {
        if (product == null)
            throw new NoDataException("The product do not exits...");
        return product.markExpired();
    }

    @Override
    public boolean isCategory(String category) {
        return Category.isCategory(category);
    }

}
