package pl.edu.agh.debtexecutor.services.utils;

import pl.edu.agh.debtexecutor.services.options.SortDirection;
import pl.edu.agh.debtexecutor.services.options.SortOptions;
import pl.edu.agh.debtexecutor.services.options.Sortable;

public class SortingService {
    private final Sortable sortable;
    private final SortOptions sortOptions;

    public SortingService(Sortable sortable) {
        this.sortable = sortable;
        this.sortOptions = sortable.getSortOptions();
    }

    public SortOptions getSortOptions() {
        return sortOptions;
    }

    public void sortBy(String sortBy) {
        sortOptions.setSortBy(sortBy);
        sortable.updateSorting();
    }

    public void setSortDirection(SortDirection sortDirection) {
        sortOptions.setSortDirection(sortDirection);
        sortable.updateSorting();
    }
}
