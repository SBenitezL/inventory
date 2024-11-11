package com.inventory.myfood.infraestructure.input.product.controllers;

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
import com.inventory.myfood.application.output.ExceptionFormatterIntPort;
import com.inventory.myfood.infraestructure.input.product.dto.request.ProductDTORequest;
import com.inventory.myfood.infraestructure.input.product.dto.request.ProductWithoutIdDTORequest;
import com.inventory.myfood.infraestructure.input.product.dto.response.ProductDTOResponse;
import com.inventory.myfood.infraestructure.input.product.mapper.MapperProductInfraestructureDomain;
import com.inventory.myfood.infraestructure.output.waste.dto.response.WasteDTOResponse;
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
    private final ExceptionFormatterIntPort formatter;

    @PostMapping("")
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductWithoutIdDTORequest request,
            BindingResult errors) {
        this.catchErrors(errors);
        Product product = this.mapper.infraestructureToDomain(request);
        ProductDTOResponse response = this.mapper.domainToInfraestructure(this.domain.saveProduct(product));
        return new ResponseEntity<ProductDTOResponse>(response, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductDTORequest request,
            BindingResult errors) {
        this.catchErrors(errors);
        Product product = this.mapper.infraestructureToDomain(request);
        ProductDTOResponse response = this.mapper.domainToInfraestructure(this.domain.updateProduct(product));
        return new ResponseEntity<ProductDTOResponse>(response, HttpStatus.OK);

    }

    @PostMapping("/clean")
    public ResponseEntity<List<ProductDTOResponse>> cleanExpired() {
        List<ProductDTOResponse> response = this.mapper.domainToInfraestructure(this.domain.removeExpired());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/identifier/{productId}")
    public ResponseEntity<?> updateExpired(
            @Valid @NotBlank(message = "The identifier can't be empty.") @PathVariable String productId,
            BindingResult errors) {
        this.catchErrors(errors);
        ProductDTOResponse response = this.mapper.domainToInfraestructure(this.domain.markExpired(productId));
        return new ResponseEntity<ProductDTOResponse>(response, HttpStatus.OK);

    }

    @PatchMapping("/identifier/{productId}/name/{name}")
    public ResponseEntity<?> changeName(
            @Valid @NotBlank(message = "The identifier can't be empty.") @PathVariable String productId,
            @Valid @NotBlank(message = "The identifier can't be empty.") @PathVariable String name,
            BindingResult errors) {
        this.catchErrors(errors);
        ProductDTOResponse response = this.mapper.domainToInfraestructure(this.domain.changeName(productId, name));
        return new ResponseEntity<ProductDTOResponse>(response, HttpStatus.OK);
    }

    @PatchMapping("/identifier/{productId}/amount/{amount}")
    public ResponseEntity<?> increase(
            @Valid @NotBlank(message = "The identifier can't be empty.") @PathVariable String productId,
            @Valid @NotBlank(message = "The identifier can't be empty.") @PathVariable Double amount,
            BindingResult errors) {
        this.catchErrors(errors);
        ProductDTOResponse response = this.mapper
                .domainToInfraestructure(this.domain.increaseStock(productId, amount));
        return new ResponseEntity<ProductDTOResponse>(response, HttpStatus.OK);
    }

    @PatchMapping("/amount/{amount}/identifier/{productId}")
    public ResponseEntity<?> decrease(
            @Valid @NotBlank(message = "The identifier can't be empty.") @PathVariable Double amount,
            @Valid @NotBlank(message = "The identifier can't be empty.") @PathVariable String productId,
            BindingResult errors) {
        Map<String, Object> errorResponse = new HashMap<>();
        this.catchErrors(errors);
        ProductDTOResponse response = this.mapper
                .domainToInfraestructure(this.domain.decreaseStock(productId, amount));
        return new ResponseEntity<ProductDTOResponse>(response, HttpStatus.OK);
    }

    @PatchMapping("")
    public ResponseEntity<?> updateExpired(BindingResult errors) {
        this.catchErrors(errors);
        List<ProductDTOResponse> response = this.mapper.domainToInfraestructure(this.domain.updateExpired());
        return new ResponseEntity<List<ProductDTOResponse>>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        List<ProductDTOResponse> response = this.mapper.domainToInfraestructure(this.domain.getAll());
        return new ResponseEntity<List<ProductDTOResponse>>(response, HttpStatus.OK);
    }

    @GetMapping("/existences")
    public ResponseEntity<?> getExistences() {
        List<ProductDTOResponse> response = this.mapper.domainToInfraestructure(this.domain.getProductExistences());
        return new ResponseEntity<List<ProductDTOResponse>>(response, HttpStatus.OK);
    }

    @GetMapping("/existences/any")
    public ResponseEntity<?> getWithoutExistences() {
        List<ProductDTOResponse> response = this.mapper.domainToInfraestructure(this.domain.getWithOutExistences());
        return new ResponseEntity<List<ProductDTOResponse>>(response, HttpStatus.OK);
    }

    @GetMapping("/expired")
    public ResponseEntity<?> getExpired() {
        List<ProductDTOResponse> response = this.mapper.domainToInfraestructure(this.domain.getExpired());
        return new ResponseEntity<List<ProductDTOResponse>>(response, HttpStatus.OK);
    }

    @GetMapping("/near/expire")
    public ResponseEntity<?> getNearToExpired() {

        List<ProductDTOResponse> response = this.mapper.domainToInfraestructure(this.domain.getOneWeekToExpire());
        return new ResponseEntity<List<ProductDTOResponse>>(response, HttpStatus.OK);
    }

    @PostMapping("/avalible/inventory")
    public ResponseEntity<?> checkAvalibleInventory(@Valid @RequestBody List<ProductDTORequest> requests,
            BindingResult errors) {
        this.catchErrors(errors);

        List<Product> products = this.mapper.infraestructureToDomain(requests);

        List<ProductDTOResponse> response = this.mapper
                .domainToInfraestructure(this.domain.checkInventoryAvailability(products));
        return new ResponseEntity<List<ProductDTOResponse>>(response, HttpStatus.OK);
    }

    private void catchErrors(BindingResult errors) {
        if (errors.hasErrors()) {
            String msg = "";

            for (FieldError error : errors.getFieldErrors()) {
                msg += "The field '" + error.getField() + "‘ " + error.getDefaultMessage() + "\n";
            }
            this.formatter.returnResponseBadFormat(msg);
        }
    }

}
