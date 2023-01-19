package pl.edu.agh.debtexecutor.expenses.model;

import java.util.List;

public class ExpenseSearchCriteria {
    private List<String> categories;

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
