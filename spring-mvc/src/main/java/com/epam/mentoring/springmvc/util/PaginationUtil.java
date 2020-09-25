package com.epam.mentoring.springmvc.util;

/**
 * @author Kaikenov Adilhan
**/
public final class PaginationUtil {

    /**
     * Calculate the number of pages for a paginator
     *
     * @param totalItems
     *            The total items that are going to be paginated
     * @param pageSize
     *            The number of items on one page
     * @param minPageIndex
     *            The index of the first page
     *
     * @return The number of pages for a paginator
     */
    public static int calculatePageCount(final int totalItems, final int pageSize, final int minPageIndex) {
        return Math.max(((totalItems + pageSize) - 1) / pageSize, minPageIndex);
    }

}
