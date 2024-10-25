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
     * Se encarga de la lógica de salida de los productos del inventario.
     * 
     * @param amount cantidad de unidades salidas del inventario
     * @return {@code true} si se completa satisfactoriamente y {@code false} en
     *         caso contrario.
     */
    boolean decreaseStock(Product product, int amount);

    /**
     * Se encarga de la lógica de agregar productos al inventario.
     * 
     * @param amount cantidad de unidades a agregar al inventario.
     * @return {@code true} en caso de que se complete la acción y {@code false} en
     *         caso contrario.
     */
    boolean increaseStock(Product product, int amount);

    /**
     * Se encarga de determinar si un producto ha llegado a su fecha de caducidad y
     * lo marca de esta forma
     * 
     * @return {@code true} en caso de que el producto se haya vencido o
     *         {@code false} en caso contrario.
     */
    public boolean markExpired(Product product);

    /**
     * Determina si la categoria ingresada es válida
     * 
     * @param category categoria del alimento
     * @return {@code true} en caso de que la categoria este registrada y
     *         {@code false} en caso contrario.
     */
    boolean isCategory(String category);

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
