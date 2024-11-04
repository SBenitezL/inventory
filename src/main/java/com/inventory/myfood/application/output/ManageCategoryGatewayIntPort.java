package com.inventory.myfood.application.output;

import com.inventory.myfood.domain.agregates.Category;

/**
 * Gateway que permite a las entidades del dominio comunicarse con los servicios
 * de persistencia de Categoría.
 */
public interface ManageCategoryGatewayIntPort extends ManageFinderCategoryGatewayIntPort {

    /**
     * Metodo que solicita guardar cambios o agregar nuevas Categorías en el
     * servicio
     * de persistencia.
     * 
     * @param category información a salvar.
     * @return {@code Category} información almacenada en el servicio de
     *         persistencia.
     */
    Category save(Category category);

}
