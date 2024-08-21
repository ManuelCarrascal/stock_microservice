package com.emazon.stock.domain.util;

public enum ExceptionMessagesConstants {
   ENTITY_ALREADY_EXISTS("already exists"),
   CATEGORY_NAME_REQUIRED("category name is required"),
    CATEGORY_DESCRIPTION_REQUIRED("category description is required"),
    CATEGORY_NAME_LENGTH("The category name must not exceed 50 characters"),
    CATEGORY_DESCRIPTION_LENGTH("The category description must not exceed 90 characters");


    private final String message;

    ExceptionMessagesConstants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
