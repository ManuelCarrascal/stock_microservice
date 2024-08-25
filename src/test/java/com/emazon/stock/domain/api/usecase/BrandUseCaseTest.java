package com.emazon.stock.domain.api.usecase;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Test;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.exception.EntityAlreadyExistsException;
import com.emazon.stock.domain.spi.brand.IBrandPersistencePort;
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
}
