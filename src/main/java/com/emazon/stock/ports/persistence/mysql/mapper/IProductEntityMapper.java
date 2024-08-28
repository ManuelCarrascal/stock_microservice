package com.emazon.stock.ports.persistence.mysql.mapper;

import com.emazon.stock.domain.model.Product;
import com.emazon.stock.ports.persistence.mysql.entity.CategoryEntity;
import com.emazon.stock.ports.persistence.mysql.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {IBrandEntityMapper.class})

public interface IProductEntityMapper {
    @Mapping(source = "brandId", target = "brand", qualifiedByName = "idToBrand")
    @Mapping(source = "categoryIds", target = "categories", qualifiedByName = "idToCategory")

    ProductEntity toEntity(Product product);

    @Named("idToCategory")
    default CategoryEntity idToCategory(Long categoryId) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(categoryId);
        return categoryEntity;
    }

}