package com.emazon.stock.infrastructure.configuration.beanconfiguration;

import com.emazon.stock.domain.api.ICategoryServicePort;
import com.emazon.stock.domain.api.usecase.CategoryUseCase;
import com.emazon.stock.domain.spi.category.ICategoryPersistencePort;
import com.emazon.stock.ports.persistence.mysql.adapter.CategoryAdapter;
import com.emazon.stock.ports.persistence.mysql.mapper.CategoryEntityMapper;
import com.emazon.stock.ports.persistence.mysql.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }

}
