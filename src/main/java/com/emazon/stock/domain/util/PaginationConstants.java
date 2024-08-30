package com.emazon.stock.domain.util;

public class PaginationConstants {
    public static final int MIN_PAGE_NUMBER = 0;
    public static final int MIN_PAGE_SIZE = 1;
    public static final String INVALID_SORT_BY_MESSAGE = "Invalid sortBy parameter: ";
    public static final String INVALID_PAGE_NUMBER_MESSAGE = "Page number cannot be negative";
    public static final String INVALID_PAGE_SIZE_MESSAGE = "Page size must be greater than "+MIN_PAGE_SIZE;

    private PaginationConstants() {

    }
}
