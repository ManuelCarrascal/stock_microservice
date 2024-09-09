package com.emazon.stock.ports.persistence.mysql.util;

public class BrandEntityConstants {
    public static final String TABLE_NAME = "brand";
    public static final String COLUMN_BRAND_ID = "brand_id";
    public static final String COLUMN_BRAND_NAME = "brand_name";
    public static final String COLUMN_BRAND_DESCRIPTION = "brand_description";
    public static final int LENGTH_BRAND_NAME = 50;
    public static final int LENGTH_BRAND_DESCRIPTION = 120;
    public static final String MAPPED_BY_BRAND = "brand";

    private BrandEntityConstants() {
    }
}