package pl.edu.agh.debtexecutor.services.utils;

import javafx.beans.binding.ListExpression;
import javafx.beans.property.SimpleListProperty;
import pl.edu.agh.debtexecutor.services.options.FilterOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterService {
    private final Map<String, SimpleListProperty<String>> filters =
            new HashMap<>();
    private final FilterOptions filterOptions;

    public FilterService(Filterable service, List<String> filterProperties) {
        filterOptions = service.getFilterOptions();
        filterProperties.forEach(property -> {
            SimpleListProperty<String> filterValues =
                    new SimpleListProperty<>();
            if (filters.get(property) != null) {
                filterValues.addAll(filters.get(property));
            }
            filters.put(property, filterValues);
        });
    }

    public List<String> getFilterValues(String filterProperty) {
        return filters.get(filterProperty).getValue();
    }

    public SimpleListProperty<String> filterValuesProperty(String filterProperty) {
        return filters.get(filterProperty);
    }

    public void setFilterValues(String filterProperty, List<String> values) {
        if (filters.get(filterProperty) == null) {
            throw new IllegalArgumentException(filterProperty +
                                               " is not a valid filter property");
        }
        filters.get(filterProperty).setAll(values);
        filterOptions.setFilter(filterProperty, values);
    }

    public void clearFilters() {
        filterOptions.clear();
        filters.values().forEach(ListExpression::clear);
    }
}
