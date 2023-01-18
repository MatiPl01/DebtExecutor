package pl.edu.agh.debtexecutor.services.utils;

import javafx.beans.binding.ListExpression;
import javafx.beans.property.SimpleListProperty;
import pl.edu.agh.debtexecutor.services.options.FilterOptions;
import pl.edu.agh.debtexecutor.services.options.Filterable;
import pl.edu.agh.debtexecutor.utils.Timeout;

import java.util.List;
import java.util.Optional;

public class FilterService {
    private static final int UPDATE_TIMEOUT = 2000; // ms
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
        Optional<SimpleListProperty<String>> values = filterOptions.appliedFilterValuesProperty(filterName);
        if (values.isEmpty()) return;
        Optional<List<String>> availableValues = filterOptions.getAvailableFilterValues(filterName);
        if (availableValues.isPresent() && availableValues.get().contains(value)) {
            values.get().add(value);
        }
        startUpdateTimeout();
    }

    public void removeFilter(String filterName, String value) {
        Optional<SimpleListProperty<String>> values = filterOptions.appliedFilterValuesProperty(filterName);
        if (values.isEmpty()) return;
        Optional<List<String>> availableValues = filterOptions.getAvailableFilterValues(filterName);
        if (availableValues.isPresent() && availableValues.get().contains(value)) {
            values.get().remove(value);
        }
        startUpdateTimeout();
    }

    public void clearFilter(String filterName) {
        filterOptions.appliedFilterValuesProperty(filterName).ifPresent(ListExpression::clear);
        startUpdateTimeout();
    }

    public void clearFilters() {
        filterOptions.getAppliedFilters().values().forEach(ListExpression::clear);
        startUpdateTimeout();
    }

    private void startUpdateTimeout() {
        if (updateTimeout != null) updateTimeout.clear();
        updateTimeout = new Timeout(filterable::update, UPDATE_TIMEOUT);
    }
}
