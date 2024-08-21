package com.emazon.stock.ports.persistence.mysql.adapter;

import com.emazon.stock.domain.exception.EntityAlreadyExistsException;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.Pagination;
import com.emazon.stock.domain.spi.category.ICategoryPersistencePort;
import com.emazon.stock.ports.persistence.mysql.entity.CategoryEntity;
import com.emazon.stock.ports.persistence.mysql.mapper.CategoryEntityMapper;
import com.emazon.stock.ports.persistence.mysql.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@RequiredArgsConstructor
public class CategoryAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Override
    public void saveCategory(Category category){
        if(categoryRepository.findByCategoryName(category.getCategoryName()).isPresent()) {
            throw new EntityAlreadyExistsException("Category");
        }
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public List<Category> getAllCategories() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        return categoryEntityMapper.toCategoryList(categoryEntityList);
    }

    @Override
    public Pagination<Category> getAllCategoriesPaginated(int page, int size, String sortDirection) {
        PageRequest pageRequest = PageRequest.of(page, size, sortDirection.equals("asc") ? org.springframework.data.domain.Sort.Direction.ASC : org.springframework.data.domain.Sort.Direction.DESC);
        Page<CategoryEntity> categoryPage = categoryRepository.findAll(pageRequest);

        List<Category> categories = categoryEntityMapper.toCategoryList(categoryPage.getContent());

        return new Pagination<>(
                categories,
                categoryPage.getNumber(),
                categoryPage.getSize(),
                categoryPage.getTotalElements(),
                categoryPage.getTotalPages(),
                categoryPage.getSort().toString()

        );
    }


}
