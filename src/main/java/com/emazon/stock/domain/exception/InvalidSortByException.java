package com.emazon.stock.domain.exception;

public class InvalidSortByException extends RuntimeException {
    public InvalidSortByException(String message) {
        super(message);
    }
}
