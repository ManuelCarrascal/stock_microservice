package com.emazon.stock.ports.application.http.mapper.brand;

import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.ports.application.http.dto.brand.BrandProductResponse;
import com.emazon.stock.ports.application.http.dto.brand.BrandResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBrandResponseMapper {
    BrandResponse brandToBrandResponse(Brand brand);

    List<BrandResponse> brandsToBrandResponses(List<Brand> brands);
    @Mapping(source = "brandName", target = "brandName")
    BrandProductResponse brandToBrandProductResponse(Brand brand);
}