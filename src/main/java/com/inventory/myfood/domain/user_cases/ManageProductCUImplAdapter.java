package com.inventory.myfood.domain.user_cases;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.inventory.myfood.application.input.ManageProductCUIntPort;
import com.inventory.myfood.application.output.ExceptionFormatterIntPort;
import com.inventory.myfood.application.output.ManageProductGatewayIntPort;
import com.inventory.myfood.domain.agregates.Product;
import com.inventory.myfood.domain.value_objects.Category;

/**
 * Clase encargada de implementar las reglas de negocio referentes a la getión
 * del inventario.
 * 
 * @author Santiago Benitez Lopez sbenitez1607@gmail.com
 *         www.github.com/SBenitezL
 */
public class ManageProductCUImplAdapter implements ManageProductCUIntPort {
    private final ManageProductGatewayIntPort gateway;
    private final ExceptionFormatterIntPort formatter;

    public ManageProductCUImplAdapter(ManageProductGatewayIntPort gateway, ExceptionFormatterIntPort formatter) {
        this.gateway = gateway;
        this.formatter = formatter;
    }

    @Override
    public boolean isCategory(String category) {
        return Category.isCategory(category);
    }

    @Override
    public List<Product> checkInventoryAvailability(List<Product> demand) {
        List<Product> missingProducts = new ArrayList<>();
        for (Product product : demand) {
            Product inventory = this.gateway.findByIdAndIsNotExpired(product.getId());
            if (inventory == null) {
                missingProducts.add(product);
                continue;
            }
            if (inventory.getStock().getAmount() < product.getStock().getAmount())
                continue;
            product.decreaseStock(inventory.getStock().getAmount());
            missingProducts.add(product);
        }
        return missingProducts;
    }

    @Override
    public Product saveProduct(Product product) {
        if (!product.isValidName())
            this.formatter.returnResponseBusinessRuleViolated("Name can't be empty.");
        if (this.gateway.existByName(product.getName().getName()))
            this.formatter.returnResponseErrorEntityExists(
                    "The product with name: " + product.getName().getName() + "already exist in the system.");
        if (product.getCategory() == null)
            this.formatter.returnResponseBusinessRuleViolated("Category can't be null.");
        if (product.getUnit() == null)
            this.formatter.returnResponseBusinessRuleViolated("Units can't be empty.");
        if (product.isExpired())
            this.formatter.returnResponseBusinessRuleViolated("Can't save products expired.");
        if (!product.isValidStock())
            this.formatter.returnResponseBusinessRuleViolated("Stock must be major or equal than 0.");
        return this.gateway.saveProduct(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Product old = this.gateway.findByProductId(product.getId());
        if (old == null)
            this.formatter.returnResponseErrorEntityNotFound(
                    "The product with id: " + product.getId() + " is not registered in the inventory.");
        if (product.getCategory() == null)
            this.formatter.returnResponseBusinessRuleViolated("Category can't be null.");
        if (!old.getName().getName().equals(product.getName().getName())) {
            if (!product.isValidName())
                this.formatter.returnResponseBusinessRuleViolated("Name can't be empty.");
            if (this.gateway.existByName(product.getName().getName()))
                this.formatter.returnResponseBusinessRuleViolated(
                        "All ready exist a product with name " + product.getName().getName() + ".");
        }
        if (product.getUnit() == null)
            this.formatter.returnResponseBusinessRuleViolated("Units can't be empty.");

        return this.gateway.saveProduct(product);
    }

    @Override
    public Product changeName(String uuid, String name) {
        if (name.isBlank())
            this.formatter.returnResponseBusinessRuleViolated("Name can't be empty.");
        if (this.gateway.existByName(name))
            this.formatter.returnResponseBusinessRuleViolated("All ready exist a product with name " + name + ".");
        Product old = this.gateway.findByProductId(uuid);
        if (old == null)
            this.formatter.returnResponseErrorEntityNotFound(
                    "The product with id: " + uuid + " is not registered in the inventory.");
        old.changeName(name);
        return this.gateway.saveProduct(old);
    }

    @Override
    public List<Product> getAll() {
        List<Product> inventory = this.gateway.findAll();
        if (inventory == null || inventory.isEmpty())
            this.formatter.returNoData("There is no information recorded in the inventory.");
        return inventory;
    }

    @Override
    public List<Product> getProductExistences() {
        List<Product> inventory = this.gateway.findNotExpiredWithAmount(0.0);
        if (inventory == null || inventory.isEmpty())
            this.formatter.returNoData("There is no information recorded in the inventory.");
        return inventory;
    }

    @Override
    public List<Product> getWithOutExistences() {
        List<Product> inventory = this.gateway.findWithoutExistenses();
        if (inventory == null || inventory.isEmpty())
            this.formatter.returNoData("There is no information recorded in the inventory.");
        return inventory;
    }

    @Override
    public List<Product> getExpired() {
        List<Product> inventory = this.gateway.findExpired();
        if (inventory == null || inventory.isEmpty())
            this.formatter.returNoData("There is no information recorded in the inventory.");
        return inventory;
    }

    @Override
    public List<Product> getOneWeekToExpire() {
        Calendar nextDate = Calendar.getInstance();
        nextDate.add(Calendar.DATE, 7);
        List<Product> inventory = this.gateway.findNearToExpire(nextDate.getTime());
        if (inventory == null || inventory.isEmpty())
            this.formatter.returNoData("There is no information recorded in the inventory.");
        return inventory;
    }

    @Override
    public Product decreaseStock(String uuid, Double amount) {
        if (amount <= 0)
            this.formatter.returnResponseBusinessRuleViolated("The amount to decrease must be major than 0");
        Product old = this.gateway.findByProductId(uuid);
        if (old == null)
            this.formatter.returnResponseErrorEntityNotFound(
                    "The product with id: " + uuid + " is not registered in the inventory.");
        if (!old.decreaseStock(amount)) {
            this.formatter.returnResponseBusinessRuleViolated("You can not remove more units than the existing ones");
        }
        return this.gateway.saveProduct(old);
    }

    @Override
    public Product increaseStock(String uuid, Double amount) {
        if (amount <= 0)
            this.formatter.returnResponseBusinessRuleViolated("The amount to decrease must be major than 0");
        Product old = this.gateway.findByProductId(uuid);
        if (old == null)
            this.formatter.returnResponseErrorEntityNotFound(
                    "The product with id: " + uuid + " is not registered in the inventory.");
        old.increaseStock(amount);
        return this.gateway.saveProduct(old);
    }

    @Override
    public Product markExpired(String uuid) {
        Product old = this.gateway.findByProductId(uuid);
        if (old == null)
            this.formatter.returnResponseErrorEntityNotFound(
                    "The product with id: " + uuid + " is not registered in the inventory.");
        if (!old.calculeExpired())
            this.formatter.returnResponseBusinessRuleViolated("The product has not expired yet");
        return this.saveProduct(old);
    }

    @Override
    public List<Product> updateExpired() {
        // Lista de productos no marcados como expirados
        List<Product> unmarked = new ArrayList<>();
        List<Product> expired = this.gateway.findNearToExpire(new Date());
        for (Product product : expired) {
            if (product.isExpired())
                continue;
            if (!product.calculeExpired())
                continue;
            unmarked.add(product);
        }
        return this.gateway.saveAll(unmarked);
    }

}
