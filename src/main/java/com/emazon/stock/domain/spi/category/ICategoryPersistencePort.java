package com.emazon.stock.domain.spi.category;

import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.Pagination;
import com.emazon.stock.domain.util.PaginationUtil;

import java.util.List;

public interface ICategoryPersistencePort {
    void saveCategory(Category category);
    boolean categoryExistsByName(String categoryName);
    Pagination<Category> getAllCategoriesPaginated(PaginationUtil paginationUtil);
    List<Category> getAllByProduct(Long idProduct);

}
