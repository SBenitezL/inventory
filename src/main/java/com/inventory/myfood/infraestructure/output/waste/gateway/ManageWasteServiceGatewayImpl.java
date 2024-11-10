package com.inventory.myfood.infraestructure.output.waste.gateway;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.myfood.application.output.waste.ManageWasteServiceGatewayIntPort;
import com.inventory.myfood.domain.agregates.Product;
import com.inventory.myfood.infraestructure.output.waste.dto.response.WasteDTOResponse;
import com.inventory.myfood.infraestructure.output.waste.mapper.MapperProductWasteInfraestructureDomain;
import com.inventory.myfood.infraestructure.output.waste.service.WasteServiceClient;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ManageWasteServiceGatewayImpl implements ManageWasteServiceGatewayIntPort {
    @Autowired
    private final WasteServiceClient wasteService;
    private final MapperProductWasteInfraestructureDomain mapper;

    @Override
    public List<WasteDTOResponse> updateWaste(List<Product> waste) {
        return this.wasteService.registerWaste(this.mapper.mapDomainToInfraestructure(waste));
    }

}
