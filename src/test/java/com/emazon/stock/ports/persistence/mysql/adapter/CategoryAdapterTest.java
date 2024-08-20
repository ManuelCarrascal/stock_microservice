package com.emazon.stock.ports.persistence.mysql.adapter;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.emazon.stock.domain.exception.EntityAlreadyExistsException;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.ports.persistence.mysql.entity.CategoryEntity;
import com.emazon.stock.ports.persistence.mysql.mapper.CategoryEntityMapper;
import com.emazon.stock.ports.persistence.mysql.repository.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import java.util.logging.Logger;

 class CategoryAdapterTest {

    private static final Logger logger = Logger.getLogger(CategoryAdapterTest.class.getName());

    @Mock
    private ICategoryRepository categoryRepository;

    @Mock
    private CategoryEntityMapper categoryEntityMapper;

    @InjectMocks
    private CategoryAdapter categoryAdapter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testSaveCategory_Success() {
        Category category = new Category();
        category.setCategoryName("Electronics");

        when(categoryRepository.findByCategoryName("Electronics")).thenReturn(Optional.empty());
        when(categoryEntityMapper.toEntity(category)).thenReturn(new CategoryEntity());

        categoryAdapter.saveCategory(category);
        logger.info("Category saved successfully");

        verify(categoryRepository, times(1)).save(any(CategoryEntity.class));
    }

     @Test
     void testSaveCategory_EntityAlreadyExists() {
         Category category = new Category();
         category.setCategoryName("Electronics");

         when(categoryRepository.findByCategoryName("Electronics")).thenReturn(Optional.of(new CategoryEntity()));

         EntityAlreadyExistsException exception = assertThrows(EntityAlreadyExistsException.class, () -> {
             categoryAdapter.saveCategory(category);
         });
         logger.info(exception.getMessage());
     }
}