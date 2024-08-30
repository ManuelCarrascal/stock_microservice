package com.emazon.stock.ports.persistence.mysql.adapter;

import com.emazon.stock.domain.model.Pagination;
import com.emazon.stock.domain.util.PaginationUtil;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Test;
import com.emazon.stock.domain.model.Product;
import com.emazon.stock.ports.persistence.mysql.repository.IProductRepository;
import com.emazon.stock.ports.persistence.mysql.mapper.IProductEntityMapper;
import com.emazon.stock.ports.persistence.mysql.entity.ProductEntity;
import org.mockito.MockedStatic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeast;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
class ProductAdapterTest {

    private final IProductEntityMapper productEntityMapperMock = mock(IProductEntityMapper.class, "productEntityMapper");

    private final IProductRepository productRepositoryMock = mock(IProductRepository.class, "productRepository");

    private final PageRequest pageRequestMock = mock(PageRequest.class);

    @Test()
    void saveProductTest() {
        ProductEntity productEntityMock = mock(ProductEntity.class);
        Product productMock = mock(Product.class);
        doReturn(productEntityMock).when(productEntityMapperMock).toEntity(productMock);
        doReturn(productEntityMock).when(productRepositoryMock).save(productEntityMock);
        ProductAdapter target = new ProductAdapter(productRepositoryMock, productEntityMapperMock);

        target.saveProduct(productMock);

        assertAll("result", () -> {
            verify(productEntityMapperMock).toEntity(productMock);
            verify(productRepositoryMock).save(productEntityMock);
        });
    }

    @Test()
    void getAllProductsPaginatedWhenProductPageIsNullThrowsAssertionError() {

        try (MockedStatic<PageRequest> pageRequest = mockStatic(PageRequest.class, CALLS_REAL_METHODS)) {
            doReturn(null).when(productRepositoryMock).findAllOrderByBrandNameAsc(pageRequestMock);
            pageRequest.when(() -> PageRequest.of(0, 0)).thenReturn(pageRequestMock);
            ProductAdapter target = new ProductAdapter(productRepositoryMock, productEntityMapperMock);
            PaginationUtil paginationUtil = new PaginationUtil();
            paginationUtil.setPageNumber(0);
            paginationUtil.setPageSize(0);
            paginationUtil.setSortBy("brandName");
            paginationUtil.setAscending(true);
            final AssertionError result = assertThrows(AssertionError.class, () -> target.getAllProductsPaginated(paginationUtil));
            assertAll("result", () -> {
                assertThat(result, is(notNullValue()));
                verify(productRepositoryMock, atLeast(1)).findAllOrderByBrandNameAsc(pageRequestMock);
                pageRequest.verify(() -> PageRequest.of(0, 0), atLeast(1));
            });
        }
    }

    @Test()
    void getAllProductsPaginatedWhenPaginationUtilNotIsAscendingAndProductPageIsNullThrowsAssertionError() {

        try (MockedStatic<PageRequest> pageRequest = mockStatic(PageRequest.class, CALLS_REAL_METHODS)) {
            doReturn(null).when(productRepositoryMock).findAllOrderByBrandNameDesc(pageRequestMock);
            pageRequest.when(() -> PageRequest.of(0, 0)).thenReturn(pageRequestMock);
            ProductAdapter target = new ProductAdapter(productRepositoryMock, productEntityMapperMock);
            PaginationUtil paginationUtil = new PaginationUtil();
            paginationUtil.setPageNumber(0);
            paginationUtil.setPageSize(0);
            paginationUtil.setSortBy("brandName");
            paginationUtil.setAscending(false);
            //Act Statement(s)
            final AssertionError result = assertThrows(AssertionError.class, () -> target.getAllProductsPaginated(paginationUtil));
            //Assert statement(s)
            assertAll("result", () -> {
                assertThat(result, is(notNullValue()));
                verify(productRepositoryMock, atLeast(1)).findAllOrderByBrandNameDesc(pageRequestMock);
                pageRequest.verify(() -> PageRequest.of(0, 0), atLeast(1));
            });
        }
    }

