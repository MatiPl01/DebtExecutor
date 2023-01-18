package pl.edu.agh.debtexecutor.services.utils;

import pl.edu.agh.debtexecutor.services.options.SortDirection;
import pl.edu.agh.debtexecutor.services.options.SortOptions;
import pl.edu.agh.debtexecutor.services.options.Sortable;

public class SortService {
    private final Sortable sortable;
    private final SortOptions sortOptions;

    public SortService(Sortable sortable) {
        this.sortable = sortable;
        this.sortOptions = sortable.getSortOptions();
    }

    public SortOptions getSortOptions() {
        return sortOptions;
    }

    public void sortBy(String sortBy) {
        sortOptions.setSortBy(sortBy);
        sortable.update();
    }

    public void setSortDirection(SortDirection sortDirection) {
        sortOptions.setSortDirection(sortDirection);
        sortable.update();
    }
}
