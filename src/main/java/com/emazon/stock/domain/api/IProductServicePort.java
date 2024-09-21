package com.emazon.stock.domain.api;

import com.emazon.stock.domain.model.Pagination;
import com.emazon.stock.domain.model.Product;
import com.emazon.stock.domain.util.PaginationUtil;

import java.util.List;

public interface IProductServicePort {
    void saveProduct(Product product);

    Pagination<Product> getAllProductsPaginated(PaginationUtil paginationUtil);

    void updateProduct(Product product);

    void getProductById(Long productId);

    boolean isStockSufficient(Long productId, Integer quantity);

    Pagination<Product> getAllProductsPaginatedByIds(PaginationUtil paginationUtil, List<Long> productIds, String categoryName, String brandName);
}