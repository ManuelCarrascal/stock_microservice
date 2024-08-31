package com.emazon.stock.ports.persistence.mysql.repository;

import com.emazon.stock.ports.persistence.mysql.entity.ProductEntity;
import com.emazon.stock.ports.persistence.mysql.util.QueriesConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IProductRepository extends JpaRepository<ProductEntity,Long> {

    @Query(QueriesConstants.FIND_ALL_ORDER_BY_PRODUCT_NAME_ASC)
    Page<ProductEntity> findAllOrderByProductNameAsc(Pageable pageable);

    @Query(QueriesConstants.FIND_ALL_ORDER_BY_PRODUCT_NAME_DESC)
    Page<ProductEntity> findAllOrderByProductNameDesc(Pageable pageable);

    @Query(QueriesConstants.FIND_ALL_ORDER_BY_BRAND_NAME_ASC)
    Page<ProductEntity> findAllOrderByBrandNameAsc(Pageable pageable);

    @Query(QueriesConstants.FIND_ALL_ORDER_BY_BRAND_NAME_DESC)
    Page<ProductEntity> findAllOrderByBrandNameDesc(Pageable pageable);

    @Query(QueriesConstants.FIND_ALL_ORDER_BY_NUMBER_OF_CATEGORIES_ASC)
    Page<ProductEntity> findAllOrderByNumberOfCategoriesAsc(Pageable pageable);

    @Query(QueriesConstants.FIND_ALL_ORDER_BY_NUMBER_OF_CATEGORIES_DESC)
    Page<ProductEntity> findAllOrderByNumberOfCategoriesDesc(Pageable pageable);
}
