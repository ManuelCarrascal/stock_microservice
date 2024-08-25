package com.emazon.stock.ports.persistence.mysql.adapter;

import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.spi.brand.IBrandPersistencePort;
import com.emazon.stock.ports.persistence.mysql.mapper.IBrandEntityMapper;
import com.emazon.stock.ports.persistence.mysql.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;

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
}
