package com.emazon.stock.ports.application.http.util;

public class CategoryValidationConstants {
    public static final int NAME_MAX_LENGTH = 50;
    public static final int DESCRIPTION_MAX_LENGTH = 90;
    public static final String NAME_REQUIRED_MESSAGE = "category name is required";
    public static final String DESCRIPTION_REQUIRED_MESSAGE = "category description is required";
    public static final String NAME_LENGTH_MESSAGE = "The category name must not exceed 50 characters";
    public static final String DESCRIPTION_LENGTH_MESSAGE = "The category description must not exceed 90 characters";

    private CategoryValidationConstants() {
    }

}
