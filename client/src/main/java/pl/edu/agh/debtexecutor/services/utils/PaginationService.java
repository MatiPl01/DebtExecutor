package pl.edu.agh.debtexecutor.services.utils;

import pl.edu.agh.debtexecutor.services.options.Paginable;
import pl.edu.agh.debtexecutor.services.options.PaginationOptions;

public class PaginationService {
    private final Paginable paginable;
    private final PaginationOptions paginationOptions;

    private boolean isInitialPageSizeSet = false;

    public PaginationService(Paginable paginable) {
        this.paginable = paginable;
        paginationOptions = paginable.getPaginationOptions();
    }

    public PaginationOptions getPaginationOptions() {
        return paginationOptions;
    }

    public void setPageSize(int pageSize) {
        int size = Math.max(1, pageSize);
        paginationOptions.setPageNumber(getCurrentPageAfterSizeChange(pageSize));
        paginationOptions.setPageSize(size);
        if (!isInitialPageSizeSet) paginationOptions.setInitialPageSize(size);
        isInitialPageSizeSet = true;
        paginable.updatePagination();
    }

    public void setCurrentPage(int pageNumber) {
        paginationOptions.setPageNumber(Math.min(
                Math.max(1, pageNumber),
                paginationOptions.getTotalPages()
        ));
        paginable.updatePagination();
    }

    public void nextPage() {
        paginationOptions.setPageNumber(Math.min(
                paginationOptions.getPageNumber() + 1,
                paginationOptions.getTotalPages()
        ));
        paginable.updatePagination();
    }

    public void prevPage() {
        paginationOptions.setPageNumber(Math.max(
                paginationOptions.getPageNumber() - 1,
                1
        ));
        paginable.updatePagination();
    }

    public void lastPage() {
        paginationOptions.setPageNumber(paginationOptions.getTotalPages());
        paginable.updatePagination();
    }

    public void firstPage() {
        paginationOptions.setPageNumber(1);
        paginable.updatePagination();
    }

    private int getCurrentPageAfterSizeChange(int newSize) {
        int oldSize = paginationOptions.getPageSize();
        int offset = oldSize * (paginationOptions.getPageNumber() - 1);
        return offset / newSize + 1;
    }
}
