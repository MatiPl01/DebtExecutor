package pl.edu.agh.debtexecutor.services.utils;

import javafx.beans.property.SimpleObjectProperty;
import pl.edu.agh.debtexecutor.services.options.SortDirection;
import pl.edu.agh.debtexecutor.services.options.SortOptions;

public class SortService {
    private final SimpleObjectProperty<SortDirection> sortDirectionProperty = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<String> sortByProperty = new SimpleObjectProperty<>();
    private final SortOptions sortOptions;

    public SortService(Sortable service) {
        sortOptions = service.getSortOptions();
        sortDirectionProperty.set(sortOptions.getSortDirection());
        sortByProperty.set(sortOptions.getSortBy());
    }

    public void sortBy(String sortBy) {
        sortOptions.setSortBy(sortBy);
        sortByProperty.set(sortBy);
    }

    public void setSortDirection(SortDirection sortDirection) {
        sortOptions.setSortDirection(sortDirection);
        sortDirectionProperty.set(sortDirection);
    }
}
