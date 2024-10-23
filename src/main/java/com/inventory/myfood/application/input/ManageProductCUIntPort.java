package com.inventory.myfood.application.input;

import com.inventory.myfood.domain.agregates.Product;

import java.util.List;

public interface ManageProductCUIntPort {

    /**
     * Crea un nuevo producto
     * 
     * @param product producto a crear
     * @return el {@code Product} que se persiste o {@code null} en caso de un error
     */
    Product saveProduct(Product product);

    /**
     * Actualiza la información de un producto
     * 
     * @param product Nueva información del producto
     * @return el {@code Product} que se persiste o {@code null} en caso de un error
     */
    Product updateProduct(Product product);

    /**
     * Permite cambiar - corregir el nombre de un producto
     * 
     * @param uuid Identificador del producto a actualizar
     * @param name Nuevo nombre del producto
     * @return el {@code Product} que se persiste o {@code null} en caso de un error
     */
    Product changeName(String uuid, String name);

    /**
     * Recupera todos los productos que se han registrado en el sistema.
     * 
     * @return {@code List<Product>} con la información del inventario o
     *         {@code null} en caso de que este vacío.
     */
    List<Product> getAll();

    /**
     * Recupera todos los productos que cuentan con existencias en el
     * inventario.
     * 
     * @return {@code List<Product>} con la información del inventario o
     *         {@code null} en caso de que este vacío.
     */
    List<Product> getProductExistences();

    /**
     * Recupera todos los productos que no cuentan con existencias en el
     * inventario.
     * 
     * @return {@code List<Product>} con la información del inventario o
     *         {@code null} en caso de que este vacío.
     */
    List<Product> getWithOutExistences();

    /**
     * Recupera todos los productos que han expirado en el
     * inventario.
     * 
     * @return {@code List<Product>} con la información del inventario o
     *         {@code null} en caso de que este vacío.
     */
    List<Product> getExpired();

    /**
     * Recupera todos los productos que estan a 1 semana de expirar
     * inventario.
     * 
     * @return {@code List<Product>} con la información del inventario o
     *         {@code null} en caso de que este vacío.
     */
    List<Product> getOneWeekToExpire();
}
