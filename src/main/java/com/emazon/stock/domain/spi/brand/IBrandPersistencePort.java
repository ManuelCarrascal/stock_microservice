package com.emazon.stock.domain.spi.brand;

import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.model.Pagination;
import com.emazon.stock.domain.util.PaginationUtil;

import java.util.List;

public interface IBrandPersistencePort {
    void saveBrand(Brand brand);
    boolean brandExistsByName(String brandName);
    Pagination<Brand> getAllBrandsPaginated(PaginationUtil paginationUtil);
    Brand brandGetById(Long brandId);
    List<Brand> getAllBrands();
}
