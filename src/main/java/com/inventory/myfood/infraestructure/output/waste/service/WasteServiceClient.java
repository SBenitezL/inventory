package com.inventory.myfood.infraestructure.output.waste.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.inventory.myfood.infraestructure.output.waste.dto.request.ProductWasteDTORequest;
import com.inventory.myfood.infraestructure.output.waste.dto.response.WasteDTOResponse;

@FeignClient(name = "waste-microservice")
public interface WasteServiceClient {
    @PostMapping("/api/v1/wastes/register")
    List<WasteDTOResponse> registerWaste(@RequestBody List<ProductWasteDTORequest> products);
}
