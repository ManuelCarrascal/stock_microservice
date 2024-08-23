package com.emazon.stock.domain.spi.category;

import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.Pagination;
import com.emazon.stock.domain.util.PaginationUtil;

public interface ICategoryPersistencePort {
    void saveCategory(Category category);
    boolean categoryExistsByName(String categoryName);
    Pagination<Category> getAllCategoriesPaginated(PaginationUtil paginationUtil);

}
