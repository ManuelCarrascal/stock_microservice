package com.emazon.stock.domain.api.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.util.PaginationUtil;
import com.emazon.stock.domain.model.Pagination;
import com.emazon.stock.domain.exception.EntityAlreadyExistsException;
import com.emazon.stock.domain.spi.brand.IBrandPersistencePort;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
class BrandUseCaseTest {

    private IBrandPersistencePort brandPersistencePortMock;
    private BrandUseCase target;

    @BeforeEach
    void setUp() {
        brandPersistencePortMock = mock(IBrandPersistencePort.class);
        target = new BrandUseCase(brandPersistencePortMock);
    }

    @Test
    void saveBrandWhenBrandExistsByName() {
        doReturn(true).when(brandPersistencePortMock).brandExistsByName("brandName1");
        Brand brand = new Brand();
        brand.setBrandName("brandName1");

        EntityAlreadyExistsException thrown = assertThrows(EntityAlreadyExistsException.class, () -> target.saveBrand(brand));
        assertThat(thrown, is(notNullValue()));
        verify(brandPersistencePortMock).brandExistsByName("brandName1");
    }

    @Test
    void saveBrandWhenBrandDoesNotExistByName() {
        doReturn(false).when(brandPersistencePortMock).brandExistsByName("brandName1");
        Brand brand = new Brand();
        brand.setBrandName("brandName1");
        doNothing().when(brandPersistencePortMock).saveBrand(brand);

        target.saveBrand(brand);

        assertAll("result",
                () -> verify(brandPersistencePortMock).brandExistsByName("brandName1"),
                () -> verify(brandPersistencePortMock).saveBrand(brand)
        );
    }

    @Test
    void getAllBrandsPaginated() {
        Pagination<Brand> paginationMock = mock(Pagination.class);
        PaginationUtil paginationUtilMock = mock(PaginationUtil.class);
        doReturn(paginationMock).when(brandPersistencePortMock).getAllBrandsPaginated(paginationUtilMock);

        Pagination<Brand> result = target.getAllBrandsPaginated(paginationUtilMock);

        assertAll("result",
                () -> assertThat(result, is(equalTo(paginationMock))),
                () -> verify(brandPersistencePortMock).getAllBrandsPaginated(paginationUtilMock)
        );
    }

    @Test
    void brandGetById() {
        Brand brandMock = mock(Brand.class);
        doReturn(brandMock).when(brandPersistencePortMock).brandGetById(0L);

        Brand result = target.brandGetById(0L);

        assertAll("result",
                () -> assertThat(result, is(equalTo(brandMock))),
                () -> verify(brandPersistencePortMock).brandGetById(0L)
        );
    }
}
