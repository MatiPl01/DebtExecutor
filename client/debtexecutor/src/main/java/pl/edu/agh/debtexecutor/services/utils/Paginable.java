package pl.edu.agh.debtexecutor.services.utils;

import pl.edu.agh.debtexecutor.services.options.PaginationOptions;

public interface Paginable {
    PaginationOptions getPaginationOptions();

    void fetchData();
}
