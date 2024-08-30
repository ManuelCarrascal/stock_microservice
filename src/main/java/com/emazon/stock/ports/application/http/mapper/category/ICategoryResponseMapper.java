package com.emazon.stock.ports.application.http.mapper.category;

import com.emazon.stock.domain.model.Category;
import com.emazon.stock.ports.application.http.dto.category.CategoryProductResponse;
import com.emazon.stock.ports.application.http.dto.category.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryResponseMapper {
    CategoryResponse categoryToCategoryResponse(Category category);
    List<CategoryResponse> categoriesToCategoryResponses(List<Category> categories);
    @Mapping(source = "categoryName", target = "categoryName")
    CategoryProductResponse categoryToCategoryProductResponse(Category category);

    List<CategoryProductResponse> categoriesToCategoryProductResponses(List<Category> categories);

}
