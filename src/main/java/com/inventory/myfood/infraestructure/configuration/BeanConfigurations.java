package com.inventory.myfood.infraestructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.inventory.myfood.application.output.ExceptionFormatterIntPort;
import com.inventory.myfood.application.output.ManageFinderCategoryGatewayIntPort;
import com.inventory.myfood.application.output.ManageProductGatewayIntPort;
import com.inventory.myfood.application.output.waste.ManageWasteServiceGatewayIntPort;
import com.inventory.myfood.domain.use_cases.ManageProductCUImplAdapter;
import com.inventory.myfood.infraestructure.input.product.mapper.MapperProductInfraestructureDomain;
import com.inventory.myfood.infraestructure.output.persistence.mapper.MapperProductPersistenceDomain;
import com.inventory.myfood.infraestructure.output.waste.mapper.MapperProductWasteInfraestructureDomain;

@Configuration
public class BeanConfigurations {
    @Bean
    public ManageProductCUImplAdapter createProductCUImplAdapter(ManageProductGatewayIntPort gateway,
            ExceptionFormatterIntPort exceptionFormater, ManageWasteServiceGatewayIntPort wasteService) {
        return new ManageProductCUImplAdapter(gateway, exceptionFormater, wasteService);
    }

    @Bean
    public MapperProductInfraestructureDomain createMapperProductInfraestructureDomain(
            ManageFinderCategoryGatewayIntPort finderCategory,
            ExceptionFormatterIntPort exceptionFormater) {
        return new MapperProductInfraestructureDomain(finderCategory, exceptionFormater);
    }

    @Bean
    public MapperProductWasteInfraestructureDomain createMapperProductWasteInfraestructureDomain() {
        return new MapperProductWasteInfraestructureDomain();
    }

    @Bean
    public MapperProductPersistenceDomain createMapperProductPersistenceDomain(
            ExceptionFormatterIntPort exceptionFormater) {
        return new MapperProductPersistenceDomain(exceptionFormater);
    }
}
