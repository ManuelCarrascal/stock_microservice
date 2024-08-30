package com.emazon.stock.domain.api.usecase;

import com.emazon.stock.domain.model.Pagination;
import com.emazon.stock.domain.util.PaginationUtil;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Test;
import com.emazon.stock.domain.spi.category.ICategoryPersistencePort;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.exception.EntityAlreadyExistsException;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
class CategoryUseCaseTest {

    private final ICategoryPersistencePort categoryPersistencePortMock = mock(ICategoryPersistencePort.class);

    @Test
    void saveCategoryWhenCategoryExistsThrowsException() {
        when(categoryPersistencePortMock.categoryExistsByName("categoryName1")).thenReturn(true);
        CategoryUseCase target = new CategoryUseCase(categoryPersistencePortMock);
        Category category = new Category();
        category.setCategoryName("categoryName1");

        EntityAlreadyExistsException result = assertThrows(EntityAlreadyExistsException.class, () -> target.saveCategory(category));

        assertThat(result, is(notNullValue()));
        verify(categoryPersistencePortMock).categoryExistsByName("categoryName1");
    }

    @Test
    void saveCategoryWhenCategoryDoesNotExist() {
        when(categoryPersistencePortMock.categoryExistsByName("categoryName1")).thenReturn(false);
        Category category = new Category();
        category.setCategoryName("categoryName1");
        doNothing().when(categoryPersistencePortMock).saveCategory(category);
        CategoryUseCase target = new CategoryUseCase(categoryPersistencePortMock);

        target.saveCategory(category);

        verify(categoryPersistencePortMock).categoryExistsByName("categoryName1");
        verify(categoryPersistencePortMock).saveCategory(category);
    }

    @Test
    void getAllCategoriesPaginated() {
        Pagination<Category> paginationMock = mock(Pagination.class);
        PaginationUtil paginationUtilMock = mock(PaginationUtil.class);
        when(categoryPersistencePortMock.getAllCategoriesPaginated(paginationUtilMock)).thenReturn(paginationMock);
        CategoryUseCase target = new CategoryUseCase(categoryPersistencePortMock);

        Pagination<Category> result = target.getAllCategoriesPaginated(paginationUtilMock);

        assertThat(result, equalTo(paginationMock));
        verify(categoryPersistencePortMock).getAllCategoriesPaginated(paginationUtilMock);
    }

    @Test
    void getAllByProduct() {
        List<Category> categoryList = new ArrayList<>();
        when(categoryPersistencePortMock.getAllByProduct(0L)).thenReturn(categoryList);
        CategoryUseCase target = new CategoryUseCase(categoryPersistencePortMock);

        List<Category> result = target.getAllByProduct(0L);

        assertThat(result, equalTo(categoryList));
        verify(categoryPersistencePortMock).getAllByProduct(0L);
    }
}

