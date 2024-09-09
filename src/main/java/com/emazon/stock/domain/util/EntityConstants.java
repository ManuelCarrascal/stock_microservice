package com.emazon.stock.domain.util;

public enum EntityConstants {
    BRAND_ENTITY_NAME("Brand"),
    CATEGORY_ENTITY_NAME("Category");

    private final String name;

    EntityConstants(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
