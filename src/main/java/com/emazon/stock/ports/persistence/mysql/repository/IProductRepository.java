package com.emazon.stock.ports.persistence.mysql.repository;

import com.emazon.stock.ports.persistence.mysql.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IProductRepository extends JpaRepository<ProductEntity,Long> {
    @Query("SELECT p FROM ProductEntity p ORDER BY p.productName ASC")
    Page<ProductEntity> findAllOrderByProductNameAsc(Pageable pageable);

    @Query("SELECT p FROM ProductEntity p ORDER BY p.productName DESC")
    Page<ProductEntity> findAllOrderByProductNameDesc(Pageable pageable);

    @Query("SELECT p FROM ProductEntity p JOIN p.brand b ORDER BY b.brandName ASC")
    Page<ProductEntity> findAllOrderByBrandNameAsc(Pageable pageable);

    @Query("SELECT p FROM ProductEntity p JOIN p.brand b ORDER BY b.brandName DESC")
    Page<ProductEntity> findAllOrderByBrandNameDesc(Pageable pageable);

    @Query("SELECT p FROM ProductEntity p LEFT JOIN p.categories c " +
            "GROUP BY p.productId " +
            "ORDER BY COUNT(c.categoryId) ASC, MIN(c.categoryName) ASC")
    Page<ProductEntity> findAllOrderByNumberOfCategoriesAsc(Pageable pageable);

    @Query("SELECT p FROM ProductEntity p LEFT JOIN p.categories c " +
            "GROUP BY p.productId " +
            "ORDER BY COUNT(c.categoryId) DESC, MIN(c.categoryName) ASC")
    Page<ProductEntity> findAllOrderByNumberOfCategoriesDesc(Pageable pageable);
}
