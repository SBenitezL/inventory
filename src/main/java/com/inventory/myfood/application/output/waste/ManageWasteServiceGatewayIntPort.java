package com.inventory.myfood.application.output.waste;

import java.util.List;

import com.inventory.myfood.domain.agregates.Product;

/**
 * interface de comunicación con el microservicio de desperdicio
 */
public interface ManageWasteServiceGatewayIntPort {
    /**
     * Servicio que envía al gestor de desperdicios los productos caducados.
     * 
     * @param waste productos caducados.
     * @return {@code true} en caso de que se hayan ingresado con éxito y
     *         {@code false} en caso contrario.
     */
    Boolean updateWaste(List<Product> waste);
}
