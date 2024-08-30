package com.emazon.stock.ports.persistence.mysql.adapter;

import com.emazon.stock.domain.model.Pagination;
import com.emazon.stock.domain.util.PaginationUtil;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Test;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.ports.persistence.mysql.entity.CategoryEntity;
import com.emazon.stock.ports.persistence.mysql.repository.ICategoryRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import com.emazon.stock.ports.persistence.mysql.mapper.ICategoryEntityMapper;
import org.mockito.MockedStatic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
class CategoryAdapterTest {

    private final ICategoryEntityMapper categoryEntityMapperMock = mock(ICategoryEntityMapper.class, "categoryEntityMapper");

    private final ICategoryRepository categoryRepositoryMock = mock(ICategoryRepository.class, "categoryRepository");

    private final CategoryEntity categoryEntityMock = mock(CategoryEntity.class);

    private final Page<CategoryEntity> pageMock = mock(Page.class);

    private final PageRequest pageRequestMock = mock(PageRequest.class);

    private final Sort sortMock = mock(Sort.class);

    @Test
    void saveCategoryTest() {
        Category categoryMock = mock(Category.class);
        CategoryEntity categoryEntityMockTest = mock(CategoryEntity.class);

        doReturn(categoryEntityMockTest).when(categoryEntityMapperMock).toEntity(categoryMock);
        doReturn(categoryEntityMockTest).when(categoryRepositoryMock).save(categoryEntityMockTest);

        CategoryAdapter target = new CategoryAdapter(categoryRepositoryMock, categoryEntityMapperMock);

        target.saveCategory(categoryMock);

        assertAll("result",
                () -> verify(categoryEntityMapperMock).toEntity(categoryMock),
                () -> verify(categoryRepositoryMock).save(categoryEntityMockTest)
        );
    }

    @Test()
    void categoryExistsByNameWhenCategoryRepositoryFindByCategoryNameCategoryNameIsPresent() {

        doReturn(Optional.of(categoryEntityMock)).when(categoryRepositoryMock).findByCategoryName("Electronics");
        CategoryAdapter target = new CategoryAdapter(categoryRepositoryMock, categoryEntityMapperMock);

        boolean result = target.categoryExistsByName("Electronics");

        assertAll("result", () -> {
            assertThat(result, equalTo(Boolean.TRUE));
            verify(categoryRepositoryMock).findByCategoryName("Electronics");
        });
    }

    @Test()
    void categoryExistsByNameWhenCategoryRepositoryFindByCategoryNameCategoryNameNotIsPresent() {

        doReturn(Optional.empty()).when(categoryRepositoryMock).findByCategoryName("Electronics");
        CategoryAdapter target = new CategoryAdapter(categoryRepositoryMock, categoryEntityMapperMock);

        boolean result = target.categoryExistsByName("Electronics");

        assertAll("result", () -> {
            assertThat(result, equalTo(Boolean.FALSE));
            verify(categoryRepositoryMock).findByCategoryName("Electronics");
        });
    }

    @Test
    void getAllCategoriesPaginatedWhenPaginationUtilIsAscending() {
        try (MockedStatic<Sort> sortMockStatic = mockStatic(Sort.class);
             MockedStatic<PageRequest> pageRequestMockStatic = mockStatic(PageRequest.class, CALLS_REAL_METHODS)) {

            List<CategoryEntity> categoryEntityList = Collections.emptyList();
            List<Category> categoryList = Collections.emptyList();

            when(categoryRepositoryMock.findAll(pageRequestMock)).thenReturn(pageMock);
            when(pageMock.getContent()).thenReturn(categoryEntityList);
            when(pageMock.getTotalPages()).thenReturn(0);
            when(pageMock.getTotalElements()).thenReturn(1L);
            when(categoryEntityMapperMock.toCategoryList(categoryEntityList)).thenReturn(categoryList);

            sortMockStatic.when(() -> Sort.by(Sort.Direction.ASC, "categoryName")).thenReturn(sortMock);
            pageRequestMockStatic.when(() -> PageRequest.of(0, 0, sortMock)).thenReturn(pageRequestMock);

            PaginationUtil paginationUtil = new PaginationUtil();
            paginationUtil.setPageNumber(0);
            paginationUtil.setPageSize(0);
            paginationUtil.setAscending(true);
            paginationUtil.setSortBy("categoryName");

            CategoryAdapter categoryAdapter = new CategoryAdapter(categoryRepositoryMock, categoryEntityMapperMock);

            Pagination<Category> result = categoryAdapter.getAllCategoriesPaginated(paginationUtil);

            assertThat(result.getContent(), is(categoryList));
            assertThat(result.getTotalPages(), is(0));
            assertThat(result.getTotalElements(), is(1L));

            verify(categoryRepositoryMock).findAll(pageRequestMock);
            verify(pageMock).getContent();
            verify(pageMock).getTotalPages();
            verify(pageMock).getTotalElements();
            verify(categoryEntityMapperMock).toCategoryList(categoryEntityList);
            sortMockStatic.verify(() -> Sort.by(Sort.Direction.ASC, "categoryName"));
            pageRequestMockStatic.verify(() -> PageRequest.of(0, 0, sortMock));
        }
    }

