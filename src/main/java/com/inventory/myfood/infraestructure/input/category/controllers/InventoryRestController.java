package com.inventory.myfood.infraestructure.input.category.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.myfood.application.input.ManageCategoryCUIntPort;
import com.inventory.myfood.domain.agregates.Category;
import com.inventory.myfood.infraestructure.input.category.dto.request.CategoryDTORequest;
import com.inventory.myfood.infraestructure.input.category.dto.request.CategoryWithoutIdDTORequest;
import com.inventory.myfood.infraestructure.input.category.dto.response.CategoryDTOResponse;
import com.inventory.myfood.infraestructure.input.category.mapper.MapperCategoryInfraestructureDomain;

import jakarta.validation.Valid;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8080" })
@RestController
@RequestMapping("api/v1/categories")
@AllArgsConstructor
public class InventoryRestController {
    private final ManageCategoryCUIntPort domain;

    /**
     * Endpoint para la creación de categorías
     * 
     * @param request Petición con los datos de la categoría a registrar.
     * @param errors  Errores capturados de la validación del DTO
     * @return {@code CategoryDTOResponse} en caso de que se ejecute correctamente
     *         con un código de respuesta {@code HttpStatus.CREATED} y en caso
     *         contrario un {@code Error} con un código http {@code 4xx}.
     * @see com.inventory.myfood.infraestructure.exceptionHandler.RestApiException
     * @see CategoryDTOResponse
     * @see CategoryWithoutIdDTORequest
     */
    @PostMapping("")
    public ResponseEntity<?> save(@Valid @RequestBody CategoryWithoutIdDTORequest request, BindingResult errors) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse = this.catchErrors(errors);
        if (!errorResponse.isEmpty())
            return new ResponseEntity<Map<String, Object>>(errorResponse, HttpStatus.BAD_REQUEST);

        Category domain = MapperCategoryInfraestructureDomain.mapInfraestructureDomain(request);
        CategoryDTOResponse response = MapperCategoryInfraestructureDomain
                .mapDomainInfraestructure(this.domain.save(domain));
        return new ResponseEntity<CategoryDTOResponse>(response, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<?> update(@Valid @RequestBody CategoryDTORequest request, BindingResult errors) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse = this.catchErrors(errors);
        if (!errorResponse.isEmpty())
            return new ResponseEntity<Map<String, Object>>(errorResponse, HttpStatus.BAD_REQUEST);

        Category domain = MapperCategoryInfraestructureDomain.mapInfraestructureDomain(request);
        CategoryDTOResponse response = MapperCategoryInfraestructureDomain
                .mapDomainInfraestructure(this.domain.update(domain));
        return new ResponseEntity<CategoryDTOResponse>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<CategoryDTOResponse>> getAll() {
        List<CategoryDTOResponse> response = MapperCategoryInfraestructureDomain
                .mapDomainInfraestructure(this.domain.findAll());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<CategoryDTOResponse> getById(@RequestParam String uuid) {
        CategoryDTOResponse response = MapperCategoryInfraestructureDomain
                .mapDomainInfraestructure(this.domain.findById(uuid));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private Map<String, Object> catchErrors(BindingResult errors) {
        Map<String, Object> response = new HashMap<>();
        if (errors.hasErrors()) {
            List<String> listaErrores = new ArrayList<>();

            for (FieldError error : errors.getFieldErrors()) {
                listaErrores.add("The field '" + error.getField() + "‘ " + error.getDefaultMessage());
            }
            response.put("errors", listaErrores);
        }
        return response;
    }
}
