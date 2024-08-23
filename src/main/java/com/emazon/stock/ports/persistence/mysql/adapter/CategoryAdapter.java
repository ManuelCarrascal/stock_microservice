package com.emazon.stock.ports.persistence.mysql.adapter;

import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.spi.category.ICategoryPersistencePort;
import com.emazon.stock.ports.persistence.mysql.mapper.CategoryEntityMapper;
import com.emazon.stock.ports.persistence.mysql.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public boolean categoryExistsByName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName).isPresent();
    }
}
