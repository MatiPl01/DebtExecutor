package pl.edu.agh.debtexecutor.services.options;

import pl.edu.agh.debtexecutor.services.options.PaginationOptions;

public interface Paginable {
    PaginationOptions getPaginationOptions();

    void update();
}
