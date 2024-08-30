package com.emazon.stock.domain.util;

public enum SortBy {
    PRODUCT_NAME("productName"),
    BRAND_NAME("brandName"),
    NUMBER_OF_CATEGORIES("numberOfCategories");

    private final String fieldName;

    SortBy(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

}
