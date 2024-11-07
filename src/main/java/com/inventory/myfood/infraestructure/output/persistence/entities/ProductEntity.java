package com.inventory.myfood.infraestructure.output.persistence.entities;

import com.inventory.myfood.infraestructure.output.persistence.entities.enums.UnitsEnum;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @brief Clase ligada a la tecnología (Spring JPA), que se encarga de modelar
 *        los objetos en la base de datos SQL que se utilizará. Esta clase
 *        representa al producto que se guarda en el inventario.
 * @details Esta clase se utiliza para mapear la entidad "Product" en la base de
 *          datos.
 */
@Entity
@Table(name = "Product")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    /**
     * @brief Llave primaria de la entidad producto, se utiliza el tipo de
     *        generación UUID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String productId;
    @Column(unique = true, length = 30, nullable = false)
    /**
     * Nombre del producto, debe ser no nulo, único y con una longitud no mayor a 30
     * caracteres.
     */
    private String productName;
    /**
     * @brief Representa la cantidad del producto que se tiene en stock, ya que se
     *        pueden guardar kilos, gramos, litros, etc, se decide por guardarlo en
     *        un tipo de dato con decimales.
     */
    @Column(nullable = false)
    private Double productStock;
    /**
     * @brief Categoría a la que pertenece el producto, pertenece a un dominio.
     */
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;
    /**
     * @brief Unidades en las que se almacena el producto, pueden ser {@code Kilos},
     *        {@code Libras}, {@code Litros}, etc ...
     */
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UnitsEnum units;
    /**
     * @brief Representa la vida útil del producto, hay que tener en cuenta que hay
     *        productos que no se vencen.
     */
    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    private Date productUseFulLife;
    /**
     * @brief Da información acerca de si el producto ya ha expirado.
     */
    @Column(nullable = false)
    private boolean productExpired;

}
