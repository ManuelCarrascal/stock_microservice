package com.emazon.stock.ports.application.http.util;

public class CategoryValidationConstants {
    public static final int NAME_MAX_LENGTH = 50;
    public static final int DESCRIPTION_MAX_LENGTH = 90;
    public static final String NAME_REQUIRED_MESSAGE = "category name is required";
    public static final String DESCRIPTION_REQUIRED_MESSAGE = "category description is required";
    public static final int MIN_LENGTH_CATEGORY=3;
    public static final String NAME_LENGTH_MESSAGE = "The category name must be between " + MIN_LENGTH_CATEGORY + " and 50 characters";
    public static final String DESCRIPTION_LENGTH_MESSAGE = "The category description must be between " + MIN_LENGTH_CATEGORY + " and 90 characters";

    private CategoryValidationConstants() {
    }

}
