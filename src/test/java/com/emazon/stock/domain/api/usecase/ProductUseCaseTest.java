package com.emazon.stock.domain.api.usecase;

import com.emazon.stock.domain.model.Pagination;
import com.emazon.stock.domain.util.PaginationUtil;
import com.emazon.stock.domain.util.PaginationValidator;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Test;
import com.emazon.stock.domain.model.Product;
import com.emazon.stock.domain.spi.product.IProductPersistencePort;
import org.mockito.MockedStatic;
import org.mockito.stubbing.Answer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
class ProductUseCaseTest {

    private final IProductPersistencePort productPersistencePortMock = mock(IProductPersistencePort.class, "productPersistencePort");

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
}
