package pl.edu.agh.debtexecutor.services.options;

import javafx.beans.property.SimpleIntegerProperty;

public class PaginationOptions {
    private int initialPageSize = 1;
    private final SimpleIntegerProperty pageSize = new SimpleIntegerProperty(initialPageSize);
    private final SimpleIntegerProperty pageNumber = new SimpleIntegerProperty(1);
    private final SimpleIntegerProperty totalPages = new SimpleIntegerProperty(1);

    public int getPageSize() {
        return pageSize.get();
    }

    public SimpleIntegerProperty pageSizeProperty() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize.set(pageSize);
    }

    public int getPageNumber() {
        return pageNumber.get();
    }

    public SimpleIntegerProperty pageNumberProperty() {
        return pageNumber;
    }

    public void setInitialPageSize(int pageSize) {
        initialPageSize = pageSize;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber.set(pageNumber);
    }

    public int getTotalPages() {
        return totalPages.get();
    }

    public SimpleIntegerProperty totalPagesProperty() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        int totalPagesNumber = Math.max(totalPages, 1);
        this.totalPages.set(totalPagesNumber);
        if (pageNumber.get() > totalPagesNumber) {
            pageNumber.set(totalPagesNumber);
        }
    }

    public void clear() {
        pageSize.set(initialPageSize);
        pageNumber.set(1);
        totalPages.set(1);
    }
}
