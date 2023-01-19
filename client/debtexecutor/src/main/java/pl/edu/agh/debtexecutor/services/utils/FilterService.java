package pl.edu.agh.debtexecutor.services.utils;

import javafx.collections.ObservableList;
import pl.edu.agh.debtexecutor.services.options.FilterOptions;
import pl.edu.agh.debtexecutor.services.options.Filterable;
import pl.edu.agh.debtexecutor.utils.Timeout;

import java.util.List;
import java.util.Optional;

public class FilterService {
    private static final int UPDATE_TIMEOUT = 500; // ms
    private Timeout updateTimeout;

    private final Filterable filterable;
    private final FilterOptions filterOptions;

    public FilterService(Filterable filterable) {
        this.filterable = filterable;
        this.filterOptions = filterable.getFilterOptions();
    }

    public FilterOptions getFilterOptions() {
        return filterOptions;
    }

    public void addFilter(String filterName, String value) {
        Optional<List<String>> values =
                filterOptions.getAppliedFilterValues(filterName);
        Optional<ObservableList<String>> availableValues =
                filterOptions.getAvailableFilterValues(filterName);

        if (availableValues.isPresent() &&
            availableValues.get().contains(value)) {
            if (values.isPresent()) {
                values.get().add(value);
            } else {
                filterOptions.setAppliedFilter(filterName, List.of(value));
            }
        }
        startUpdateTimeout();
    }

    public void removeFilter(String filterName, String value) {
        Optional<List<String>> values =
                filterOptions.getAppliedFilterValues(filterName);
        if (values.isEmpty()) return;
        Optional<ObservableList<String>> availableValues =
                filterOptions.getAvailableFilterValues(filterName);
        if (availableValues.isPresent() &&
            availableValues.get().contains(value)) {
            values.get().remove(value);
            if (values.get().size() == 0)
                filterOptions.removeAppliedFilter(filterName);
        }
        startUpdateTimeout();
    }

    public void clearFilter(String filterName) {
        filterOptions.removeAppliedFilter(filterName);
        startUpdateTimeout();
    }

    public void clearFilters() {
        filterOptions.getAppliedFilters()
                     .keySet()
                     .forEach(filterOptions::removeAppliedFilter);
        startUpdateTimeout();
    }

    private void startUpdateTimeout() {
        if (updateTimeout != null) updateTimeout.clear();
        updateTimeout = new Timeout(filterable::updateFilters, UPDATE_TIMEOUT);
    }
}
