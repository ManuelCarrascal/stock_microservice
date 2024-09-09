package com.emazon.stock.ports.persistence.mysql.util;

public class ProductEntityConstants {
    public static final String TABLE_NAME = "product";
    public static final String COLUMN_PRODUCT_ID = "product_id";
    public static final String COLUMN_PRODUCT_NAME = "product_name";
    public static final String COLUMN_PRODUCT_DESCRIPTION = "product_description";
    public static final String COLUMN_PRODUCT_QUANTITY = "product_quantity";
    public static final String COLUMN_PRODUCT_PRICE = "product_price";
    public static final String JOIN_COLUMN_BRAND_ID = "brand_id";
    public static final String JOIN_TABLE_PRODUCT_CATEGORY = "product_category";
    public static final String JOIN_COLUMN_PRODUCT_ID = "product_id";
    public static final String JOIN_COLUMN_CATEGORY_ID = "category_id";
    public static final int LENGTH_PRODUCT_NAME = 60;
    public static final int LENGTH_PRODUCT_DESCRIPTION = 90;

    private ProductEntityConstants() {
    }
}
