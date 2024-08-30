package com.emazon.stock.ports.application.http.mapper.product;

import com.emazon.stock.domain.model.Product;
import com.emazon.stock.ports.application.http.dto.product.ProductResponse;
import com.emazon.stock.ports.application.http.mapper.category.ICategoryResponseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ICategoryResponseMapper.class})
public interface IProductResponseMapper  {
    ProductResponse productToProductResponse(Product product);
}
