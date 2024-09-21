package com.emazon.stock.ports.persistence.mysql.repository;

import com.emazon.stock.ports.persistence.mysql.entity.ProductEntity;
import com.emazon.stock.ports.persistence.mysql.util.QueriesConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


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

    @Query(QueriesConstants.FIND_STOCK_QUANTITY_PRODUCT)
    Long findStockQuantityProduct(Long productId);

    @Query("SELECT p FROM ProductEntity p WHERE p.productId IN :ids")
    Page<ProductEntity> findByIdIn(List<Long> ids, Pageable pageable);

    @Query("SELECT p FROM ProductEntity p WHERE p.productId IN :ids ORDER BY p.productName ASC")
    Page<ProductEntity> findByIdInOrderByProductNameAsc(List<Long> ids, Pageable pageable);

    @Query("SELECT p FROM ProductEntity p WHERE p.productId IN :ids ORDER BY p.productName DESC")
    Page<ProductEntity> findByIdInOrderByProductNameDesc(List<Long> ids, Pageable pageable);

    @Query("SELECT p FROM ProductEntity p WHERE p.productId IN :ids ORDER BY p.brand.brandName ASC")
    Page<ProductEntity> findByIdInOrderByBrandNameAsc(List<Long> ids, Pageable pageable);

    @Query("SELECT p FROM ProductEntity p WHERE p.productId IN :ids ORDER BY p.brand.brandName DESC")
    Page<ProductEntity> findByIdInOrderByBrandNameDesc(List<Long> ids, Pageable pageable);

    @Query("SELECT p FROM ProductEntity p WHERE p.productId IN :ids ORDER BY size(p.categories) ASC")
    Page<ProductEntity> findByIdInOrderByNumberOfCategoriesAsc(List<Long> ids, Pageable pageable);

    @Query("SELECT p FROM ProductEntity p WHERE p.productId IN :ids ORDER BY size(p.categories) DESC")
    Page<ProductEntity> findByIdInOrderByNumberOfCategoriesDesc(List<Long> ids, Pageable pageable);


}
