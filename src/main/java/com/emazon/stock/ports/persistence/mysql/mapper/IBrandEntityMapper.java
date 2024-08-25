package com.emazon.stock.ports.persistence.mysql.mapper;

import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.ports.persistence.mysql.entity.BrandEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IBrandEntityMapper {
    BrandEntity toEntity (Brand brand);
}
