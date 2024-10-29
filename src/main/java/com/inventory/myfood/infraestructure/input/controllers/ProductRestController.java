package com.inventory.myfood.infraestructure.input.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.myfood.application.input.ManageProductCUIntPort;
import com.inventory.myfood.infraestructure.input.dto.request.ProductDTORequest;
import com.inventory.myfood.infraestructure.input.dto.request.ProductWithoutIdDTORequest;
import com.inventory.myfood.infraestructure.input.dto.response.ProductDTOResponse;
import com.inventory.myfood.infraestructure.input.mapper.MapperProductInfraestructureDomain;
import com.inventory.myfood.domain.agregates.Product;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductRestController {
    private final ManageProductCUIntPort domain;
    private final MapperProductInfraestructureDomain mapper;

    @PostMapping("")
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductWithoutIdDTORequest request,
            BindingResult errors) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse = this.catchErrors(errors);
        if (!errorResponse.isEmpty())
            return new ResponseEntity<Map<String, Object>>(errorResponse, HttpStatus.BAD_REQUEST);
        Product product = this.mapper.infraestructureToDomain(request);

        ProductDTOResponse response = this.mapper.domainToInfraestructure(this.domain.saveProduct(product));
        return new ResponseEntity<ProductDTOResponse>(response, HttpStatus.CREATED);

    }

    @PutMapping("")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductDTORequest request,
            BindingResult errors) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse = this.catchErrors(errors);
        if (!errorResponse.isEmpty())
            return new ResponseEntity<Map<String, Object>>(errorResponse, HttpStatus.BAD_REQUEST);
        Product product = this.mapper.infraestructureToDomain(request);
        try {
            ProductDTOResponse response = this.mapper.domainToInfraestructure(this.domain.updateProduct(product));
            return new ResponseEntity<ProductDTOResponse>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            errorResponse.put("mensaje", "Error when inserting into database");
            errorResponse.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PatchMapping("/identifier/{productId}")
    public ResponseEntity<?> updateExpired(
            @Valid @NotBlank(message = "The identifier can't be empty.") @PathVariable String productId,
            BindingResult errors) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse = this.catchErrors(errors);
        if (!errorResponse.isEmpty())
            return new ResponseEntity<Map<String, Object>>(errorResponse, HttpStatus.BAD_REQUEST);
        try {
            ProductDTOResponse response = this.mapper.domainToInfraestructure(this.domain.markExpired(productId));
            return new ResponseEntity<ProductDTOResponse>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            errorResponse.put("mensaje", "Error when inserting into database");
            errorResponse.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/identifier/{productId}/name/{name}")
    public ResponseEntity<?> changeName(
            @Valid @NotBlank(message = "The identifier can't be empty.") @PathVariable String productId,
            @Valid @NotBlank(message = "The identifier can't be empty.") @PathVariable String name,
            BindingResult errors) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse = this.catchErrors(errors);
        if (!errorResponse.isEmpty())
            return new ResponseEntity<Map<String, Object>>(errorResponse, HttpStatus.BAD_REQUEST);
        try {
            ProductDTOResponse response = this.mapper.domainToInfraestructure(this.domain.changeName(productId, name));
            return new ResponseEntity<ProductDTOResponse>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            errorResponse.put("mensaje", "Error when inserting into database");
            errorResponse.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/identifier/{productId}/amount/{amount}")
    public ResponseEntity<?> increase(
            @Valid @NotBlank(message = "The identifier can't be empty.") @PathVariable String productId,
            @Valid @NotBlank(message = "The identifier can't be empty.") @PathVariable Double amount,
            BindingResult errors) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse = this.catchErrors(errors);
        if (!errorResponse.isEmpty())
            return new ResponseEntity<Map<String, Object>>(errorResponse, HttpStatus.BAD_REQUEST);
        try {
            ProductDTOResponse response = this.mapper
                    .domainToInfraestructure(this.domain.increaseStock(productId, amount));
            return new ResponseEntity<ProductDTOResponse>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            errorResponse.put("mensaje", "Error when inserting into database");
            errorResponse.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/amount/{amount}/identifier/{productId}")
    public ResponseEntity<?> decrease(
            @Valid @NotBlank(message = "The identifier can't be empty.") @PathVariable Double amount,
            @Valid @NotBlank(message = "The identifier can't be empty.") @PathVariable String productId,
            BindingResult errors) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse = this.catchErrors(errors);
        if (!errorResponse.isEmpty())
            return new ResponseEntity<Map<String, Object>>(errorResponse, HttpStatus.BAD_REQUEST);
        try {
            ProductDTOResponse response = this.mapper
                    .domainToInfraestructure(this.domain.decreaseStock(productId, amount));
            return new ResponseEntity<ProductDTOResponse>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            errorResponse.put("mensaje", "Error when inserting into database");
            errorResponse.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("")
    public ResponseEntity<?> updateExpired(BindingResult errors) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse = this.catchErrors(errors);
        if (!errorResponse.isEmpty())
            return new ResponseEntity<Map<String, Object>>(errorResponse, HttpStatus.BAD_REQUEST);
        try {
            List<ProductDTOResponse> response = this.mapper.domainToInfraestructure(this.domain.updateExpired());
            return new ResponseEntity<List<ProductDTOResponse>>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            errorResponse.put("mensaje", "Error when inserting into database");
            errorResponse.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        Map<String, Object> errorResponse = new HashMap<>();
        try {
            List<ProductDTOResponse> response = this.mapper.domainToInfraestructure(this.domain.getAll());
            return new ResponseEntity<List<ProductDTOResponse>>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            errorResponse.put("mensaje", "Error when searching into database");
            errorResponse.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/existences")
    public ResponseEntity<?> getExistences() {
        Map<String, Object> errorResponse = new HashMap<>();
        try {
            List<ProductDTOResponse> response = this.mapper.domainToInfraestructure(this.domain.getProductExistences());
            return new ResponseEntity<List<ProductDTOResponse>>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            errorResponse.put("mensaje", "Error when searching into database");
            errorResponse.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/existences/any")
    public ResponseEntity<?> getWithoutExistences() {
        Map<String, Object> errorResponse = new HashMap<>();
        try {
            List<ProductDTOResponse> response = this.mapper.domainToInfraestructure(this.domain.getWithOutExistences());
            return new ResponseEntity<List<ProductDTOResponse>>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            errorResponse.put("mensaje", "Error when searching into database");
            errorResponse.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/expired")
    public ResponseEntity<?> getExpired() {
        Map<String, Object> errorResponse = new HashMap<>();
        try {
            List<ProductDTOResponse> response = this.mapper.domainToInfraestructure(this.domain.getExpired());
            return new ResponseEntity<List<ProductDTOResponse>>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            errorResponse.put("mensaje", "Error when searching into database");
            errorResponse.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/near/expire")
    public ResponseEntity<?> getNearToExpired() {
        Map<String, Object> errorResponse = new HashMap<>();
        try {
            List<ProductDTOResponse> response = this.mapper.domainToInfraestructure(this.domain.getOneWeekToExpire());
            return new ResponseEntity<List<ProductDTOResponse>>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            errorResponse.put("mensaje", "Error when searching into database");
            errorResponse.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/avalible/inventory")
    public ResponseEntity<?> checkAvalibleInventory(@Valid @RequestBody List<ProductDTORequest> requests,
            BindingResult errors) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse = this.catchErrors(errors);
        if (!errorResponse.isEmpty())
            return new ResponseEntity<Map<String, Object>>(errorResponse, HttpStatus.BAD_REQUEST);
        List<Product> products = this.mapper.infraestructureToDomain(requests);
        try {
            List<ProductDTOResponse> response = this.mapper
                    .domainToInfraestructure(this.domain.checkInventoryAvailability(products));
            return new ResponseEntity<List<ProductDTOResponse>>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            errorResponse.put("mensaje", "Error when inserting into database");
            errorResponse.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