    @Test
    void getAllCategoriesPaginatedWhenPaginationUtilNotIsAscending() {
        try (MockedStatic<Sort> sortMockStatic = mockStatic(Sort.class);
             MockedStatic<PageRequest> pageRequestMockStatic = mockStatic(PageRequest.class, CALLS_REAL_METHODS)) {

            List<CategoryEntity> categoryEntityList = Collections.emptyList();
            List<Category> categoryList = Collections.emptyList();

            when(categoryRepositoryMock.findAll(pageRequestMock)).thenReturn(pageMock);
            when(pageMock.getContent()).thenReturn(categoryEntityList);
            when(pageMock.getTotalPages()).thenReturn(0);
            when(pageMock.getTotalElements()).thenReturn(1L);
            when(categoryEntityMapperMock.toCategoryList(categoryEntityList)).thenReturn(categoryList);

            sortMockStatic.when(() -> Sort.by(Sort.Direction.DESC, "nameCategory")).thenReturn(sortMock);
            pageRequestMockStatic.when(() -> PageRequest.of(0, 0, sortMock)).thenReturn(pageRequestMock);

            PaginationUtil paginationUtil = new PaginationUtil();
            paginationUtil.setPageNumber(0);
            paginationUtil.setPageSize(0);
            paginationUtil.setAscending(false);
            paginationUtil.setSortBy("nameCategory");
            CategoryAdapter categoryAdapter = new CategoryAdapter(categoryRepositoryMock, categoryEntityMapperMock);
            Pagination<Category> result = categoryAdapter.getAllCategoriesPaginated(paginationUtil);

            assertThat(result.getContent(), is(categoryList));
            assertThat(result.getTotalPages(), is(0));
            assertThat(result.getTotalElements(), is(1L));

            verify(categoryRepositoryMock).findAll(pageRequestMock);
            verify(pageMock).getContent();
            verify(pageMock).getTotalPages();
            verify(pageMock).getTotalElements();
            verify(categoryEntityMapperMock).toCategoryList(categoryEntityList);
            sortMockStatic.verify(() -> Sort.by(Sort.Direction.DESC, "nameCategory"));
            pageRequestMockStatic.verify(() -> PageRequest.of(0, 0, sortMock));
        }
    }

    @Test()
    void getAllByProductTest() {
        List<CategoryEntity> categoryEntityList = new ArrayList<>();
        doReturn(categoryEntityList).when(categoryRepositoryMock).findCategoriesByProductId(0L);
        List<Category> categoryList = new ArrayList<>();
        doReturn(categoryList).when(categoryEntityMapperMock).toCategoryList(categoryEntityList);
        CategoryAdapter target = new CategoryAdapter(categoryRepositoryMock, categoryEntityMapperMock);

        List<Category> result = target.getAllByProduct(0L);

        assertAll("result", () -> {
            assertThat(result, equalTo(categoryList));
            verify(categoryRepositoryMock).findCategoriesByProductId(0L);
            verify(categoryEntityMapperMock).toCategoryList(categoryEntityList);
        });
    }
}

