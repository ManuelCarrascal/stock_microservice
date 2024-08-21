package com.emazon.stock.domain.model;

import java.util.List;

public class Pagination<T> {
    private final List<T> items;
    private final int currentPage;
    private final int pageSize;
    private final long totalItems;
    private final int totalPages;
    private final String sortDirection;



    public Pagination(List<T> items, int currentPage, int pageSize, long totalItems, int totalPages, String sortDirection) {
        this.items = items;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.sortDirection = sortDirection;


    }

    public List<T> getItems() {
        return items;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public String getSortDirection() {
        return sortDirection;
    }
}