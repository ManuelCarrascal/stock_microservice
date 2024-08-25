package com.emazon.stock.ports.persistence.mysql.adapter;

import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.model.Pagination;
import com.emazon.stock.domain.spi.brand.IBrandPersistencePort;
import com.emazon.stock.domain.util.PaginationUtil;
import com.emazon.stock.ports.persistence.mysql.entity.BrandEntity;
import com.emazon.stock.ports.persistence.mysql.mapper.IBrandEntityMapper;
import com.emazon.stock.ports.persistence.mysql.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class BrandAdapter implements IBrandPersistencePort {
    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;


    @Override
    public void saveBrand(Brand brand) {
        brandRepository.save(brandEntityMapper.toEntity(brand));
    }

    @Override
    public boolean brandExistsByName(String brandName) {
        return brandRepository.findByBrandName(brandName).isPresent();
    }

    @Override
    public Pagination<Brand> getAllBrandsPaginated(PaginationUtil paginationUtil) {
        Sort.Direction sortDirection = paginationUtil.isAscending()? Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest pageRequest = PageRequest.of(paginationUtil.getPageNumber(), paginationUtil.getPageSize(), Sort.by(sortDirection, paginationUtil.getNameFilter()));
        Page<BrandEntity> brandPage = brandRepository.findAll(pageRequest);
        List<Brand> brands = brandEntityMapper.toBrandList(brandPage.getContent());
        return new Pagination<>(
                paginationUtil.isAscending(),
                paginationUtil.getPageNumber(),
                brandPage.getTotalPages(),
                brandPage.getTotalElements(),
                brands
        );
    }
}
