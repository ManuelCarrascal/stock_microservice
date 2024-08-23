package com.emazon.stock.ports.application.http.mapper.category;

import com.emazon.stock.domain.model.Category;
import com.emazon.stock.ports.application.http.dto.CategoryRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICategoryRequestMapper {
    Category categoryRequestToCategory(CategoryRequest categoryRequest);
}
