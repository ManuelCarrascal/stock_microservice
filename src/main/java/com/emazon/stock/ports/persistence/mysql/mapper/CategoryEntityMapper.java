package com.emazon.stock.ports.persistence.mysql.mapper;

import com.emazon.stock.domain.model.Category;
import com.emazon.stock.ports.persistence.mysql.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {
    CategoryEntity toEntity(Category category);
}
