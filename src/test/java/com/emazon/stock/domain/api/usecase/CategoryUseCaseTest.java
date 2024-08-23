package com.emazon.stock.domain.api.usecase;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Test;
import com.emazon.stock.domain.spi.category.ICategoryPersistencePort;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.exception.EntityAlreadyExistsException;
import static org.mockito.Mockito.doNothing;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doReturn;
import static org.hamcrest.Matchers.is;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
class CategoryUseCaseTest {

    private final ICategoryPersistencePort categoryPersistencePortMock = mock(ICategoryPersistencePort.class, "categoryPersistencePort");

    @Test
    void saveCategoryWhenCategoryPersistencePortCategoryExistsByNameCategoryGetCategoryNameThrowsEntityAlreadyExistsException() {
        // Arrange Statement
        doReturn(true).when(categoryPersistencePortMock).categoryExistsByName("Electronics");
        CategoryUseCase target = new CategoryUseCase(categoryPersistencePortMock);
        Category category = new Category();
        category.setCategoryName("Electronics");

        // Act Statement
        EntityAlreadyExistsException result = assertThrows(EntityAlreadyExistsException.class, () -> target.saveCategory(category));

        // Assert statement(s)
        assertAll("result",
                () -> assertThat(result, is(notNullValue())),
                () -> verify(categoryPersistencePortMock).categoryExistsByName("Electronics")
        );
    }


    @Test()
    void saveCategoryWhenCategoryPersistencePortNotCategoryExistsByNameCategoryGetCategoryName() {

        //(categoryPersistencePort.categoryExistsByName(category.getCategoryName())) : false

         //Arrange Statement
        doReturn(false).when(categoryPersistencePortMock).categoryExistsByName("Electronics");
        Category category = new Category();
        category.setCategoryName("Electronics");
        doNothing().when(categoryPersistencePortMock).saveCategory(category);
        CategoryUseCase target = new CategoryUseCase(categoryPersistencePortMock);
        
        //Act Statement
        target.saveCategory(category);
        
        //Assert statement
        assertAll("result", () -> {
            verify(categoryPersistencePortMock).categoryExistsByName("Electronics");
            verify(categoryPersistencePortMock).saveCategory(category);
        });
    }
}
