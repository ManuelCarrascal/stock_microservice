package com.emazon.stock.ports.persistence.mysql.adapter;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Test;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.ports.persistence.mysql.entity.CategoryEntity;
import com.emazon.stock.ports.persistence.mysql.repository.ICategoryRepository;
import java.util.Optional;
import com.emazon.stock.ports.persistence.mysql.mapper.CategoryEntityMapper;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doReturn;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
class CategoryAdapterTest {

    private final CategoryEntityMapper categoryEntityMapperMock = mock(CategoryEntityMapper.class, "categoryEntityMapper");

    private final ICategoryRepository categoryRepositoryMock = mock(ICategoryRepository.class, "categoryRepository");

    private final CategoryEntity categoryEntityMock = mock(CategoryEntity.class);

    @Test
    void saveCategoryTest() {
        // Arrange
        Category categoryMock = mock(Category.class);
        CategoryEntity categoryEntityMockTest = mock(CategoryEntity.class);

        doReturn(categoryEntityMockTest).when(categoryEntityMapperMock).toEntity(categoryMock);
        doReturn(categoryEntityMockTest).when(categoryRepositoryMock).save(categoryEntityMockTest);

        CategoryAdapter target = new CategoryAdapter(categoryRepositoryMock, categoryEntityMapperMock);

        // Act
        target.saveCategory(categoryMock);

        // Assert
        assertAll("result",
                () -> verify(categoryEntityMapperMock).toEntity(categoryMock),
                () -> verify(categoryRepositoryMock).save(categoryEntityMockTest)
        );
    }


    @Test()
    void categoryExistsByNameWhenCategoryRepositoryFindByCategoryNameCategoryNameIsPresent() {

         // (categoryRepository.findByCategoryName(categoryName).isPresent()) : true

         //Arrange Statement
        doReturn(Optional.of(categoryEntityMock)).when(categoryRepositoryMock).findByCategoryName("Electronics");
        CategoryAdapter target = new CategoryAdapter(categoryRepositoryMock, categoryEntityMapperMock);
        
        //Act Statement
        boolean result = target.categoryExistsByName("Electronics");
        
        //Assert statement
        assertAll("result", () -> {
            assertThat(result, equalTo(Boolean.TRUE));
            verify(categoryRepositoryMock).findByCategoryName("Electronics");
        });
    }

    @Test()
    void categoryExistsByNameWhenCategoryRepositoryFindByCategoryNameCategoryNameNotIsPresent() {

         //(categoryRepository.findByCategoryName(categoryName).isPresent()) : false

         //Arrange Statement
        doReturn(Optional.empty()).when(categoryRepositoryMock).findByCategoryName("Electronics");
        CategoryAdapter target = new CategoryAdapter(categoryRepositoryMock, categoryEntityMapperMock);
        
        //Act Statement
        boolean result = target.categoryExistsByName("Electronics");
        
        //Assert statement
        assertAll("result", () -> {
            assertThat(result, equalTo(Boolean.FALSE));
            verify(categoryRepositoryMock).findByCategoryName("Electronics");
        });
    }
}
