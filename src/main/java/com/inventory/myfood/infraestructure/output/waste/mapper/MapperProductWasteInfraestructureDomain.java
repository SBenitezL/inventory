package com.inventory.myfood.infraestructure.output.waste.mapper;

import java.util.List;
import java.util.ArrayList;

import com.inventory.myfood.domain.agregates.Product;
import com.inventory.myfood.infraestructure.output.waste.dto.request.ProductWasteDTORequest;

public class MapperProductWasteInfraestructureDomain {
    public List<ProductWasteDTORequest> mapDomainToInfraestructure(List<Product> products) {
        List<ProductWasteDTORequest> mapped = new ArrayList<>();
        for (Product product : products) {
            mapped.add(new ProductWasteDTORequest(product.getId(), product.getName().getName(),
                    product.getCategory().name(), product.getStock().getAmount()));
        }
        return mapped;
    }
}
