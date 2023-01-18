package pl.edu.agh.debtexecutor.services.utils;

import pl.edu.agh.debtexecutor.services.options.SortOptions;

public interface Sortable {
    SortOptions getSortOptions();

    void fetchData();
}
