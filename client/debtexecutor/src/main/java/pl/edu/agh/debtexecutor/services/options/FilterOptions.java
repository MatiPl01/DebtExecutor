package pl.edu.agh.debtexecutor.services.options;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public class FilterOptions {
    private final Map<String, ObservableList<String>> availableFilters = new HashMap<>();
    private final Map<String, List<String>> appliedFilters = new HashMap<>();

    public FilterOptions(List<String> filterNames) {
        filterNames.forEach(filterName -> {
            availableFilters.put(filterName, FXCollections.observableArrayList());
        });
    }

    public Map<String, ObservableList<String>> getAvailableFilters() {
        return availableFilters;
    }

    public Optional<ObservableList<String>> getAvailableFilterValues(String filterName) {
        if (availableFilters.containsKey(filterName)) {
            return Optional.of(availableFilters.get(filterName));
        }
        return Optional.empty();
    }

    public Map<String, List<String>> getAppliedFilters() {
        return appliedFilters;
    }

    public Optional<List<String>> getAppliedFilterValues(String filterName) {
        return Optional.ofNullable(appliedFilters.get(filterName));
    }

    public void setAvailableFilter(String filterName, List<String> values) {
        availableFilters.get(filterName).setAll(values);
    }

    public void setAppliedFilter(String filterName, List<String> values) {
        if (!availableFilters.containsKey(filterName)) return;
        appliedFilters.put(filterName, new ArrayList<>(values));
    }

    public void removeAppliedFilter(String filterName) {
        appliedFilters.remove(filterName);
    }
}
