package com.emazon.stock.domain.api;

import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.model.Pagination;
import com.emazon.stock.domain.util.PaginationUtil;

public interface IBrandServicePort {
    void saveBrand(Brand brand);
    Pagination<Brand> getAllBrandsPaginated(PaginationUtil paginationUtil);
}
