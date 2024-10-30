package com.inventory.myfood.infraestructure.output.waste.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.inventory.myfood.infraestructure.output.waste.dto.request.ProductWasteDTORequest;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

//TODO: Verificar endpoint
@FeignClient(name = "waste-microservice", url = "http://localhost:4003/api/waste")
public interface WasteServiceClient {
    @PostMapping("/register")
    Boolean registerWaste(@RequestBody List<ProductWasteDTORequest> products);
}
