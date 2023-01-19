package pl.edu.agh.debtexecutor.expenses.model;

import org.springframework.data.domain.Sort;

public class ExpensePage {
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final String DEFAULT_SORT_BY = "date";
    private static final Sort.Direction DEFAULT_SORT_DIRECTION =
            Sort.Direction.ASC;
    private int pageNumber = 0;
    private int pageSize = DEFAULT_PAGE_SIZE;
    private String sortBy = DEFAULT_SORT_BY;
    private Sort.Direction sortDirection = DEFAULT_SORT_DIRECTION;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public Sort.Direction getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(Sort.Direction sortDirection) {
        this.sortDirection = sortDirection;
    }
}
