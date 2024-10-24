package com.inventory.myfood.application.output;

import java.util.List;
import java.util.Date;

import com.inventory.myfood.domain.agregates.Product;

/**
 * 
 * Se encarga de establecer los métodos por los cuales el core de la aplicación
 * se comunicarácon la base de datos dando por hecho la tecnología, su función
 * principal es dar paso a la persistencia y consultas de productos dentro del
 * inventario.
 * 
 * @author Santiago Benitez López <sbenitez1607@gmail.com>
 *         www.github.com/SBenitezL
 */
public interface ManageProductGatewayIntPort {
    /**
     * Método que permite guardar la información de un producto o actualizarla en
     * caso de que ya esté registrado.
     * 
     * @param product información a guardar
     * @return {@code Product} instancia del producto almacenado en db o
     *         {@code null} en caso de error.
     */
    Product saveProduct(Product product);

    /**
     * Recupera la información de todos los productos registrados en el inventario.
     * 
     * @return {@code List<Product>} Lista con la información de los productos en
     *         caso de que se encuentren o {@code null} en caso de que el inventario
     *         esté vacio.
     */
    List<Product> findAll();

    /**
     * Recupera la información de los productos que no han expirado.
     * 
     * @return {@code List<Product>} Lista con la información de los productos en
     *         caso de que se encuentren o {@code null} en caso de que el inventario
     *         esté vacio.
     */
    List<Product> findNotExpired();

    /**
     * Recupera la información de los productos que no han expirado y que tienen mas
     * de la cantidad indicada.
     * 
     * @param amount cantidad del producto que debe haber en el inventario.
     * @return {@code List<Product>} Lista con la información de los productos en
     *         caso de que se encuentren o {@code null} en caso de que el inventario
     *         esté vacio.
     */
    List<Product> findNotExpiredWithAmount(Double amount);

    /**
     * Recupera la información de los productos registrados en el inventario que ya
     * han expirado.
     * 
     * @return {@code List<Product>} Lista con la información de los productos en
     *         caso de que se encuentren o {@code null} en caso de que el inventario
     *         esté vacio.
     */
    List<Product> findExpired();

    /**
     * Recupera los productos que expiran antes de la fecha consultada.
     * 
     * @param date fecha a consultar.
     * @return {@code List<Product>} Lista con la información de los productos en
     *         caso de que se encuentren o {@code null} en caso de que el inventario
     *         esté vacio.
     */
    List<Product> findNearToExpire(Date date);
}
