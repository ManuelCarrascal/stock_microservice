package com.emazon.stock.ports.application.http.util.openapi.controller;

public class BrandRestControllerConstants {
    public static final String TAG_NAME = "Brand";
    public static final String TAG_DESCRIPTION = "Brand API";
    public static final String SAVE_BRAND_SUMMARY = "Save a new brand";
    public static final String SAVE_BRAND_DESCRIPTION = "Creates a new brand in the database";
    public static final String SAVE_BRAND_RESPONSE_201_DESCRIPTION = "Brand created successfully";
    public static final String SAVE_BRAND_RESPONSE_400_DESCRIPTION = "Invalid input";
    public static final String SAVE_BRAND_RESPONSE_409_DESCRIPTION = "Brand already exists";
    public static final String GET_ALL_BRANDS_PAGINATED_SUMMARY = "Get all brands paginated";
    public static final String GET_ALL_BRANDS_PAGINATED_DESCRIPTION = "Retrieves a paginated list of brands";
    public static final String GET_ALL_BRANDS_PAGINATED_RESPONSE_200_DESCRIPTION = "Brands retrieved successfully";
    public static final String GET_ALL_BRANDS_PAGINATED_RESPONSE_400_DESCRIPTION = "Invalid pagination parameters";
    public static final String PARAM_PAGE_DESCRIPTION = "Page number";
    public static final String PARAM_PAGE_EXAMPLE = "1";
    public static final String PARAM_SIZE_DESCRIPTION = "Page size";
    public static final String PARAM_SIZE_EXAMPLE = "10";
    public static final String PARAM_SORT_BY_DESCRIPTION = "Category name filter";
    public static final String PARAM_SORT_BY_EXAMPLE = "brandName";
    public static final String PARAM_SORT_ORDER_DESCRIPTION = "Sort order";
    public static final String PARAM_SORT_ORDER_EXAMPLE = "true";
    public static final String PARAM_BRAND_REQUEST_BODY_DESCRIPTION = "Brand request body";

    private BrandRestControllerConstants() {
    }
}
