package pl.edu.agh.debtexecutor.services.utils;

import javafx.beans.property.SimpleIntegerProperty;
import pl.edu.agh.debtexecutor.services.options.PaginationOptions;

public class PaginationService {
    private final SimpleIntegerProperty pageSize = new SimpleIntegerProperty();
    private final SimpleIntegerProperty pageNumber = new SimpleIntegerProperty();
    private final SimpleIntegerProperty totalPages = new SimpleIntegerProperty();
    private final PaginationOptions paginationOptions;

    public PaginationService(Paginable service) {
        paginationOptions = service.getPaginationOptions();
    }

    public int getPageSize() {
        return pageSize.get();
    }

    public SimpleIntegerProperty pageSizeProperty() {
        return pageSize;
    }

    public int getPageNumber() {
        return pageNumber.get();
    }

    public SimpleIntegerProperty pageNumberProperty() {
        return pageNumber;
    }

    public int getTotalPages() {
        return totalPages.get();
    }

    public SimpleIntegerProperty totalPagesProperty() {
        return totalPages;
    }

    public void nextPage() {
        int value = Math.min(totalPages.get(), pageNumber.get() + 1);
        pageNumber.set(value);
        paginationOptions.setPageNumber(value);
    }

    public void prevPage() {
        int value = Math.max(0, pageNumber.get() - 1);
        pageNumber.set(value);
        paginationOptions.setPageNumber(value);
    }

    public void lastPage() {
        pageNumber.set(totalPages.get());
        paginationOptions.setPageNumber(totalPages.get());
    }

    public void firstPage() {
        pageNumber.set(0);
        paginationOptions.setPageNumber(0);
    }

    public void setPageSize(int size) {
        pageSize.set(size);
        paginationOptions.setPageSize(size);
    }
}
