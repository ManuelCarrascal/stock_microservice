package com.emazon.stock.ports.persistence.mysql.mapper;

import com.emazon.stock.domain.model.Category;
import com.emazon.stock.ports.persistence.mysql.entity.CategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryEntityMapper {
    CategoryEntity toEntity(Category category);
    Category toCategory(CategoryEntity categoryEntity);
    List<Category> toCategoryList(List<CategoryEntity> categoryEntityList);

}
