package pl.edu.agh.debtexecutor.services.options;

import javafx.beans.property.SimpleListProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FilterOptions {
    private final Map<String, SimpleListProperty<String>> availableFilters = new HashMap<>();
    private final Map<String, SimpleListProperty<String>> appliedFilters = new HashMap<>();

    public FilterOptions(List<String> filterNames) {
        filterNames.forEach(name -> availableFilters.put(name, new SimpleListProperty<>()));
    }

    public Map<String, SimpleListProperty<String>> getAvailableFilters() {
        return availableFilters;
    }

    public Optional<List<String>> getAvailableFilterValues(String filterName) {
        if (availableFilters.containsKey(filterName)) {
            return Optional.of(availableFilters.get(filterName).get());
        }
        return Optional.empty();
    }

    public Optional<SimpleListProperty<String>> availableFilterValuesProperty(String filterName) {
        return Optional.ofNullable(availableFilters.get(filterName));
    }

    public Map<String, SimpleListProperty<String>> getAppliedFilters() {
        return appliedFilters;
    }

    public Optional<List<String>> getAppliedFilterValues(String filterName) {
        if (appliedFilters.containsKey(filterName)) {
            return Optional.of(appliedFilters.get(filterName).get());
        }
        return Optional.empty();
    }

    public Optional<SimpleListProperty<String>> appliedFilterValuesProperty(String filterName) {
        return Optional.ofNullable(appliedFilters.get(filterName));
    }
}
