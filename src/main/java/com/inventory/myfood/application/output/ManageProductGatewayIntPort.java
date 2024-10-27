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
     * Recupera la información de un único producto.
     * 
     * @param uuid identificador del producto.
     * @return {@code Product} información del producto encontrado o {@code null} en
     *         caso de no encontrarlo.
     */
    Product findByProductId(String uuid);

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

    /**
     * Recupera la información de un producto a partir de su identificador solo si
     * no ha expirado.
     * 
     * @param uuid identificador del producto a buscar.
     * @return {@code Product} información del producto encontrado en caso de que se
     *         cumplan las condiciones o {@code null} en caso de no encontrar
     *         coincidencias.
     */
    Product findByIdAndIsNotExpired(String uuid);

    /**
     * Recupera los productos que tienen 0 unidades almacenadas.
     * 
     * @return {@code List<Product>} Lista con la información de los productos en
     *         caso de que se encuentren o {@code null} en caso de que el inventario
     *         esté vacio.
     */
    List<Product> findWithoutExistenses();

    /**
     * Recupera una lista de productos a partir del id.
     * 
     * @param uuids lista de ids a recuperar
     * @return {@code List<Product} con la información del inventario o {@code null}
     *         en caso de que no se encuentren coincidencias.
     */
    List<Product> findAllById(List<String> uuids);

    /**
     * Guarda la información de varios productos.
     * 
     * @param products productos a almacenar.
     * @return {@code List<Product>} lista de productos guardados.
     */
    List<Product> saveAll(List<Product> products);

    /**
     * Determina si existe una entidad almacenada con el mismo nombre.
     * 
     * @param name nombre del producto a buscar
     * @return {@code true} en caso de que se encuentren ocurrencias y {@code false}
     *         en caso contrario.
     */
    boolean existByName(String name);
}
