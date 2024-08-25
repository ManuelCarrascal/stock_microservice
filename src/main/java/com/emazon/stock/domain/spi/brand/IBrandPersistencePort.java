package com.emazon.stock.domain.spi.brand;

import com.emazon.stock.domain.model.Brand;

public interface IBrandPersistencePort {
    void saveBrand(Brand brand);
    boolean brandExistsByName(String brandName);
}
