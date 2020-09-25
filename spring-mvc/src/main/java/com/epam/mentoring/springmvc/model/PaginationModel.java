package com.epam.mentoring.springmvc.model;

import com.epam.mentoring.springmvc.util.PaginationUtil;

import java.io.Serializable;

/**
 * @author Kaikenov Adilhan
**/
public class PaginationModel implements Serializable {

    private static final String DEFAULT_PARAMETER_NAME = "page";
    private static final int MIN_PAGE_INDEX = 1;

    private long totalItems;

    private int pageSize;

    private int currentPageSize;

    private String pageParameterName;

    public PaginationModel(long totalItems, int pageSize, int currentPageSize) {
        this(totalItems, pageSize, currentPageSize, DEFAULT_PARAMETER_NAME);
    }

    public PaginationModel(long totalItems, int pageSize, int currentPageSize, String pageParameterName) {
        this.totalItems = totalItems;
        this.pageSize = pageSize;
        this.currentPageSize = currentPageSize;
        this.pageParameterName = pageParameterName;
    }

    public long getTotalItems() {
        return this.totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPageSize() {
        return this.currentPageSize;
    }

    public void setCurrentPageSize(int currentPageSize) {
        this.currentPageSize = currentPageSize;
    }

    public String getPageParameterName() {
        return this.pageParameterName;
    }

    public void setPageParameterName(String pageParameterName) {
        this.pageParameterName = pageParameterName;
    }

    public int getFirstPageIndex() {
        return MIN_PAGE_INDEX;
    }

    public int getPageCount() {
        return PaginationUtil.calculatePageCount((int) this.totalItems, this.pageSize, MIN_PAGE_INDEX);
    }
}
