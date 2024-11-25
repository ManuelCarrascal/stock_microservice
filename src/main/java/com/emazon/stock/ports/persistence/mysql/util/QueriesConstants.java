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
    public static final String FIND_CATEGORY_NAMES_BY_PRODUCT_ID = "SELECT c.categoryName FROM CategoryEntity c JOIN c.products p WHERE p.productId = :productId";
    public static final String FIND_STOCK_QUANTITY_PRODUCT = "SELECT p.productQuantity FROM ProductEntity p WHERE p.productId = :productId";

    public static final String FIND_BY_IDS = "SELECT p FROM ProductEntity p WHERE p.productId IN :ids ";
    public static final String FIND_BY_BRAND_NAME_AND_IDS = "SELECT p FROM ProductEntity  p WHERE p.brand.brandName LIKE :brandName AND p.productId IN :ids";
    public static final String FIND_BY_CATEGORY_NAME_AND_IDS = "SELECT p FROM ProductEntity p JOIN p.categories c WHERE c.categoryName LIKE :categoryName AND p.productId IN :ids";
    public static final String FIND_BY_BRAND_NAME_AND_CATEGORY_NAME_AND_IDS ="SELECT p FROM ProductEntity p JOIN p.brand b JOIN p.categories c WHERE b.brandName LIKE :brandName AND c.categoryName LIKE :categoryName AND p.productId IN :ids";

    public static final String PARAM_IDS = "ids";
    public static final String PARAM_BRAND_NAME = "brandName";
    public static final String PARAM_CATEGORY_NAME = "categoryName";

    private QueriesConstants() {
    }
}
