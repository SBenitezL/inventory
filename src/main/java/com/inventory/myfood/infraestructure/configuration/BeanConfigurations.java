package com.inventory.myfood.infraestructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.inventory.myfood.application.output.ExceptionFormatterIntPort;
import com.inventory.myfood.application.output.ManageProductGatewayIntPort;
import com.inventory.myfood.domain.user_cases.ManageProductCUImplAdapter;
import com.inventory.myfood.infraestructure.input.mapper.MapperProductInfraestructureDomain;
import com.inventory.myfood.infraestructure.output.persistence.mapper.MapperProductPersistenceDomain;

@Configuration
public class BeanConfigurations {
    @Bean
    public ManageProductCUImplAdapter createProductCUImplAdapter(ManageProductGatewayIntPort gateway,
            ExceptionFormatterIntPort exceptionFormater) {
        return new ManageProductCUImplAdapter(gateway, exceptionFormater);
    }

    @Bean
    public MapperProductInfraestructureDomain createMapperProductInfraestructureDomain(
            ExceptionFormatterIntPort exceptionFormater) {
        return new MapperProductInfraestructureDomain(exceptionFormater);
    }

    @Bean
    public MapperProductPersistenceDomain createMapperProductPersistenceDomain(
            ExceptionFormatterIntPort exceptionFormater) {
        return new MapperProductPersistenceDomain(exceptionFormater);
    }
}
