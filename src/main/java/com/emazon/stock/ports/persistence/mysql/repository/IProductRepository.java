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

    @Query (QueriesConstants.FIND_BY_IDS)
    Page<ProductEntity> findByIds(List<Long> ids, Pageable pageable);

    @Query(QueriesConstants.FIND_BY_BRAND_NAME_AND_IDS )
    Page<ProductEntity> findByBrandNameAndIds(
            @Param(QueriesConstants.PARAM_BRAND_NAME) String brandName,
            @Param(QueriesConstants.PARAM_IDS) List<Long> ids,
            Pageable pageable
    );

    @Query(QueriesConstants.FIND_BY_CATEGORY_NAME_AND_IDS)
    Page<ProductEntity> findByCategoryAndIds(
            @Param(QueriesConstants.PARAM_CATEGORY_NAME) String categoryName,
            @Param(QueriesConstants.PARAM_IDS) List<Long> ids,
            Pageable pageable
    );

    @Query(QueriesConstants.FIND_BY_BRAND_NAME_AND_CATEGORY_NAME_AND_IDS)
    Page<ProductEntity> findByBrandNameAndCategoryNameAndIds(
            @Param(QueriesConstants.PARAM_BRAND_NAME) String brandName,
            @Param(QueriesConstants.PARAM_CATEGORY_NAME) String categoryName,
            @Param(QueriesConstants.PARAM_IDS) List<Long> ids, Pageable pageable
    );


    @Query("SELECT p FROM ProductEntity p WHERE p.productId IN :ids")
    List<ProductEntity> findAllProductsByProductIds(@Param("ids") List<Long> ids);
}
