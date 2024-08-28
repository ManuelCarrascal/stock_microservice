package com.emazon.stock.infrastructure.configuration.beanconfiguration;

import com.emazon.stock.domain.api.IBrandServicePort;
import com.emazon.stock.domain.api.ICategoryServicePort;
import com.emazon.stock.domain.api.IProductServicePort;
import com.emazon.stock.domain.api.usecase.BrandUseCase;
import com.emazon.stock.domain.api.usecase.CategoryUseCase;
import com.emazon.stock.domain.api.usecase.ProductUseCase;
import com.emazon.stock.domain.spi.brand.IBrandPersistencePort;
import com.emazon.stock.domain.spi.category.ICategoryPersistencePort;
import com.emazon.stock.domain.spi.product.IProductPersistencePort;
import com.emazon.stock.ports.persistence.mysql.adapter.BrandAdapter;
import com.emazon.stock.ports.persistence.mysql.adapter.CategoryAdapter;
import com.emazon.stock.ports.persistence.mysql.adapter.ProductAdapter;
import com.emazon.stock.ports.persistence.mysql.mapper.IBrandEntityMapper;
import com.emazon.stock.ports.persistence.mysql.mapper.ICategoryEntityMapper;
import com.emazon.stock.ports.persistence.mysql.mapper.IProductEntityMapper;
import com.emazon.stock.ports.persistence.mysql.repository.IBrandRepository;
import com.emazon.stock.ports.persistence.mysql.repository.ICategoryRepository;
import com.emazon.stock.ports.persistence.mysql.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;

    private final IProductRepository productRepository;
    private final IProductEntityMapper productEntityMapper;

    //Category
    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }

    //Brand
    @Bean
    public IBrandPersistencePort brandPersistencePort() {
        return new BrandAdapter(brandRepository, brandEntityMapper);
    }

    @Bean
    IBrandServicePort brandServicePort() {
        return new BrandUseCase(brandPersistencePort());
    }

    //Product
    @Bean
    public IProductPersistencePort productPersistencePort(){
        return new ProductAdapter(productRepository, productEntityMapper);
    }

    @Bean
    IProductServicePort productServicePort(){
        return new ProductUseCase(productPersistencePort());
    }


}
