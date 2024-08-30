package com.emazon.stock.domain.util;

import java.util.Set;

public class SortByValidator {
    private static final Set<String> VALID_SORT_FIELDS = Set.of(
            SortBy.BRAND_NAME.getFieldName(),
            SortBy.PRODUCT_NAME.getFieldName(),
            SortBy.NUMBER_OF_CATEGORIES.getFieldName()
    );
    private SortByValidator() {
    }
    public static boolean isValidSortBy(String sortBy) {
        return VALID_SORT_FIELDS.contains(sortBy);
    }
}
