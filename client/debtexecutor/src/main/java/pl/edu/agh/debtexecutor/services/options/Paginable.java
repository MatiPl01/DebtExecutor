package pl.edu.agh.debtexecutor.services.options;

public interface Paginable {
    PaginationOptions getPaginationOptions();

    void updatePagination();
}
