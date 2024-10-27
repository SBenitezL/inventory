package com.inventory.myfood.infraestructure.output.persistence.gateway;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inventory.myfood.application.output.ManageProductGatewayIntPort;
import com.inventory.myfood.domain.agregates.Product;
import com.inventory.myfood.infraestructure.output.persistence.entities.ProductEntity;
import com.inventory.myfood.infraestructure.output.persistence.mapper.MapperProductPersistenceDomain;
import com.inventory.myfood.infraestructure.output.persistence.repositories.ProductRepository;

/**
 * Clase que implementa la lógica con la cual se conecta el dominio con la
 * infraestructura. Se encarga del intercambio de datos de dominio (agregates)
 * con objetos propios de la infraestructura (Entities).
 * 
 * @author Santiago Benitez Lopez sbenitez1607@gmail.com
 *         www.github.com/SBenitezL
 */
@Service
public class ManageProductGatewayImplAdapter implements ManageProductGatewayIntPort {
    private final ProductRepository serviceDB;
    private final MapperProductPersistenceDomain mapper;

    public ManageProductGatewayImplAdapter(ProductRepository serviceDB, MapperProductPersistenceDomain mapper) {
        this.serviceDB = serviceDB;
        this.mapper = mapper;
    }

    @Override
    public Product saveProduct(Product product) {
        return this.mapper.persistenceToDomain(this.serviceDB.save(this.mapper.domainToPersistence(product)));
    }

    @Override
    public List<Product> findAll() {
        return this.mapper.persistenceToDomain(serviceDB.findAll());
    }

    @Override
    public List<Product> findNotExpired() {
        return this.mapper.persistenceToDomain(serviceDB.findAllByProductExpiredIsFalse());
    }

    @Override
    public List<Product> findNotExpiredWithAmount(Double amount) {
        return this.mapper
                .persistenceToDomain(serviceDB.findAllByProductExpiredIsFalseAndProductStockGreaterThan(amount));
    }

    @Override
    public List<Product> findExpired() {
        return this.mapper.persistenceToDomain(serviceDB.findAllByProductExpiredIsTrue());
    }

    @Override
    public List<Product> findNearToExpire(Date date) {
        return this.mapper.persistenceToDomain(serviceDB.findAllByProductUseFulLifeBefore(date));
    }

    @Override
    public Product findByProductId(String uuid) {
        Optional<ProductEntity> search = this.serviceDB.findById(uuid);
        if (!search.isPresent())
            return null;
        return this.mapper.persistenceToDomain(search.get());

    }

    @Override
    public Product findByIdAndIsNotExpired(String uuid) {
        Optional<ProductEntity> search = this.serviceDB.findByProductIdAndProductExpiredIsFalse(uuid);
        if (!search.isPresent())
            return null;
        return this.mapper.persistenceToDomain(search.get());
    }

    @Override
    public List<Product> findWithoutExistenses() {
        return this.mapper.persistenceToDomain(serviceDB.findAllByProductStockLessThanEqual(0.0));
    }

    @Override
    public List<Product> findAllById(List<String> uuids) {
        return this.mapper.persistenceToDomain(serviceDB.findAllById(uuids));
    }

    @Override
    public List<Product> saveAll(List<Product> products) {

        return this.mapper.persistenceToDomain(this.serviceDB.saveAll((this.mapper.domainToPersistence(products))));
    }

    @Override
    public boolean existByName(String name) {
        return this.serviceDB.existsByProductName(name);
    }

}
