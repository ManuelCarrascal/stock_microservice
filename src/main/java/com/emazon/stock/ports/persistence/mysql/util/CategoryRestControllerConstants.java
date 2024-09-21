package com.emazon.stock.ports.persistence.mysql.util;

public class CategoryRestControllerConstants {
    public static final String TAG_NAME = "Category";
    public static final String TAG_DESCRIPTION = "Category API";
    public static final String SAVE_CATEGORY_SUMMARY = "Save a new category";
    public static final String SAVE_CATEGORY_DESCRIPTION = "Creates a new category in the database";
    public static final String SAVE_CATEGORY_RESPONSE_201_DESCRIPTION = "Category created successfully";
    public static final String SAVE_CATEGORY_RESPONSE_400_DESCRIPTION = "Invalid input";
    public static final String SAVE_CATEGORY_RESPONSE_409_DESCRIPTION = "Category already exists";
    public static final String GET_ALL_CATEGORIES_PAGINATED_SUMMARY = "Get all categories paginated";
    public static final String GET_ALL_CATEGORIES_PAGINATED_DESCRIPTION = "Retrieves a paginated list of categories";
    public static final String GET_ALL_CATEGORIES_PAGINATED_RESPONSE_200_DESCRIPTION = "Categories retrieved successfully";
    public static final String GET_ALL_CATEGORIES_PAGINATED_RESPONSE_400_DESCRIPTION = "Invalid pagination parameters";
    public static final String PARAM_PAGE_DESCRIPTION = "Page number";
    public static final String PARAM_PAGE_EXAMPLE = "1";
    public static final String PARAM_SIZE_DESCRIPTION = "Page size";
    public static final String PARAM_SIZE_EXAMPLE = "10";
    public static final String PARAM_SORT_BY_DESCRIPTION = "Category name filter";
    public static final String PARAM_SORT_BY_EXAMPLE = "categoryName";
    public static final String PARAM_SORT_ORDER_DESCRIPTION = "Sort order";
    public static final String PARAM_SORT_ORDER_EXAMPLE = "true";
    public static final String PARAM_CATEGORY_REQUEST_BODY_DESCRIPTION = "Category request body";

    private CategoryRestControllerConstants() {
    }
}
