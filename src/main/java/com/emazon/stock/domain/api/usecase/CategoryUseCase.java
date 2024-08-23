package com.emazon.stock.domain.api.usecase;

import com.emazon.stock.domain.api.ICategoryServicePort;
import com.emazon.stock.domain.exception.EntityAlreadyExistsException;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.spi.category.ICategoryPersistencePort;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort){
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        if (categoryPersistencePort.categoryExistsByName(category.getCategoryName())) {
            throw new EntityAlreadyExistsException("Category");
        }
        categoryPersistencePort.saveCategory(category);
    }
}
