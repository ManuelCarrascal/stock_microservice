package com.emazon.stock.domain.api.usecase;

import com.emazon.stock.domain.model.Pagination;
import com.emazon.stock.domain.util.PaginationUtil;
import com.emazon.stock.domain.util.PaginationValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Test;
import com.emazon.stock.domain.model.Product;
import com.emazon.stock.domain.spi.product.IProductPersistencePort;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
class ProductUseCaseTest {
    @Mock
    private final IProductPersistencePort productPersistencePortMock = mock(IProductPersistencePort.class, "productPersistencePort");

    private ProductUseCase productUseCase;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productUseCase = new ProductUseCase(productPersistencePortMock);
    }

    @Test()
    void saveProductTest() {
        Product productMock = mock(Product.class);
        doNothing().when(productPersistencePortMock).saveProduct(productMock);
        ProductUseCase target = new ProductUseCase(productPersistencePortMock);

        target.saveProduct(productMock);

        assertAll("result", () -> verify(productPersistencePortMock).saveProduct(productMock));
    }

    @Test()
    void getAllProductsPaginatedTest() {
        Pagination<Product> paginationMock = mock(Pagination.class);
        PaginationUtil paginationUtilMock = mock(PaginationUtil.class);
        try (MockedStatic<PaginationValidator> paginationValidator = mockStatic(PaginationValidator.class)) {
            doReturn(paginationMock).when(productPersistencePortMock).getAllProductsPaginated(paginationUtilMock);
            paginationValidator.when(() -> PaginationValidator.validate(paginationUtilMock)).thenAnswer((Answer<Void>) invocation -> null);
            ProductUseCase target = new ProductUseCase(productPersistencePortMock);
            Pagination<Product> result = target.getAllProductsPaginated(paginationUtilMock);
            assertAll("result", () -> {
                assertThat(result, equalTo(paginationMock));
                verify(productPersistencePortMock).getAllProductsPaginated(paginationUtilMock);
                paginationValidator.verify(() -> PaginationValidator.validate(paginationUtilMock), atLeast(1));
            });
        }
    }

    @Test
    void updateProductTest() {
        Product productMock = mock(Product.class);
        doNothing().when(productPersistencePortMock).updateProduct(productMock);

        productUseCase.updateProduct(productMock);

        verify(productPersistencePortMock, times(1)).updateProduct(productMock);
    }

    @Test
    void getProductByIdTest() {
        Long productId = 1L;

        productUseCase.getProductById(productId);

        verify(productPersistencePortMock, times(1)).getProductById(productId);
    }


    @Test
    void isStockSufficientTest() {
        Long productId = 1L;
        Integer quantity = 5;
        when(productPersistencePortMock.isStockSufficient(productId, quantity)).thenReturn(true);

        boolean result = productUseCase.isStockSufficient(productId, quantity);

        assertThat(result, equalTo(true));
        verify(productPersistencePortMock, times(1)).isStockSufficient(productId, quantity);
    }

    @Test
    void getAllProductsPaginatedByIdsTest() {
        Pagination<Product> paginationMock = mock(Pagination.class);
        PaginationUtil paginationUtilMock = mock(PaginationUtil.class);
        List<Long> productIds = Arrays.asList(1L, 2L);
        String categoryName = "Category1";
        String brandName = "Brand1";

        try (MockedStatic<PaginationValidator> paginationValidator = mockStatic(PaginationValidator.class)) {
            doReturn(paginationMock).when(productPersistencePortMock).getAllProductsPaginatedByIds(paginationUtilMock, productIds, categoryName, brandName);
            paginationValidator.when(() -> PaginationValidator.validate(paginationUtilMock)).thenAnswer(invocation -> null);

            Pagination<Product> result = productUseCase.getAllProductsPaginatedByIds(paginationUtilMock, productIds, categoryName, brandName);

            assertEquals(paginationMock, result);
            verify(productPersistencePortMock, times(1)).getAllProductsPaginatedByIds(paginationUtilMock, productIds, categoryName, brandName);
            paginationValidator.verify(() -> PaginationValidator.validate(paginationUtilMock), atLeast(1));
        }
    }

    @Test
    void getProductPriceByIdTest() {
        Long productId = 1L;
        double expectedPrice = 99.99;
        when(productPersistencePortMock.getProductPriceById(productId)).thenReturn(expectedPrice);

        double actualPrice = productUseCase.getProductPriceById(productId);

        assertEquals(expectedPrice, actualPrice);
        verify(productPersistencePortMock, times(1)).getProductPriceById(productId);
    }
}
