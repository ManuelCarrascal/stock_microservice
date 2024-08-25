package com.emazon.stock.ports.application.http.mapper.brand;

import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.ports.application.http.dto.brand.BrandResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBrandResponseMapper {
    List<BrandResponse> brandsToBrandResponses(List<Brand> brands);

}
