package com.emazon.stock.domain.api.usecase;

import com.emazon.stock.domain.api.ICategoryServicePort;
import com.emazon.stock.domain.exception.EntityAlreadyExistsException;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.Pagination;
import com.emazon.stock.domain.spi.category.ICategoryPersistencePort;
import com.emazon.stock.domain.util.EntityConstants;
import com.emazon.stock.domain.util.PaginationUtil;

import java.util.List;


public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        if (categoryPersistencePort.categoryExistsByName(category.getCategoryName())) {
            throw new EntityAlreadyExistsException(EntityConstants.CATEGORY_ENTITY_NAME.getName());
        }
        categoryPersistencePort.saveCategory(category);
    }

    @Override
    public Pagination<Category> getAllCategoriesPaginated(PaginationUtil paginationUtil) {
        return categoryPersistencePort.getAllCategoriesPaginated(paginationUtil);
    }

    @Override
    public List<Category> getAllByProduct(Long idProduct) {
        return categoryPersistencePort.getAllByProduct(idProduct);
    }


}
