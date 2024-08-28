package com.emazon.stock.ports.persistence.mysql.adapter;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Test;
import com.emazon.stock.domain.model.Product;
import com.emazon.stock.ports.persistence.mysql.repository.IProductRepository;
import com.emazon.stock.ports.persistence.mysql.mapper.IProductEntityMapper;
import com.emazon.stock.ports.persistence.mysql.entity.ProductEntity;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doReturn;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
class ProductAdapterTest {

    private final IProductEntityMapper productEntityMapperMock = mock(IProductEntityMapper.class, "productEntityMapper");

    private final IProductRepository productRepositoryMock = mock(IProductRepository.class, "productRepository");

    @Test()
    void saveProductTest() {
        // Arrange
        ProductEntity productEntityMock = mock(ProductEntity.class);
        Product productMock = mock(Product.class);
        doReturn(productEntityMock).when(productEntityMapperMock).toEntity(productMock);
        doReturn(productEntityMock).when(productRepositoryMock).save(productEntityMock);
        ProductAdapter target = new ProductAdapter(productRepositoryMock, productEntityMapperMock);

        // Act
        target.saveProduct(productMock);

        // Assert
        assertAll("result", () -> {
            verify(productEntityMapperMock).toEntity(productMock);
            verify(productRepositoryMock).save(productEntityMock);
        });
    }
}
