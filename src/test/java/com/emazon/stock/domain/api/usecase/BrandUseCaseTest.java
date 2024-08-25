package com.emazon.stock.domain.api.usecase;

import com.emazon.stock.domain.model.Pagination;
import com.emazon.stock.domain.util.PaginationUtil;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Test;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.exception.EntityAlreadyExistsException;
import com.emazon.stock.domain.spi.brand.IBrandPersistencePort;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doReturn;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
class BrandUseCaseTest {

    private final IBrandPersistencePort brandPersistencePortMock = mock(IBrandPersistencePort.class, "brandPersistencePort");

    @Test()
    void saveBrandWhenBrandPersistencePortBrandExistsByNameBrandGetBrandNameThrowsEntityAlreadyExistsException() {

         //(brandPersistencePort.brandExistsByName(brand.getBrandName())) : true

         //Arrange Statement
        doReturn(true).when(brandPersistencePortMock).brandExistsByName("Samsung");
        BrandUseCase target = new BrandUseCase(brandPersistencePortMock);
        Brand brand = new Brand();
        brand.setBrandName("Samsung");
        //Act Statement
        final EntityAlreadyExistsException result = assertThrows(EntityAlreadyExistsException.class, () -> target.saveBrand(brand));
        
        //Assert statement
        assertAll("result", () -> {
            assertThat(result, is(notNullValue()));
            verify(brandPersistencePortMock).brandExistsByName("Samsung");
        });
    }

    @Test()
    void saveBrandWhenBrandPersistencePortNotBrandExistsByNameBrandGetBrandName() {
         //(brandPersistencePort.brandExistsByName(brand.getBrandName())) : false

         //Arrange Statement
        doReturn(false).when(brandPersistencePortMock).brandExistsByName("Samsung");
        Brand brand = new Brand();
        brand.setBrandName("Samsung");
        doNothing().when(brandPersistencePortMock).saveBrand(brand);
        BrandUseCase target = new BrandUseCase(brandPersistencePortMock);
        
        //Act Statement
        target.saveBrand(brand);
        
        //Assert statement
        assertAll("result", () -> {
            verify(brandPersistencePortMock).brandExistsByName("Samsung");
            verify(brandPersistencePortMock).saveBrand(brand);
        });
    }
    @Test
    void getAllBrandsPaginatedTest() {
        // Arrange Statement
        Pagination<Brand> paginationMock = mock(Pagination.class);
        PaginationUtil paginationUtilMock = mock(PaginationUtil.class);
        doReturn(paginationMock).when(brandPersistencePortMock).getAllBrandsPaginated(paginationUtilMock);
        BrandUseCase target = new BrandUseCase(brandPersistencePortMock);

        // Act Statement
        Pagination<Brand> result = target.getAllBrandsPaginated(paginationUtilMock);

        // Assert Statement
        assertAll("result", () -> {
            assertThat(result, equalTo(paginationMock));
            verify(brandPersistencePortMock).getAllBrandsPaginated(paginationUtilMock);
        });
    }
}
