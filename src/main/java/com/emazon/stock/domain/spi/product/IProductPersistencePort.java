package com.emazon.stock.domain.spi.product;

import com.emazon.stock.domain.model.Product;

public interface IProductPersistencePort {
    void saveProduct(Product product);
}
