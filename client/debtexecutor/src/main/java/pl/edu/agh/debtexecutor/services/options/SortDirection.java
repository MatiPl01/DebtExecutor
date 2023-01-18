package pl.edu.agh.debtexecutor.services.options;

public enum SortDirection {
    ASC("ASC"),
    DESC("DESC");

    private final String sortDirection;

    SortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    @Override
    public String toString() {
        return sortDirection;
    }
}
