package com.inventory.myfood.infraestructure.input.category.mapper;

import java.util.ArrayList;
import java.util.List;

import com.inventory.myfood.domain.agregates.Category;
import com.inventory.myfood.infraestructure.input.category.dto.request.CategoryDTORequest;
import com.inventory.myfood.infraestructure.input.category.dto.request.CategoryWithoutIdDTORequest;
import com.inventory.myfood.infraestructure.input.category.dto.response.CategoryDTOResponse;

public class MapperCategoryInfraestructureDomain {
    public static Category mapInfraestructureDomain(CategoryDTORequest request) {
        if (request == null)
            return null;
        return new Category(request.getId(), request.getName());
    }

    public static Category mapInfraestructureDomain(CategoryWithoutIdDTORequest request) {
        if (request == null)
            return null;
        return new Category(null, request.getName());
    }

    public static CategoryDTOResponse mapDomainInfraestructure(Category response) {
        if (response == null)
            return null;
        return new CategoryDTOResponse(response.getId(), response.getName().getName());
    }

    public static List<CategoryDTOResponse> mapDomainInfraestructure(List<Category> data) {
        if (data == null)
            return null;
        List<CategoryDTOResponse> response = new ArrayList<>();
        for (Category value : data)
            response.add(mapDomainInfraestructure(value));
        return response;
    }
}
