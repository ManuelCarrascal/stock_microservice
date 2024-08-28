package com.emazon.stock.ports.persistence.mysql.adapter;

import com.emazon.stock.domain.model.Pagination;
import com.emazon.stock.domain.util.PaginationUtil;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Test;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.ports.persistence.mysql.mapper.IBrandEntityMapper;
import com.emazon.stock.ports.persistence.mysql.entity.BrandEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import com.emazon.stock.ports.persistence.mysql.repository.IBrandRepository;
import org.mockito.MockedStatic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
class BrandAdapterTest {

    private final IBrandEntityMapper brandEntityMapperMock = mock(IBrandEntityMapper.class, "brandEntityMapper");

    private final IBrandRepository brandRepositoryMock = mock(IBrandRepository.class, "brandRepository");

    private final BrandEntity brandEntityMock = mock(BrandEntity.class);

    private final PageRequest pageRequestMock = mock(PageRequest.class);

    private final Sort sortMock = mock(Sort.class);


    @Test
    void saveBrandTest() {
        Brand brandMock = mock(Brand.class);

        doReturn(brandEntityMock).when(brandEntityMapperMock).toEntity(brandMock);
        doReturn(brandEntityMock).when(brandRepositoryMock).save(brandEntityMock);

        BrandAdapter target = new BrandAdapter(brandRepositoryMock, brandEntityMapperMock);

        target.saveBrand(brandMock);

        assertAll("result",
                () -> verify(brandEntityMapperMock).toEntity(brandMock),
                () -> verify(brandRepositoryMock).save(brandEntityMock)
        );
    }

    @Test()
    void brandExistsByNameWhenBrandRepositoryFindByBrandNameBrandNameIsPresent() {

        doReturn(Optional.of(brandEntityMock)).when(brandRepositoryMock).findByBrandName("Samsung");
        BrandAdapter target = new BrandAdapter(brandRepositoryMock, brandEntityMapperMock);
        boolean result = target.brandExistsByName("Samsung");
        assertAll("result", () -> {
            assertThat(result, equalTo(Boolean.TRUE));
            verify(brandRepositoryMock).findByBrandName("Samsung");
        });
    }

    @Test()
    void brandExistsByNameWhenBrandRepositoryFindByBrandNameBrandNameNotIsPresent() {

        doReturn(Optional.empty()).when(brandRepositoryMock).findByBrandName("Samsung");
        BrandAdapter target = new BrandAdapter(brandRepositoryMock, brandEntityMapperMock);
        boolean result = target.brandExistsByName("Samsung");
        assertAll("result", () -> {
            assertThat(result, equalTo(Boolean.FALSE));
            verify(brandRepositoryMock).findByBrandName("Samsung");
        });
    }

    @Test
    void getAllBrandsPaginatedWhenPaginationUtilIsAscending() {
        try (MockedStatic<Sort> sortMockStatic = mockStatic(Sort.class);
             MockedStatic<PageRequest> pageRequestMockStatic = mockStatic(PageRequest.class, CALLS_REAL_METHODS)) {

            List<BrandEntity> brandEntityList = Collections.emptyList();
            List<Brand> brandList = Collections.emptyList();

            Page<BrandEntity> brandPageMock = mock(Page.class);

            when(brandRepositoryMock.findAll(any(PageRequest.class))).thenReturn(brandPageMock);
            when(brandPageMock.getContent()).thenReturn(brandEntityList);
            when(brandPageMock.getTotalPages()).thenReturn(0);
            when(brandPageMock.getTotalElements()).thenReturn(1L);
            when(brandEntityMapperMock.toBrandList(brandEntityList)).thenReturn(brandList);

            sortMockStatic.when(() -> Sort.by(Sort.Direction.ASC, "brandName")).thenReturn(sortMock);
            pageRequestMockStatic.when(() -> PageRequest.of(0, 0, sortMock)).thenReturn(pageRequestMock);

            PaginationUtil paginationUtil = new PaginationUtil();
            paginationUtil.setPageNumber(0);
            paginationUtil.setPageSize(0);
            paginationUtil.setAscending(true);
            paginationUtil.setNameFilter("brandName");
            BrandAdapter brandAdapter = new BrandAdapter(brandRepositoryMock, brandEntityMapperMock);

            Pagination<Brand> result = brandAdapter.getAllBrandsPaginated(paginationUtil);

            assertThat(result.getContent(), is(brandList));
            assertThat(result.getTotalPages(), is(0));
            assertThat(result.getTotalElements(), is(1L));

            verify(brandRepositoryMock).findAll(pageRequestMock);
            verify(brandPageMock).getContent();
            verify(brandPageMock).getTotalPages();
            verify(brandPageMock).getTotalElements();
            verify(brandEntityMapperMock).toBrandList(brandEntityList);
            sortMockStatic.verify(() -> Sort.by(Sort.Direction.ASC, "brandName"));
            pageRequestMockStatic.verify(() -> PageRequest.of(0, 0, sortMock));
        }
    }

    @Test
    void getAllBrandsPaginatedWhenPaginationUtilNotIsAscending() {
        try (MockedStatic<Sort> sortMockStatic = mockStatic(Sort.class);
             MockedStatic<PageRequest> pageRequestMockStatic = mockStatic(PageRequest.class, CALLS_REAL_METHODS)) {

            List<BrandEntity> brandEntityList = Collections.emptyList();
            List<Brand> brandList = Collections.emptyList();

            Page<BrandEntity> brandPageMock = mock(Page.class);

            when(brandRepositoryMock.findAll(any(PageRequest.class))).thenReturn(brandPageMock);
            when(brandPageMock.getContent()).thenReturn(brandEntityList);
            when(brandPageMock.getTotalPages()).thenReturn(0);
            when(brandPageMock.getTotalElements()).thenReturn(1L);
            when(brandEntityMapperMock.toBrandList(brandEntityList)).thenReturn(brandList);

            sortMockStatic.when(() -> Sort.by(Sort.Direction.DESC, "brandName")).thenReturn(sortMock);
            pageRequestMockStatic.when(() -> PageRequest.of(0, 0, sortMock)).thenReturn(pageRequestMock);

            PaginationUtil paginationUtil = new PaginationUtil();
            paginationUtil.setPageNumber(0);
            paginationUtil.setPageSize(0);
            paginationUtil.setAscending(false);
            paginationUtil.setNameFilter("brandName");
            BrandAdapter brandAdapter = new BrandAdapter(brandRepositoryMock, brandEntityMapperMock);

            Pagination<Brand> result = brandAdapter.getAllBrandsPaginated(paginationUtil);

            assertThat(result.getContent(), is(brandList));
            assertThat(result.getTotalPages(), is(0));
            assertThat(result.getTotalElements(), is(1L));

            verify(brandRepositoryMock).findAll(pageRequestMock);
            verify(brandPageMock).getContent();
            verify(brandPageMock).getTotalPages();
            verify(brandPageMock).getTotalElements();
            verify(brandEntityMapperMock).toBrandList(brandEntityList);
            sortMockStatic.verify(() -> Sort.by(Sort.Direction.DESC, "brandName"));
            pageRequestMockStatic.verify(() -> PageRequest.of(0, 0, sortMock));
        }
    }

}
