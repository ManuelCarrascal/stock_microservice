package com.emazon.stock.ports.persistence.mysql.adapter;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Test;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.ports.persistence.mysql.mapper.IBrandEntityMapper;
import com.emazon.stock.ports.persistence.mysql.entity.BrandEntity;
import java.util.Optional;
import com.emazon.stock.ports.persistence.mysql.repository.IBrandRepository;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doReturn;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
class BrandAdapterTest {

    private final IBrandEntityMapper brandEntityMapperMock = mock(IBrandEntityMapper.class, "brandEntityMapper");

    private final IBrandRepository brandRepositoryMock = mock(IBrandRepository.class, "brandRepository");

    private final BrandEntity brandEntityMock = mock(BrandEntity.class);

    @Test
    void saveBrandTest() {
        // Arrange
        Brand brandMock = mock(Brand.class);

        doReturn(brandEntityMock).when(brandEntityMapperMock).toEntity(brandMock);
        doReturn(brandEntityMock).when(brandRepositoryMock).save(brandEntityMock);

        BrandAdapter target = new BrandAdapter(brandRepositoryMock, brandEntityMapperMock);

        // Act
        target.saveBrand(brandMock);

        // Assert
        assertAll("result",
                () -> verify(brandEntityMapperMock).toEntity(brandMock),
                () -> verify(brandRepositoryMock).save(brandEntityMock)
        );
    }

    @Test()
    void brandExistsByNameWhenBrandRepositoryFindByBrandNameBrandNameIsPresent() {

         //(brandRepository.findByBrandName(brandName).isPresent()) : true

        //Arrange Statement
        doReturn(Optional.of(brandEntityMock)).when(brandRepositoryMock).findByBrandName("Samsung");
        BrandAdapter target = new BrandAdapter(brandRepositoryMock, brandEntityMapperMock);
        //Act Statement(s)
        boolean result = target.brandExistsByName("Samsung");
        //Assert statement
        assertAll("result", () -> {
            assertThat(result, equalTo(Boolean.TRUE));
            verify(brandRepositoryMock).findByBrandName("Samsung");
        });
    }

    @Test()
    void brandExistsByNameWhenBrandRepositoryFindByBrandNameBrandNameNotIsPresent() {

         // (brandRepository.findByBrandName(brandName).isPresent()) : false

        //Arrange Statement
        doReturn(Optional.empty()).when(brandRepositoryMock).findByBrandName("Samsung");
        BrandAdapter target = new BrandAdapter(brandRepositoryMock, brandEntityMapperMock);
        //Act Statement
        boolean result = target.brandExistsByName("Samsung");
        //Assert statement
        assertAll("result", () -> {
            assertThat(result, equalTo(Boolean.FALSE));
            verify(brandRepositoryMock).findByBrandName("Samsung");
        });
    }
}
