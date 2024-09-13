package com.emazon.stock.domain.api;

import com.emazon.stock.domain.model.Pagination;
import com.emazon.stock.domain.model.Product;
import com.emazon.stock.domain.util.PaginationUtil;

public interface IProductServicePort {
    void saveProduct(Product product);
    Pagination<Product> getAllProductsPaginated(PaginationUtil paginationUtil);
    void updateProduct(Product product);
    void getProductById(Long productId);
}
