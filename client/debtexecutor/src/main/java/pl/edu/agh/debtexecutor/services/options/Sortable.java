package pl.edu.agh.debtexecutor.services.options;

import pl.edu.agh.debtexecutor.services.options.SortOptions;

public interface Sortable {
    SortOptions getSortOptions();

    void update();
}
