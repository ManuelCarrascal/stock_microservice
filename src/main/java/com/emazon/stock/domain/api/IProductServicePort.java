package com.emazon.stock.domain.api;

import com.emazon.stock.domain.model.Product;

public interface IProductServicePort {
    void saveProduct(Product product);
}
