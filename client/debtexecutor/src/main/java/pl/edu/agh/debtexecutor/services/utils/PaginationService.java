package pl.edu.agh.debtexecutor.services.utils;

import pl.edu.agh.debtexecutor.services.options.Paginable;
import pl.edu.agh.debtexecutor.services.options.PaginationOptions;

public class PaginationService {
    private final Paginable paginable;
    private final PaginationOptions paginationOptions;

    public PaginationService(Paginable paginable) {
        this.paginable = paginable;
        this.paginationOptions = paginable.getPaginationOptions();
    }

    public PaginationOptions getPaginationOptions() {
        return paginationOptions;
    }

    public void setPageSize(int pageSize) {
        paginationOptions.setPageSize(Math.max(1, pageSize));
    }

    public void setCurrentPage(int pageNumber) {
        paginationOptions.setPageNumber(Math.min(
                Math.max(0, pageNumber),
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
}
