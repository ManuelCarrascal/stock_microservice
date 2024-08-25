package com.emazon.stock.ports.application.http.mapper.brand;

import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.ports.application.http.dto.brand.BrandRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IBrandRequestMapper {
    Brand brandRequestToBrand(BrandRequest brandRequest);

}
