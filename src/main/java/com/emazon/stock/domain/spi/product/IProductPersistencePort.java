package com.emazon.stock.domain.spi.product;

import com.emazon.stock.domain.model.Pagination;
import com.emazon.stock.domain.model.Product;
import com.emazon.stock.domain.util.PaginationUtil;

public interface IProductPersistencePort {
    void saveProduct(Product product);
    Pagination<Product> getAllProductsPaginated(PaginationUtil paginationUtil);
}
