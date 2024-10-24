package com.inventory.myfood.infraestructure.output.persistence.gateway;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.inventory.myfood.application.output.ManageProductGatewayIntPort;
import com.inventory.myfood.domain.agregates.Product;
import com.inventory.myfood.infraestructure.output.persistence.entities.ProductEntity;
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
    private final ModelMapper mapper;

    public ManageProductGatewayImplAdapter(ProductRepository serviceDB, ModelMapper mapper) {
        this.serviceDB = serviceDB;
        this.mapper = mapper;
    }

    @Override
    public Product saveProduct(Product product) {
        return this.mapper.map(this.serviceDB.save(this.mapper.map(product, ProductEntity.class)), Product.class);
    }

    @Override
    public List<Product> findAll() {
        return this.mapper.map(serviceDB.findAll(), new TypeToken<List<Product>>() {
        }.getType());
    }

    @Override
    public List<Product> findNotExpired() {
        return this.mapper.map(serviceDB.findAllByProductExpiredIsFalse(), new TypeToken<List<Product>>() {
        }.getType());
    }

    @Override
    public List<Product> findNotExpiredWithAmount(Double amount) {
        return this.mapper.map(serviceDB.findAllByProductExpiredIsFalseAndProductStockGreaterThan(amount),
                new TypeToken<List<Product>>() {
                }.getType());
    }

    @Override
    public List<Product> findExpired() {
        return this.mapper.map(serviceDB.findAllByProductExpiredIsTrue(), new TypeToken<List<Product>>() {
        }.getType());
    }

    @Override
    public List<Product> findNearToExpire(Date date) {
        return this.mapper.map(serviceDB.findAllByProductUseFulLifeBefore(date), new TypeToken<List<Product>>() {
        }.getType());
    }

}
