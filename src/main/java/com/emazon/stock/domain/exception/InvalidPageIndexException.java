package com.emazon.stock.domain.exception;

public class InvalidPageIndexException extends RuntimeException {
    public InvalidPageIndexException(String message) {
        super(message);
    }
}