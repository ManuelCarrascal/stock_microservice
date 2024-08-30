package com.emazon.stock.ports.persistence.mysql.repository;

import com.emazon.stock.ports.persistence.mysql.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<CategoryEntity,Long> {
    Optional<CategoryEntity> findByCategoryName(String categoryName);

    @Query("SELECT c FROM CategoryEntity c JOIN c.products p WHERE p.productId = :productId ORDER BY c.categoryName ASC")
    List<CategoryEntity> findCategoriesByProductId(@Param("productId") Long productId);
}
