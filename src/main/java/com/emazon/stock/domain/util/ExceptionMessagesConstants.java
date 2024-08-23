package com.emazon.stock.domain.util;

public enum ExceptionMessagesConstants {
    ENTITY_ALREADY_EXISTS("already exists");


    private final String message;

    ExceptionMessagesConstants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