    @Test()
    void getAllProductsPaginatedWhenPaginationUtilIsAscendingAndProductPageIsNullThrowsAssertionError() {

        try (MockedStatic<PageRequest> pageRequest = mockStatic(PageRequest.class, CALLS_REAL_METHODS)) {
            doReturn(null).when(productRepositoryMock).findAllOrderByNumberOfCategoriesAsc(pageRequestMock);
            pageRequest.when(() -> PageRequest.of(0, 0)).thenReturn(pageRequestMock);
            ProductAdapter target = new ProductAdapter(productRepositoryMock, productEntityMapperMock);
            PaginationUtil paginationUtil = new PaginationUtil();
            paginationUtil.setPageNumber(0);
            paginationUtil.setPageSize(0);
            paginationUtil.setSortBy("numberOfCategories");
            paginationUtil.setAscending(true);
            final AssertionError result = assertThrows(AssertionError.class, () -> target.getAllProductsPaginated(paginationUtil));
            assertAll("result", () -> {
                assertThat(result, is(notNullValue()));
                verify(productRepositoryMock, atLeast(1)).findAllOrderByNumberOfCategoriesAsc(pageRequestMock);
                pageRequest.verify(() -> PageRequest.of(0, 0), atLeast(1));
            });
        }
    }

    @Test()
    void getAllProductsPaginatedWhenSortByNUMBER_OF_CATEGORIESGetFieldNameEqualsPaginationUtilGetSortByAndPaThrowsAssertionError() {

        try (MockedStatic<PageRequest> pageRequest = mockStatic(PageRequest.class, CALLS_REAL_METHODS)) {
            doReturn(null).when(productRepositoryMock).findAllOrderByNumberOfCategoriesDesc(pageRequestMock);
            pageRequest.when(() -> PageRequest.of(0, 0)).thenReturn(pageRequestMock);
            ProductAdapter target = new ProductAdapter(productRepositoryMock, productEntityMapperMock);
            PaginationUtil paginationUtil = new PaginationUtil();
            paginationUtil.setPageNumber(0);
            paginationUtil.setPageSize(0);
            paginationUtil.setSortBy("numberOfCategories");
            paginationUtil.setAscending(false);
            final AssertionError result = assertThrows(AssertionError.class, () -> target.getAllProductsPaginated(paginationUtil));
            assertAll("result", () -> {
                assertThat(result, is(notNullValue()));
                verify(productRepositoryMock, atLeast(1)).findAllOrderByNumberOfCategoriesDesc(pageRequestMock);
                pageRequest.verify(() -> PageRequest.of(0, 0), atLeast(1));
            });
        }
    }
    @Test
    void getAllProductsPaginatedWhenSortByPRODUCT_NAME() {
        Page<ProductEntity> pageMock = mock(Page.class);
        try (MockedStatic<PageRequest> pageRequest = mockStatic(PageRequest.class, CALLS_REAL_METHODS)) {
            List<ProductEntity> productEntityList = new ArrayList<>();
            List<Product> productList = new ArrayList<>();
            doReturn(pageMock).when(productRepositoryMock).findAllOrderByProductNameAsc(pageRequestMock);
            doReturn(productEntityList).when(pageMock).getContent();
            doReturn(0).when(pageMock).getTotalPages();
            doReturn(1L).when(pageMock).getTotalElements();
            doReturn(productList).when(productEntityMapperMock).toProductList(productEntityList);
            pageRequest.when(() -> PageRequest.of(0, 0)).thenReturn(pageRequestMock);
            ProductAdapter target = new ProductAdapter(productRepositoryMock, productEntityMapperMock);
            PaginationUtil paginationUtil = new PaginationUtil();
            paginationUtil.setPageNumber(0);
            paginationUtil.setPageSize(0);
            paginationUtil.setSortBy("productName");
            paginationUtil.setAscending(true);

            Pagination<Product> result = target.getAllProductsPaginated(paginationUtil);

            assertAll("result",
                    () -> assertThat(result, is(notNullValue())),
                    () -> assertThat(result.getContent(), is(productList)),
                    () -> assertThat(result.getTotalPages(), is(0)),
                    () -> assertThat(result.getTotalElements(), is(1L)),
                    () -> verify(productRepositoryMock, atLeast(1)).findAllOrderByProductNameAsc(pageRequestMock),
                    () -> verify(pageMock, atLeast(1)).getContent(),
                    () -> verify(pageMock, atLeast(1)).getTotalPages(),
                    () -> verify(pageMock, atLeast(1)).getTotalElements(),
                    () -> verify(productEntityMapperMock, atLeast(1)).toProductList(productEntityList),
                    () -> pageRequest.verify(() -> PageRequest.of(0, 0), atLeast(1))
            );
        }
    }
    @Test
    void getAllProductsPaginatedWhenSortByPRODUCT_NAMEGetFieldNameEqualsPaginationUtilGetSortByAndPaginationUtilIsAscendingAndPr() {

        Page pageMock = mock(Page.class);
        try (MockedStatic<PageRequest> pageRequest = mockStatic(PageRequest.class, CALLS_REAL_METHODS)) {
            List<ProductEntity> productEntityList = new ArrayList<>();
            List<Product> productList = new ArrayList<>();
            doReturn(pageMock).when(productRepositoryMock).findAllOrderByProductNameAsc(pageRequestMock);
            doReturn(productEntityList).when(pageMock).getContent();
            doReturn(0).when(pageMock).getTotalPages();
            doReturn(1L).when(pageMock).getTotalElements();
            doReturn(productList).when(productEntityMapperMock).toProductList(productEntityList);
            pageRequest.when(() -> PageRequest.of(0, 0)).thenReturn(pageRequestMock);
            ProductAdapter target = new ProductAdapter(productRepositoryMock, productEntityMapperMock);
            PaginationUtil paginationUtil = new PaginationUtil();
            paginationUtil.setPageNumber(0);
            paginationUtil.setPageSize(0);
            paginationUtil.setSortBy("productName");
            paginationUtil.setAscending(true);

            Pagination<Product> result = target.getAllProductsPaginated(paginationUtil);

            assertAll("result",
                    () -> assertThat(result, is(notNullValue())),
                    () -> assertThat(result.getContent(), is(productList)),
                    () -> assertThat(result.getTotalPages(), is(0)),
                    () -> assertThat(result.getTotalElements(), is(1L)),
                    () -> verify(productRepositoryMock, atLeast(1)).findAllOrderByProductNameAsc(pageRequestMock),
                    () -> verify(pageMock, atLeast(1)).getContent(),
                    () -> verify(pageMock, atLeast(1)).getTotalPages(),
                    () -> verify(pageMock, atLeast(1)).getTotalElements(),
                    () -> verify(productEntityMapperMock, atLeast(1)).toProductList(productEntityList),
                    () -> pageRequest.verify(() -> PageRequest.of(0, 0), atLeast(1))
            );
        }
    }


    @Test()
    void getAllProductsPaginatedWhenPaginationUtilNotIsAscendingAndProductPageIsNull2ThrowsAssertionError() {

        try (MockedStatic<PageRequest> pageRequest = mockStatic(PageRequest.class, CALLS_REAL_METHODS)) {
            doReturn(null).when(productRepositoryMock).findAllOrderByProductNameDesc(pageRequestMock);
            pageRequest.when(() -> PageRequest.of(0, 0)).thenReturn(pageRequestMock);
            ProductAdapter target = new ProductAdapter(productRepositoryMock, productEntityMapperMock);
            PaginationUtil paginationUtil = new PaginationUtil();
            paginationUtil.setPageNumber(0);
            paginationUtil.setPageSize(0);
            paginationUtil.setSortBy("productName");
            paginationUtil.setAscending(false);
            final AssertionError result = assertThrows(AssertionError.class, () -> target.getAllProductsPaginated(paginationUtil));
            assertAll("result", () -> {
                assertThat(result, is(notNullValue()));
                verify(productRepositoryMock, atLeast(1)).findAllOrderByProductNameDesc(pageRequestMock);
                pageRequest.verify(() -> PageRequest.of(0, 0), atLeast(1));
            });
        }
    }
}
