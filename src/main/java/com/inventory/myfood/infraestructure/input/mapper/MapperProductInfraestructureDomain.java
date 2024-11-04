package com.inventory.myfood.infraestructure.input.mapper;

import java.util.ArrayList;
import java.util.List;

import com.inventory.myfood.application.output.ExceptionFormatterIntPort;
import com.inventory.myfood.application.output.ManageFinderCategoryGatewayIntPort;
import com.inventory.myfood.domain.agregates.Product;
import com.inventory.myfood.domain.agregates.Category;
import com.inventory.myfood.domain.value_objects.ProductName;
import com.inventory.myfood.domain.value_objects.Stock;
import com.inventory.myfood.domain.value_objects.Units;
import com.inventory.myfood.infraestructure.input.dto.request.ProductDTORequest;
import com.inventory.myfood.infraestructure.input.dto.request.ProductWithoutIdDTORequest;
import com.inventory.myfood.infraestructure.input.dto.response.ProductDTOResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MapperProductInfraestructureDomain {
    private final ManageFinderCategoryGatewayIntPort finderCategory;
    public final ExceptionFormatterIntPort formatter;

    public Product infraestructureToDomain(ProductWithoutIdDTORequest request) {
        Category category = this.finderCategory.findById(request.getCategory());
        Units units = this.defineUnits(request.getUnits());
        Stock stock = new Stock(request.getStock());
        ProductName name = new ProductName(request.getName());
        return new Product("", name, category, stock, units, request.getUsefulLife());
    }

    public Product infraestructureToDomain(ProductDTORequest request) {
        Category category = this.defineCategory(request.getCategory());
        Units units = this.defineUnits(request.getUnits());
        Stock stock = new Stock(request.getStock());
        ProductName name = new ProductName(request.getName());
        return new Product(request.getId(), name, category, stock, units, request.getUsefulLife());
    }

    public ProductDTOResponse domainToInfraestructure(Product domain) {
        return new ProductDTOResponse(domain.getId(), domain.getName().getName(), domain.getStock().getAmount(),
                domain.getCategory(), domain.getUnit().name(), domain.getUsefulLife(), domain.isExpired());
    }

    public List<Product> infraestructureToDomain(List<ProductDTORequest> requests) {
        List<Product> domain = new ArrayList<>();
        for (ProductDTORequest request : requests)
            domain.add(this.infraestructureToDomain(request));
        return domain;
    }

    public List<ProductDTOResponse> domainToInfraestructure(List<Product> products) {
        List<ProductDTOResponse> response = new ArrayList<>();
        for (Product product : products)
            response.add(this.domainToInfraestructure(product));
        return response;
    }

    private Category defineCategory(String uuid) {
        Category value = this.finderCategory.findById(uuid);
        if (value == null)
            this.formatter.returNoData("A category was not found with the given identifier");
        return value;
    }

    private Units defineUnits(String value) {
        try {
            Units units = Units.valueOf(value);
            return units;
        } catch (Exception e) {
            String valid = "";
            for (int i = 0; i < Units.values().length; i++)
                valid += Units.values()[i].name() + " ";
            this.formatter.returnResponseBadFormat("The values accepted are: " + valid);
            return null;
        }
    }
}
