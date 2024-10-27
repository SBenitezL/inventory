package com.inventory.myfood.infraestructure.output.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.myfood.infraestructure.output.persistence.entities.ProductEntity;

import java.util.List;
import java.util.Optional;
import java.util.Date;

/**
 * @brief Interfaz ligada a la tecnología (Spring JPA) para gestionar la
 *        comunicación entre el proyecto y la base de datos.
 * @details Su función es comunicar la entidad "Product" con la tecnología de
 *          persistencia en este caso MySQL.
 */
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    List<ProductEntity> findAllByProductExpiredIsFalse();

    List<ProductEntity> findAllByProductExpiredIsFalseAndProductStockGreaterThan(Double productStock);

    List<ProductEntity> findAllByProductStockLessThanEqual(Double productStock);

    List<ProductEntity> findAllByProductExpiredIsTrue();

    List<ProductEntity> findAllByProductUseFulLifeBefore(Date date);

    Optional<ProductEntity> findByProductIdAndProductExpiredIsFalse(String productId);

    boolean existsByProductName(String productName);

}
