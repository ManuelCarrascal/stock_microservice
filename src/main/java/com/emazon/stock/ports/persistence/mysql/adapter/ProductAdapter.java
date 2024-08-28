package com.emazon.stock.ports.persistence.mysql.adapter;

import com.emazon.stock.domain.model.Product;
import com.emazon.stock.domain.spi.product.IProductPersistencePort;
import com.emazon.stock.ports.persistence.mysql.mapper.IProductEntityMapper;
import com.emazon.stock.ports.persistence.mysql.repository.IProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductAdapter implements IProductPersistencePort {
    private final IProductRepository productRepository;
    private final IProductEntityMapper productEntityMapper;

    @Override
    public void saveProduct(Product product) {
        productRepository.save(productEntityMapper.toEntity(product));
    }

}
