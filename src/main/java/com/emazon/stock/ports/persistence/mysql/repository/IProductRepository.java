package com.emazon.stock.ports.persistence.mysql.repository;

import com.emazon.stock.ports.persistence.mysql.entity.ProductEntity;
import com.emazon.stock.ports.persistence.mysql.util.QueriesConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Query ("SELECT p FROM ProductEntity p WHERE p.productId IN :ids ")
    Page<ProductEntity> findByIds(List<Long> ids, Pageable pageable);

    @Query( "SELECT p FROM ProductEntity  p WHERE p.brand.brandName LIKE :brandName AND p.productId IN :ids")
    Page<ProductEntity> findByBrandNameAndIds(@Param("brandName") String brandName, @Param("ids") List<Long> ids, Pageable pageable);

    @Query("SELECT p FROM ProductEntity p JOIN p.categories c WHERE c.categoryName LIKE :categoryName AND p.productId IN :ids")
    Page<ProductEntity> findByCategoryAndIds(@Param("categoryName") String categoryName, @Param("ids") List<Long> ids, Pageable pageable);

    @Query("SELECT p FROM ProductEntity p JOIN p.brand b JOIN p.categories c WHERE b.brandName LIKE :brandName AND c.categoryName LIKE :categoryName AND p.productId IN :ids")
    Page<ProductEntity> findByBrandNameAndCategoryNameAndIds(@Param("brandName") String brandName, @Param("categoryName") String categoryName, @Param("ids") List<Long> ids, Pageable pageable);

}
