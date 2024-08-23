package com.emazon.stock.ports.application.http.mapper.category;

import com.emazon.stock.domain.model.Category;
import com.emazon.stock.ports.application.http.dto.CategoryResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryResponseMapper {
    CategoryResponse categoryToCategoryResponse(Category category);
    List<CategoryResponse> categoriesToCategoryResponses(List<Category> categories);
}
