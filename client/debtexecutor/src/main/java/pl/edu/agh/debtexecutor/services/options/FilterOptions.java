package pl.edu.agh.debtexecutor.services.options;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterOptions {
    private final Map<String, List<String>> filters = new HashMap<>();

    public void setFilter(String name, List<String> values) {
        filters.put(name, values);
    }

    public List<String> getFilter(String name) {
        return filters.get(name) != null ?
               filters.get(name) :
               new ArrayList<>();
    }

    public void clear() {
        filters.clear();
    }
}
