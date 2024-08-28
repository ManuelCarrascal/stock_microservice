package com.emazon.stock.domain.api.usecase;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Test;
import com.emazon.stock.domain.model.Product;
import com.emazon.stock.domain.spi.product.IProductPersistencePort;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.mock;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
class ProductUseCaseTest {

    private final IProductPersistencePort productPersistencePortMock = mock(IProductPersistencePort.class, "productPersistencePort");

    @Test()
    void saveProductTest() {
        //Arrange Statement
        Product productMock = mock(Product.class);
        doNothing().when(productPersistencePortMock).saveProduct(productMock);
        ProductUseCase target = new ProductUseCase(productPersistencePortMock);
        
        //Act Statement
        target.saveProduct(productMock);
        
        //Assert statement
        assertAll("result", () -> verify(productPersistencePortMock).saveProduct(productMock));
    }
}
