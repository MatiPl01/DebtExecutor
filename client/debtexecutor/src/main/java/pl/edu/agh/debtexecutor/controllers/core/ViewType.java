package pl.edu.agh.debtexecutor.controllers.core;

public enum ViewType {
    SUMMARY("Expense summary"),
    HISTORY("Expense history"),
    SUMMARY_GRAPH("Expense summary"),
    HISTORY_GRAPH("Expense history"),
    SIMPLIFIED_GRAPH("Simplified expense graph"),
    CREATE_EXPENSE("Create personal expense"),
    CREATE_GROUP_EXPENSE("Create group expense"),
    CREATE_CATEGORY("Create category"),
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
