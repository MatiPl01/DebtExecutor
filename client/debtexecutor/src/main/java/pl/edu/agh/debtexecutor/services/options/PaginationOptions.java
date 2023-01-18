package pl.edu.agh.debtexecutor.services.options;

import javafx.beans.property.SimpleIntegerProperty;

public class PaginationOptions {
    private final SimpleIntegerProperty pageSize = new SimpleIntegerProperty();
    private final SimpleIntegerProperty pageNumber = new SimpleIntegerProperty(1);
    private final SimpleIntegerProperty totalPages = new SimpleIntegerProperty(1);

    public PaginationOptions(int pageSize) {
        this.pageSize.set(pageSize);
    }

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
        this.totalPages.set(totalPages);
    }
}
