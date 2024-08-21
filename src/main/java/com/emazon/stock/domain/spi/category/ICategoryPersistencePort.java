package com.emazon.stock.domain.spi.category;

import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.Pagination;

import java.util.List;


public interface ICategoryPersistencePort {
    void saveCategory(Category category);
    List<Category> getAllCategories();
    Pagination<Category> getAllCategoriesPaginated(int page, int size , String sortDirection);

}
