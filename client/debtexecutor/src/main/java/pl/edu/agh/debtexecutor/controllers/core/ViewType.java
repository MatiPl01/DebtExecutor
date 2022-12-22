package pl.edu.agh.debtexecutor.controllers.core;

public enum ViewType {
    SUMMARY("Expense summary"),
    HISTORY("Expense history"),
    CREATE_EXPENSE("Create personal expense"),
    CREATE_GROUP_EXPENSE("Create group expense"),
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
