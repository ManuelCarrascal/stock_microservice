package com.emazon.stock.ports.application.http.mapper.product;

import com.emazon.stock.domain.model.Product;
import com.emazon.stock.ports.application.http.dto.product.ProductQuantityRequest;
import com.emazon.stock.ports.application.http.dto.product.ProductRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IProductRequestMapper {
    Product productRequestToProduct(ProductRequest productRequest);
    @Mapping(source = "productQuantity", target = "productQuantity")
    Product productQuantityRequestToProduct(ProductQuantityRequest productQuantityRequest);

}
