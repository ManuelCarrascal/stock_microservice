package com.emazon.stock.ports.persistence.mysql.repository;

import com.emazon.stock.ports.persistence.mysql.entity.CategoryEntity;
import com.emazon.stock.ports.persistence.mysql.util.QueriesConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<CategoryEntity,Long> {
    Optional<CategoryEntity> findByCategoryName(String categoryName);

    @Query(QueriesConstants.FIND_CATEGORIES_BY_PRODUCT_ID)
    List<CategoryEntity> findCategoriesByProductId(@Param(QueriesConstants.PARAM_PRODUCT_ID) Long productId);

    @Query(QueriesConstants.FIND_CATEGORY_NAMES_BY_PRODUCT_ID)
    List<String> findCategoryNamesByProductId(Long productId);
}
