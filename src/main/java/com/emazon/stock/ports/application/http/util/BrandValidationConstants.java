package com.emazon.stock.ports.application.http.util;

public class BrandValidationConstants {
    public static final int NAME_MAX_LENGTH = 50;
    public static final int DESCRIPTION_MAX_LENGTH = 120;
    public static final String NAME_REQUIRED_MESSAGE = "brand name is required";
    public static final String DESCRIPTION_REQUIRED_MESSAGE = "brand description is required";
    public static final int MIN_LENGTH_BRAND = 3;
    public static final String NAME_LENGTH_MESSAGE = "The brand name must be between " + MIN_LENGTH_BRAND + " and 50 characters";
    public static final String DESCRIPTION_LENGTH_MESSAGE = "The brand description must be between " + MIN_LENGTH_BRAND + " and 120 characters";

    private BrandValidationConstants() {

    }
}
