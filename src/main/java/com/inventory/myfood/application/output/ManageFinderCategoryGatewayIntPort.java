package com.inventory.myfood.application.output;

import java.util.List;

import com.inventory.myfood.domain.agregates.Category;

public interface ManageFinderCategoryGatewayIntPort {
    /**
     * Método que solicita toda la información de las Categorías almacenadas en el
     * servicio de persistencia.
     * 
     * @return {@code List<Category>} con la información recuperada.
     */
    List<Category> findAll();

    /**
     * Método que solicita una Categoría a partir de su id
     * 
     * @param id Identificador de la categoría.
     * @return {@code Category} categoría encontrada o {@code null} en caso de que
     *         no encuentre nada.
     */
    Category findById(String id);

    /**
     * Método que pregunta al servicio de persistencia por la existancia de un
     * nombre en las categorías.
     * 
     * @param name nombre a buscar
     * @return {@code true} en caso de que el servicio encuentre coincidencias y
     *         {@code false} en caso contrario.
     */
    boolean existByName(String name);

    /**
     * Método que pregunta al servicio de persistencia si el identificador pertenece
     * a alguna categoría registrada.
     * 
     * @param uuid Identificador de la categoría.
     * @return {@code true} en caso de que el servicio encuentre coincidencias y
     *         {@code false} en caso contrario.
     */
    boolean existById(String uuid);
}
