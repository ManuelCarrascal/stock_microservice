package com.emazon.stock.ports.persistence.mysql.util;

public class CategoryEntityConstants {
    public static final String TABLE_NAME = "category";
    public static final String COLUMN_CATEGORY_ID = "category_id";
    public static final String COLUMN_CATEGORY_NAME = "category_name";
    public static final String COLUMN_CATEGORY_DESCRIPTION = "category_description";
    public static final int LENGTH_CATEGORY_NAME = 50;
    public static final int LENGTH_CATEGORY_DESCRIPTION = 90;
    public static final String MAPPED_BY_CATEGORIES = "categories";

    private CategoryEntityConstants() {
    }
}
