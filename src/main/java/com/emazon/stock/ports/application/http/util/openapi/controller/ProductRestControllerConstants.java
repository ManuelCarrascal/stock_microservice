package com.emazon.stock.ports.application.http.util.openapi.controller;

public class ProductRestControllerConstants {
    public static final String TAG_NAME = "Product";
    public static final String TAG_DESCRIPTION = "Product API";
    public static final String SAVE_PRODUCT_SUMMARY = "Save a new product";
    public static final String SAVE_PRODUCT_DESCRIPTION = "Creates a new product in the database";
    public static final String SAVE_PRODUCT_RESPONSE_201_DESCRIPTION = "Product created successfully";
    public static final String SAVE_PRODUCT_RESPONSE_400_DESCRIPTION = "Invalid input";
    public static final String GET_ALL_PRODUCTS_PAGINATED_SUMMARY = "Get all products paginated";
    public static final String GET_ALL_PRODUCTS_PAGINATED_DESCRIPTION = "Retrieves a paginated list of products";
    public static final String GET_ALL_PRODUCTS_PAGINATED_RESPONSE_200_DESCRIPTION = "Products retrieved successfully";
    public static final String GET_ALL_PRODUCTS_PAGINATED_RESPONSE_400_DESCRIPTION = "Invalid pagination parameters";
    public static final String PARAM_PAGE_DESCRIPTION = "Page number";
    public static final String PARAM_PAGE_EXAMPLE = "1";
    public static final String PARAM_SIZE_DESCRIPTION = "Page size";
    public static final String PARAM_SIZE_EXAMPLE = "10";
    public static final String PARAM_SORT_BY_DESCRIPTION = "Sort by";
    public static final String PARAM_SORT_BY_EXAMPLE = "productName";
    public static final String PARAM_SORT_ORDER_DESCRIPTION = "Sort order";
    public static final String PARAM_SORT_ORDER_EXAMPLE = "true";
    public static final String PARAM_PRODUCT_REQUEST_BODY_DESCRIPTION = "Product request body";
    private ProductRestControllerConstants() {
    }
}
