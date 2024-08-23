package com.emazon.stock.domain.util;

public class PaginationUtil {
    private int pageSize;
    private int pageNumber;
    private String nameFilter;
    private boolean ascending;

    public PaginationUtil() {
    }

    public PaginationUtil(int pageSize, int pageNumber, String nameFilter, boolean ascending) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.nameFilter = nameFilter;
        this.ascending = ascending;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getNameFilter() {
        return nameFilter;
    }

    public void setNameFilter(String nameFilter) {
        this.nameFilter = nameFilter;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }
}
