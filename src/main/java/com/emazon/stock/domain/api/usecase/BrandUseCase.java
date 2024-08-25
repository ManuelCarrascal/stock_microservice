package com.emazon.stock.domain.api.usecase;

import com.emazon.stock.domain.api.IBrandServicePort;
import com.emazon.stock.domain.exception.EntityAlreadyExistsException;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.spi.brand.IBrandPersistencePort;
import com.emazon.stock.domain.util.EntityConstants;

public class BrandUseCase implements IBrandServicePort {

    private final IBrandPersistencePort brandPersistencePort;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }
    @Override
    public void saveBrand(Brand brand) {
        if (brandPersistencePort.brandExistsByName(brand.getBrandName())) {
            throw new EntityAlreadyExistsException(EntityConstants.BRAND_ENTITY_NAME.getName());
        }
        brandPersistencePort.saveBrand(brand);
    }
}
