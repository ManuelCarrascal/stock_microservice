package com.emazon.stock.ports.application.http.util;

public class ProductValidationConstants {
    public static final int PRODUCT_QUANTITY_MIN_VALUE = 0;
    public static final int PRODUCT_QUANTITY_MAX_VALUE = 1000;
    public static final int PRODUCT_CATEGORY_MIN_SIZE = 1;
    public static final int PRODUCT_CATEGORY_MAX_SIZE = 3;
    public static final int PRODUCT_PRICE_MIN_VALUE = 0;
    public static final String PRODUCT_PRICE_MIN_MESSAGE = "The price must be a positive value";
    public static final String PRODUCT_NAME_REQUIRED_MESSAGE = "Product name is required";
    public static final String PRODUCT_DESCRIPTION_REQUIRED_MESSAGE = "Product description is required";
    public static final String PRODUCT_QUANTITY_REQUIRED_MESSAGE = "Product quantity is required";
    public static final String PRODUCT_QUANTITY_MIN_MESSAGE = "Product quantity must be at least "+PRODUCT_QUANTITY_MIN_VALUE;
    public static final String PRODUCT_QUANTITY_MAX_MESSAGE = "Product quantity must be less than or equal to "+PRODUCT_QUANTITY_MAX_VALUE;
    public static final String PRODUCT_PRICE_REQUIRED_MESSAGE = "Product price is required";
    public static final String PRODUCT_BRAND_REQUIRED_MESSAGE = "Every product needs a brand";
    public static final String PRODUCT_BRAND_ID_POSITIVE_MESSAGE = "Brand ID must be a valid id greater than 0";
    public static final String PRODUCT_CATEGORY_REQUIRED_MESSAGE = "Every product needs at least one category";
    public static final String PRODUCT_CATEGORY_SIZE_MESSAGE = "A product must have between " + PRODUCT_CATEGORY_MIN_SIZE + " and "+PRODUCT_CATEGORY_MAX_SIZE+" categories";
    public static final String PRODUCT_CATEGORY_UNIQUE_MESSAGE = "Every product needs unique categories";

    private ProductValidationConstants() {
    }
}
