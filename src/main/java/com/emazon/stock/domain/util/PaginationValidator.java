package com.emazon.stock.domain.util;

import com.emazon.stock.domain.exception.InvalidPageIndexException;
import com.emazon.stock.domain.exception.InvalidSortByException;

public class PaginationValidator {
    private PaginationValidator() {
    }

    public static void validate(PaginationUtil paginationUtil) {
        if (!SortByValidator.isValidSortBy(paginationUtil.getSortBy())) {
            throw new InvalidSortByException(PaginationConstants.INVALID_SORT_BY_MESSAGE + paginationUtil.getSortBy());
        }
        if (paginationUtil.getPageNumber() < PaginationConstants.MIN_PAGE_NUMBER) {
            throw new InvalidPageIndexException(PaginationConstants.INVALID_PAGE_NUMBER_MESSAGE);
        }
        if (paginationUtil.getPageSize() < PaginationConstants.MIN_PAGE_SIZE) {
            throw new IllegalArgumentException(PaginationConstants.INVALID_PAGE_SIZE_MESSAGE);
        }
    }
}
