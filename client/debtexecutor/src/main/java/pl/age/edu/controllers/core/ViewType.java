package pl.age.edu.controllers.core;

public enum ViewType {
    SUMMARY("Expense summary"),
    HISTORY("Expense history"),
    CREATE_EXPENSE("Create expense"),
    CREATE_GROUP("Create group");

    private final String viewDescription;

    ViewType(String viewDescription) {
        this.viewDescription = viewDescription;
    }

    @Override
    public String toString() {
        return viewDescription;
    }
}
