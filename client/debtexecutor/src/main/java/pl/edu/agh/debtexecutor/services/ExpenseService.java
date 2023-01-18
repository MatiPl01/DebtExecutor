package pl.edu.agh.debtexecutor.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;
import pl.edu.agh.debtexecutor.api.expense.ExpenseApi;
import pl.edu.agh.debtexecutor.api.expense.dto.CreateExpenseDTO;
import pl.edu.agh.debtexecutor.api.expense.dto.CreateGroupExpenseDTO;
import pl.edu.agh.debtexecutor.models.Expense;
import pl.edu.agh.debtexecutor.services.options.FilterOptions;
import pl.edu.agh.debtexecutor.services.options.PaginationOptions;
import pl.edu.agh.debtexecutor.services.options.SortOptions;
import pl.edu.agh.debtexecutor.services.utils.Filterable;
import pl.edu.agh.debtexecutor.services.utils.Paginable;
import pl.edu.agh.debtexecutor.services.utils.Sortable;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ExpenseService implements Paginable, Sortable, Filterable {
    private final static int DEFAULT_PAGE_SIZE = 10;

    private final SortOptions sortOptions = new SortOptions("date");
    private final FilterOptions filterOptions = new FilterOptions();
    private final PaginationOptions paginationOptions =
            new PaginationOptions(DEFAULT_PAGE_SIZE);
    private final ObservableList<Expense> displayedExpenses =
            FXCollections.observableArrayList();

    private final ExpenseApi expenseApi;

    private ExpenseService(ExpenseApi expenseApi) {
        this.expenseApi = expenseApi;
    }

    @Override
    public SortOptions getSortOptions() {
        return sortOptions;
    }

    @Override
    public FilterOptions getFilterOptions() {
        return filterOptions;
    }

    @Override
    public PaginationOptions getPaginationOptions() {
        return paginationOptions;
    }

    @Override
    public void fetchData() {
        displayedExpenses.setAll(expenseApi.getExpenses(
                paginationOptions.getPageSize(),
                paginationOptions.getPageNumber(),
                sortOptions.getSortBy(),
                sortOptions.getSortDirection(),
                filterOptions.getFilter("category")
        ));
    }

    public ObservableList<Expense> getDisplayedExpenses() {
        return displayedExpenses;
    }

    public Optional<Expense> addPersonalExpense(String title,
                                                BigDecimal amount,
                                                UUID payerId,
                                                UUID payeeId,
                                                UUID categoryId) {
        CreateExpenseDTO dto = new CreateExpenseDTO(title,
                                                    payerId,
                                                    payeeId,
                                                    categoryId,
                                                    amount
        );
        return expenseApi.createPersonalExpense(dto);
    }

    public List<Expense> addGroupExpense(String title,
                                         BigDecimal amount,
                                         UUID payerId,
                                         UUID groupId,
                                         UUID categoryId) {
        CreateGroupExpenseDTO dto = new CreateGroupExpenseDTO(title,
                                                              payerId,
                                                              groupId,
                                                              categoryId,
                                                              amount
        );
        return expenseApi.createGroupExpense(dto);
    }
}
