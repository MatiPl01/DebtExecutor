package pl.edu.agh.debtexecutor.services.options;

import pl.edu.agh.debtexecutor.services.options.FilterOptions;

public interface Filterable {
    FilterOptions getFilterOptions();

    void update();
}
