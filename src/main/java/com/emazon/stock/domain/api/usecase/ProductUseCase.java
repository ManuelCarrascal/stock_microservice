package com.emazon.stock.domain.api.usecase;

import com.emazon.stock.domain.api.IProductServicePort;
import com.emazon.stock.domain.model.Product;
import com.emazon.stock.domain.spi.product.IProductPersistencePort;

public class ProductUseCase implements IProductServicePort {

    private final IProductPersistencePort productPersistencePort;

    public ProductUseCase(IProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }

    @Override
    public void saveProduct(Product product) {
        productPersistencePort.saveProduct(product);
    }

}
