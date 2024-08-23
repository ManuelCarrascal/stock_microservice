package com.emazon.stock.domain.api;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.Pagination;
import com.emazon.stock.domain.util.PaginationUtil;

public interface ICategoryServicePort {
    void saveCategory(Category category);
    Pagination<Category> getAllCategoriesPaginated(PaginationUtil paginationUtil);
}
