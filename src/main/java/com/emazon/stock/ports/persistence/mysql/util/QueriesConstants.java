package com.emazon.stock.ports.persistence.mysql.util;

public class QueriesConstants {
    public static final String FIND_CATEGORIES_BY_PRODUCT_ID = "SELECT c FROM CategoryEntity c JOIN c.products p WHERE p.productId = :productId ORDER BY c.categoryName ASC";
    public static final String PARAM_PRODUCT_ID = "productId";
    public static final String FIND_ALL_ORDER_BY_PRODUCT_NAME_ASC = "SELECT p FROM ProductEntity p ORDER BY p.productName ASC";
    public static final String FIND_ALL_ORDER_BY_PRODUCT_NAME_DESC = "SELECT p FROM ProductEntity p ORDER BY p.productName DESC";
    public static final String FIND_ALL_ORDER_BY_BRAND_NAME_ASC = "SELECT p FROM ProductEntity p JOIN p.brand b ORDER BY b.brandName ASC";
    public static final String FIND_ALL_ORDER_BY_BRAND_NAME_DESC = "SELECT p FROM ProductEntity p JOIN p.brand b ORDER BY b.brandName DESC";
    public static final String FIND_ALL_ORDER_BY_NUMBER_OF_CATEGORIES_ASC = "SELECT p FROM ProductEntity p LEFT JOIN p.categories c " + "GROUP BY p.productId " + "ORDER BY COUNT(c.categoryId) ASC, MIN(c.categoryName) ASC";
    public static final String FIND_ALL_ORDER_BY_NUMBER_OF_CATEGORIES_DESC = "SELECT p FROM ProductEntity p LEFT JOIN p.categories c " + "GROUP BY p.productId " + "ORDER BY COUNT(c.categoryId) DESC, MIN(c.categoryName) ASC";

    private QueriesConstants() {
    }
}
