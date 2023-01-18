package pl.edu.agh.debtexecutor.services.utils;

import pl.edu.agh.debtexecutor.services.options.FilterOptions;

public interface Filterable {
    FilterOptions getFilterOptions();

    void fetchData();
}
