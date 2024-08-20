package com.emazon.stock.ports.persistence.mysql.adapter;

import com.emazon.stock.domain.exception.EntityAlreadyExistsException;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.spi.category.ICategoryPersistencePort;
import com.emazon.stock.ports.persistence.mysql.mapper.CategoryEntityMapper;
import com.emazon.stock.ports.persistence.mysql.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    public void saveCategory(Category category){
        if(categoryRepository.findByCategoryName(category.getCategoryName()).isPresent()) {
            throw new EntityAlreadyExistsException("Category");
        }
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }
}
