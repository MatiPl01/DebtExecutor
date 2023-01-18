package pl.edu.agh.debtexecutor.services.options;

public class SortOptions {
    private SortDirection sortDirection = SortDirection.DESC;
    private String sortBy;

    public SortOptions(String sortBy) {
        this.sortBy = sortBy;
    }

    public void setSortDirection(SortDirection sortDirection) {
        this.sortDirection = sortDirection;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public SortDirection getSortDirection() {
        return sortDirection;
    }

    public String getSortBy() {
        return sortBy;
    }
}
