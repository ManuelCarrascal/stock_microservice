package com.emazon.stock.domain.api.usecase;

import com.emazon.stock.domain.api.IProductServicePort;
import com.emazon.stock.domain.model.Pagination;
import com.emazon.stock.domain.model.Product;
import com.emazon.stock.domain.spi.product.IProductPersistencePort;
import com.emazon.stock.domain.util.PaginationUtil;
import com.emazon.stock.domain.util.PaginationValidator;


public class ProductUseCase implements IProductServicePort {

    private final IProductPersistencePort productPersistencePort;

    public ProductUseCase(IProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }

    @Override
    public void saveProduct(Product product) {
        productPersistencePort.saveProduct(product);
    }

    @Override
    public Pagination<Product> getAllProductsPaginated(PaginationUtil paginationUtil) {
        PaginationValidator.validate(paginationUtil);
        return productPersistencePort.getAllProductsPaginated(paginationUtil);
    }

    @Override
    public void updateProduct(Product product) {
        productPersistencePort.updateProduct(product);
    }

}
