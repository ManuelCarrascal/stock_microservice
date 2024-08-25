package com.emazon.stock.domain.util;

public enum EntityConstants {
    BRAND_ENTITY_NAME("Brand");

    private final String name;

    EntityConstants(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
