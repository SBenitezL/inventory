package com.inventory.myfood.application.input;

import java.util.List;

import com.inventory.myfood.domain.agregates.Category;

/**
 * Puerto con el que comunica la capa de infraestructura con el dominio, donde
 * se consumen los servicios ofrecidos para el manejo de Categorías.
 */
public interface ManageCategoryCUIntPort {
    /**
     * Registra una nueva categoría en el sistema
     * 
     * @param category información de la categoría a registrar
     * @return {@code Category} instancia de la categoría registrada.
     */
    Category save(Category category);

    /**
     * Actualiza la información de una categoría previamente registrada.
     * 
     * @param category nueva información de la categoría
     * @return {@code Category} información guardada de la categoría.
     */
    Category update(Category category);

    /**
     * Cambia la información del nombre de la categoría.
     * 
     * @param uuid identificador de la categoría a modificar.
     * @param name nuevo nombre que se va a asignar.
     * @return {@code Category} Categoría con el nuevo nombre.
     */
    Category changeName(String uuid, String name);

    /**
     * Recupera toda la información de categorías registradas.
     * 
     * @return {@code List<Category>} con la información solicitada
     * @see Category
     */
    List<Category> findAll();

    /**
     * Recupera toda la información de una categoría registrada.
     * 
     * @param uuid identificador de la categoría a buscar.
     * @return {@code Category} con la información solicitada
     * @see Category
     */
    Category findById(String uuid);
}
